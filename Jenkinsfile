pipeline {

    // Where Jenkins executes the pipeline
    agent any


    // Tools configured in:
    // Manage Jenkins → Tools
    tools {
        maven 'Maven-3.9.16'
    }


    // Environment variables
    environment {
        APP_NAME = 'student-api'
        VERSION = '1.0'
    }


    // Input given when starting the build
    parameters {

        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'staging', 'prod'],
            description: 'Select deployment environment'
        )

        choice(
            name: 'TEST_TYPE',
            choices: ['all', 'unit'],
            description: 'Select test type'
        )
    }


    // Main pipeline stages
    stages {


        // --------------------------------
        // STAGE 1
        // --------------------------------
        stage('Check Environment') {

            steps {

                echo "Application: ${env.APP_NAME}"
                echo "Version: ${env.VERSION}"
                echo "Build Number: ${env.BUILD_NUMBER}"
                echo "Workspace: ${env.WORKSPACE}"

                bat 'java -version'
                bat 'mvn --version'
            }
        }


        // --------------------------------
        // STAGE 2
        // --------------------------------
        stage('Build') {

            steps {
                echo 'test the ngrok works or not'

                echo "Building ${env.APP_NAME}"

                bat 'mvn clean package -DskipTests'
            }
        }


        // --------------------------------
        // STAGE 3
        // --------------------------------
        stage('Test') {
        when{
            anyOf {
                branch 'main'
                expression {
                    params.ENVIRONMENT=='staging'
                }
            }
        }
            steps {

                echo "Running tests..."

                bat 'mvn test'
                echo "the value of environment: ${env.VERSION}"
            }

            post {

                always {

                    echo 'Publishing JUnit test results...'

                    junit 'target/surefire-reports/*.xml'
                }
            }
        }


        // --------------------------------
        // STAGE 4
        // --------------------------------
        stage('Deploy') {

            when {

                allOf {

                    branch 'main'

                    expression {
                        params.ENVIRONMENT == 'prod'
                    }
                }
            }

            steps {

                echo "Deploying ${env.APP_NAME}"
                echo "Environment: ${params.ENVIRONMENT}"

                bat '''
                    echo Deploying application...
                    echo Application: %APP_NAME%
                    echo Version: %VERSION%
                '''
            }
        }


        // --------------------------------
        // STAGE 5
        // --------------------------------
        stage('Archive') {

            steps {

                echo 'Archiving JAR file...'

                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }
    }


    // --------------------------------
    // PIPELINE POST ACTIONS
    // --------------------------------

    post {

        success {

            echo "🎉 Pipeline successful!"
            echo "Application: ${env.APP_NAME}"
            echo "Build: ${env.BUILD_NUMBER}"
        }

        failure {

            echo "❌ Pipeline failed!"
            echo "Check the Console Output."
        }

        always {

            echo "Pipeline execution completed."

            cleanWs()
        }
    }
}
