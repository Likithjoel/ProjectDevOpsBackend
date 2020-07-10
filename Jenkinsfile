pipeline {
	agent any
	stages{
		stage('Checkout') {
			steps {
				echo 'checked out code'
			}
		}
		stage('clean workspace') {
			steps {
				mvn clean
			}
		}
		stage('Build') {
			steps {
				mvn install
			}
		}
	}
}
