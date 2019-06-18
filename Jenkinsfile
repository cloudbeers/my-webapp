pipeline {
  agent any // configure here if you need any agent
  options {
    buildDiscarder(logRotator(numToKeepStr: '10')) // configurable
    timeout(time: 1, unit: 'HOURS') // configurable
  }
  stages {
    stage( 'Build release branch and Upload to Nexus' ) {
      when {
        expression { BRANCH_NAME == 'master' } // release branch 'master'
      }
      steps {
        withMaven() {
          sh "./mvnw clean source:jar deploy"
        }
      }
    }

    stage( 'Build maintenance branch' ) {
      when {
        expression { BRANCH_NAME =~ /v\d+\.x/ }  // maintenance release branch 'v1.x', 'v2.x'
      }
      steps {
        withMaven() {
          sh "./mvnw clean source:jar deploy"
        }
      }
    }

    stage( 'Build Pull Request' ) {
      when {
        expression { CHANGE_ID != null }  // Pull request
      }
      steps {
        withMaven() {
          sh "./mvnw clean verify"
        }
      }
    }
  }

  // other cases
//  withMaven() {
//    //  Don't fail the build on test failure, let withMaven mark as unstable: -DtestFailureIgnore=true
//    sh "./mvnw  -DtestFailureIgnore=true -Dmaven.javadoc.failOnError=false clean verify"
//  }
}
