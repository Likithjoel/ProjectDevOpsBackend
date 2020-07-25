def remote = [:]
remote.name = "backend"
remote.host = "40.121.162.131"
remote.allowAnyHosts = true
node {
	withCredentials([usernamePassword(credentialsId: 'BACKEND', passwordVariable: 'password', usernameVariable: 'userName')]) {
        	remote.user = userName
        	remote.password = password
		stage('checkout') {
			echo 'checked out the code'
		}
		stage('clean workspace') {
			sh 'mvn clean'
		}
		stage('build') {
			sh 'mvn -f pom.xml install -Dmaven.test.skip=true'
		}
		stage('deploy') {
			sshCommand remote: remote, command: "rm -rf devops_backend/"
			sshPut remote: remote, from: '/var/lib/jenkins/workspace/devops_backend', into: '.'
		}
		stage('run') {
			sshCommand remote: remote, command: "cd devops_backend && mvn spring-boot:run"
		}
	}
}
