pipeline {
  agent any // configure here if you need any agent
  options {
    buildDiscarder(logRotator(numToKeepStr: '10')) // configurable
    timeout(time: 1, unit: 'HOURS') // configurable
  }
  stages {
    stage('Build release branch and Upload to Nexus') {
      when {
        branch 'master' // release branch 'master'
      }
      steps {
        echo "master build"
        withMaven(){ // mimic maven-plugin behaviour?
          sh "./mvnw clean source:jar deploy" // for temporary nodes
        }
      }
    }

    stage('Build maintenance branch') {
      when {
        expression { "${env.BRANCH_NAME}" =~ /v\d+\.x/ }  // maintenance release branch 'v1.x', 'v2.x'
      }
      steps {
        echo "maintenance branch build"
        withMaven(){ // mimic maven-plugin behaviour?
          sh "./mvnw clean source:jar deploy" // for temporary nodes
        }
      }
    }

    stage('Build Pull Request') {
      when {
        expression { env.CHANGE_ID != null }  // Pull request
      }
      steps {
        echo "pull request build"
        withMaven(){ // mimic maven-plugin behaviour?
          sh "./mvnw clean verify" // for temporary nodes
        }
      }
    }

    stage('Build something interesting') { // in no special case so not sure about the stage name to use..
      when {
        allOf {
          expression { env.CHANGE_ID == null }  // Pull request
          expression { "${env.BRANCH_NAME}" !=~ /v\d+\.x/ }
          expression { "${env.BRANCH_NAME}" != 'master' }
        }
      }
      steps {
        echo "Build something interesting"
        //  Don't fail the build on test failure, let withMaven mark as unstable: -DtestFailureIgnore=true
        withMaven(){ // mimic maven-plugin behaviour?
          sh "./mvnw -DtestFailureIgnore=true -Dmaven.javadoc.failOnError=false clean verify" // for temporary nodes
        }
      }
    }
  }
}
