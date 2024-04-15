pipeline {
    agent {
        label 'Jenkins-Agent'
    }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    stages {
        stage("Cleanup Workspace") {
            steps {
                cleanWs() // This command cleans up the workspace before the job runs
            }
        }
        stage("Checkout from SCM") {
            steps {
                // Checkout the code from your Git repository
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/Hariompal4/register-app1.git'
            }
        }
        stage("Build Application") {
            steps {
                // Compile and package the application using Maven
                sh "mvn clean package"
            }
        }
        stage("Test Application") {
            steps {
                // Run unit tests with Maven
                sh "mvn test"
            }
        }
        stage("SonarQube Analysis") {
            steps {
                script {
                    // Run SonarQube analysis; ensure that the SonarQube server configuration is set in Jenkins
                    withSonarQubeEnv('jenkins-sonarqube-token') { // 'jenkins-sonarqube-token' should be the ID of your SonarQube service connection configured in Jenkins
                        sh "mvn sonar:sonar"
                    }
                }
            }
        }
        stage("Quality Gate") {
            steps {
                script {
                    // This will check the Quality Gate result and make a decision if it should fail the build or not
                    timeout(time: 1, unit: 'HOURS') { // Prevents the pipeline from waiting indefinitely for SonarQube to return the quality gate result
                        def qg = waitForQualityGate() // This method captures the result of the Quality Gate
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
}
