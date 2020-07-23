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
					println "user: ${env.SOME_CREDS_USR}"
                    println "pass: ${env.SOME_CREDS_PSW}"
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
