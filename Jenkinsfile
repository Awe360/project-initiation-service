pipeline {
    agent any

    tools {
        jdk 'Java21'
        maven 'Maven3'
    }

    environment {
        APP_NAME = "project-initiation-service"
        RELEASE = "1.0.0"
        DOCKERHUB_USER = "awoke"
        IMAGE_NAME = "${DOCKERHUB_USER}/${APP_NAME}"
        IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"

        KUBECONFIG_CREDENTIAL_ID = 'minikube-kubeconfig'
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                echo "Cleaning up the workspace..."
                cleanWs()
            }
        }

        stage('Checkout SCM') {
            steps {
                echo "Checking out project-initiation-service from Git repository..."
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/Awe360/project-initiation-service.git'
            }
        }

        stage('Build Module') {
            steps {
                echo "Building the project-initiation-service module..."

                // Quick debug (remove after first successful run)
                sh 'pwd && ls -la'
                sh 'find . -name pom.xml || echo "pom.xml not found!"'

                sh "mvn clean package -DskipTests"
            }
        }

        stage('Test Module') {
            steps {
                echo "Running tests for project-initiation-service..."
                sh "mvn test"
            }
        }

        /*
        stage('SonarQube Analysis') {
            steps {
                script {
                    echo "Performing SonarQube analysis on project-initiation-service..."
                    withSonarQubeEnv('sonarqube-server') {
                        sh "mvn sonar:sonar"
                    }
                }
            }
        }
        */

        stage('Build & Push Docker Image') {
            steps {
                echo "Building and pushing Docker images (tag: ${IMAGE_TAG} and latest) using Jib..."

                withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                usernameVariable: 'DOCKER_USER',
                                passwordVariable: 'DOCKERHUB_TOKEN')]) {
                    sh """
                    mvn clean compile jib:build \
                        -Dimage=${IMAGE_NAME}:${IMAGE_TAG} \
                        -Djib.to.auth.username=${DOCKER_USER} \
                        -Djib.to.auth.password=${DOCKERHUB_TOKEN}
                    """

                    sh """
                    mvn compile jib:build \
                        -Dimage=${IMAGE_NAME}:latest \
                        -Djib.to.auth.username=${DOCKER_USER} \
                        -Djib.to.auth.password=${DOCKERHUB_TOKEN}
                    """
                }
            }
        }

        /*
        stage('Trivy Scan') {
            steps {
                script {
                    echo "Performing Trivy vulnerability scan on the Docker image..."
                    sh """
                    docker run -v /var/run/docker.sock:/var/run/docker.sock \
                        aquasec/trivy image ${IMAGE_NAME}:latest \
                        --no-progress --scanners vuln \
                        --exit-code 0 --severity HIGH,CRITICAL \
                        --format table > trivy-scan-results.txt
                    """

                    archiveArtifacts artifacts: "trivy-scan-results.txt", allowEmptyArchive: true
                    echo "Trivy Scan Results:"
                    sh "cat trivy-scan-results.txt"
                }
            }
        }
        */

        stage('Deploy to Minikube') {
            steps {
                echo "Deploying ${APP_NAME} to local Minikube cluster..."

                withCredentials([file(credentialsId: env.KUBECONFIG_CREDENTIAL_ID, variable: 'KUBECONFIG_FILE')]) {
                    // Install kubectl if missing
                    sh '''
                        if ! command -v kubectl &> /dev/null; then
                            echo "Installing kubectl..."
                            curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
                            chmod +x kubectl
                            mv kubectl /usr/local/bin/ || sudo mv kubectl /usr/local/bin/
                        fi
                    '''

                    sh '''
                        export KUBECONFIG=${KUBECONFIG_FILE}
                        echo "Testing Kubernetes connection..."
                        kubectl get nodes || echo "Kubernetes connection failed - check kubeconfig"

                        kubectl apply -f deployment/k8s/apps/${APP_NAME}.yaml || echo "Apply failed - check manifest path"
                        kubectl rollout restart deployment ${APP_NAME} || echo "Restart failed - check deployment name"
                        kubectl rollout status deployment ${APP_NAME} --timeout=120s
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline for ${APP_NAME} completed successfully."
        }
        failure {
            echo "Pipeline for ${APP_NAME} failed. Please check the logs."
        }
        always {
            echo "Final cleanup after ${APP_NAME} pipeline..."
            cleanWs()
        }
    }
}