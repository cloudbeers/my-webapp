node {
    checkout scm
    if (env.BRANCH_NAME == 'master') { // release branch 'master'
          stage ('Build') {
            withMaven() {
              sh "./mvnw clean source:jar deploy"
            }
          } // end stage 'Build'
      } else if (env.BRANCH_NAME =~ /v\d+\.x/ ) { // maintenaince release branch 'v1.x', 'v2.x'
          stage ('Build') {
            withMaven() {
              sh "./mvnw clean source:jar deploy"
            }
          } // end stage 'Build'
      } else if (env.CHANGE_ID != null) { // Pull Request
          stage ('Build') {
            withMaven() {
              sh "./mvnw clean verify"
            }
          } // end stage 'Build'
      } else {
          stage ('Build') {
            withMaven() {
              //  Don't fail the build on test failure, let withMAven mark as unstable: -DtestFailureIgnore=true
              sh "./mvnw  -Dmaven.test.failure.ignore=true -Dspotbugs.failOnError=false -Dmaven.javadoc.failOnError=false clean verify"
            }
          } // end stage 'Build'
      }

}