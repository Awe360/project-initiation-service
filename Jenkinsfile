pipeline {
    agent {
        label "jenkins-agent"
    }
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

        // Add this for clarity
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
                echo "Checking out ${APP_NAME} module from Git repository..."
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/dpi-saas/saas-backend'
            }
        }

        stage('Build Module') {
            steps {
                echo "Building the ${APP_NAME} module..."
                dir("${APP_NAME}") {
                    sh "mvn clean package -DskipTests"
                }
            }
        }

        stage('Test Module') {
            steps {
                echo "Running tests for ${APP_NAME}..."
                dir("${APP_NAME}") {
                    sh "mvn test"
                }
            }
        }

        /*
        stage('SonarQube Analysis') {
            steps {
                script {
                    echo "Performing SonarQube analysis on ${APP_NAME}..."
                    withSonarQubeEnv('sonarqube-server') {
                        dir("${APP_NAME}") {
                            sh "mvn sonar:sonar"
                        }
                    }
                }
            }
        }
        */

        stage('Build & Push Docker Image') {
            steps {
                dir("${APP_NAME}") {
                    echo "Building and pushing Docker images (tag: ${IMAGE_TAG} and latest) for ${APP_NAME} using Jib..."
                    withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                    usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKERHUB_TOKEN')]) {
                        sh """
                        mvn clean compile jib:build \
                            -Dimage=${IMAGE_NAME}:${IMAGE_TAG} \
                            -Djib.to.auth.username=${DOCKERHUB_USER} \
                            -Djib.to.auth.password=${DOCKERHUB_TOKEN}
                        """

                        sh """
                            mvn compile jib:build \
                                -Dimage=${IMAGE_NAME}:latest \
                                -Djib.to.auth.username=${DOCKERHUB_USER} \
                                -Djib.to.auth.password=${DOCKERHUB_TOKEN}
                        """
                    }
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
                        --format table > trivy-scan-results-${APP_NAME}.txt
                    """

                    archiveArtifacts artifacts: "trivy-scan-results-${APP_NAME}.txt", allowEmptyArchive: true
                    echo "Trivy Scan Results:"
                    sh "cat trivy-scan-results-${APP_NAME}.txt"
                }
            }
        }
        */

        stage('Deploy to Minikube') {
            steps {
                echo "Deploying ${APP_NAME} to local Minikube cluster..."

                // Option 1: Recommended - use Kubernetes CLI plugin (install if not present: Manage Plugins → Kubernetes CLI)
                // withKubeConfig([credentialsId: env.KUBECONFIG_CREDENTIAL_ID, serverUrl: '']) {
                //     sh "kubectl apply -f deployment/k8s/apps/${APP_NAME}.yaml"
                //     sh "kubectl rollout restart deployment/${APP_NAME}"
                //     sh "kubectl rollout status deployment/${APP_NAME} --timeout=120s"
                // }

                // Option 2: Manual way (works without plugin)
                withCredentials([file(credentialsId: env.KUBECONFIG_CREDENTIAL_ID, variable: 'KUBECONFIG_FILE')]) {
                    // Optional: install kubectl if missing in agent
                    sh '''
                        if ! command -v kubectl &> /dev/null; then
                            echo "Installing kubectl..."
                            curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
                            chmod +x kubectl
                            mv kubectl /usr/local/bin/
                        fi
                    '''

                    script {
                        // Use the mounted kubeconfig
                        sh '''
                            export KUBECONFIG=${KUBECONFIG_FILE}
                            kubectl get nodes  # quick test connection
                            kubectl apply -f deployment/k8s/apps/${APP_NAME}.yaml
                            kubectl rollout restart deployment ${APP_NAME}
                            kubectl rollout status deployment ${APP_NAME} --timeout=120s
                        '''
                    }
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