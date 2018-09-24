pipeline {
	agent any
	stages {
		stage ('Build') {
			withMaven() {
				sh "./mvnw verify"
			}
		}
	}
}