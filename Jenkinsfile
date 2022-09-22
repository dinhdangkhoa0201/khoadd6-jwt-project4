pipeline {
    agent any

    environment {
        GITHUB_URL = "https://github.com/dinhdangkhoa0201/khoadd6-jwt-project4.git"
        GITHUB_CREDETIALS = credetials("github-dinhdangkhoa0201")
        BRANCH = "main"
    }

    stages {
        stage("Clone Code") {
            steps {
                git url: "${GITHUB_URL}",
                        branch: "${BRANCH}",
                        credetialsId: "${GITHUB_CREDETIALS}"
            }
        }
        stages("Build") {
            steps {
                sh "mvn clean install"
            }
        }
        stage("Test") {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    junit "target/surefire-reports/*.xml"
                }
            }
        }
    }
}
