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
				sh 'mvn clean'
			}
		}
		stage('Build') {
			steps {
				sh 'mvn install'
			}
		}
	}
}
