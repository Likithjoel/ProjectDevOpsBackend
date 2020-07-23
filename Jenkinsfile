pipeline {
	agent any
	environment{
        SOME_CREDS = credentials('BACKEND')
    }
	stages{
		stage('Checkout') {
			steps {
				echo 'checked out code'
				script{
					sh "sshpass -e '${env.SOME_CREDS_PSW}' scp -r /home/jenkins/likith backend@40.121.162.131:./"
				}
			}
		}
		stage('clean workspace') {
			steps {
				sh 'mvn clean'
			}
		}
		stage('Build') {
			steps {
				sh 'mvn -f pom.xml install -Dmaven.test.skip=true'
			}
		}
	}
}
