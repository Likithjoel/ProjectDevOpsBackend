pipeline {
	agent any
	environment{
        BACKEND_CREDS = credentials('BACKEND')
    }
	def remote = [:]
    			remote.name = 'backend'
   			remote.host = '40.121.162.131'
    			remote.user = 'backend'
    			remote.password = '${env.BACKEND_CREDS_PSW}'
    			remote.allowAnyHosts = true
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
				sh 'mvn -f pom.xml install -Dmaven.test.skip=true'
			}
		}
		stage('Deploy') {
			steps {
				script{
					sh "sshpass -p '${env.BACKEND_CREDS_PSW}' scp -r /var/lib/jenkins/workspace/devops_backend backend@40.121.162.131:./"
				}
			}
		}
		stage('Run Application') {
			
			sshCommand remote: remote, command: "ls -lrt"
		}
	}
}
