pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        gradle 'Gradle 8'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '⬇️ Cloning Repository...'
                git url: 'https://github.com/edwinchp/api-testing-framework.git', branch: 'master'
            }
        }

        stage('Execute tests') {
            steps {
                echo '🔐 Injecting secrets as environment variables '
                withCredentials([
                    string(credentialsId: 'GOREST_API_AUTH_TOKEN', variable: 'GOREST_API_AUTH_TOKEN'),
                    string(credentialsId: 'SPOTIFY_CLIENT_ID', variable: 'SPOTIFY_CLIENT_ID'),
                    string(credentialsId: 'SPOTIFY_CLIENT_SECRET', variable: 'SPOTIFY_CLIENT_SECRET'),
                    string(credentialsId: 'SPOTIFY_ACCESS_TOKEN_URL', variable: 'SPOTIFY_ACCESS_TOKEN_URL')
                ]){
                    echo '🚀 Executing tests'
                    sh '''
                      gradle test \
                        -DGOREST_API_AUTH_TOKEN=$GOREST_API_AUTH_TOKEN \
                        -DSPOTIFY_CLIENT_ID=$SPOTIFY_CLIENT_ID \
                        -DSPOTIFY_CLIENT_SECRET=$SPOTIFY_CLIENT_SECRET \
                        -DSPOTIFY_ACCESS_TOKEN_URL=$SPOTIFY_ACCESS_TOKEN_URL \
                    '''
                }
            }
        }
    }

post {
        always {
            archiveArtifacts artifacts: 'build/cucumber.json', onlyIfSuccessful: false
            archiveArtifacts artifacts: 'build/cucumber-reports.html', onlyIfSuccessful: true
            cucumber buildStatus: 'UNSTABLE',
                reportTitle: 'API Testing Framework',
                fileIncludePattern: '**/*.json',
                trendsLimit: 10,
                classifications: [
                    [
                        'key': 'Type',
                        'value': 'Smoke'
                    ]
                ]
        }
        success {
            echo '✅  Tests executed successfully!'
        }
        failure {
            echo '❌  Something went wrong, please check the logs.'
        }
    }
}