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
        mavenBuild("clean source:jar deploy")
      }
    }

    stage('Build maintenance branch') {
      when {
        expression { ${env.BRANCH_NAME} =~ /v\d+\.x/ }  // maintenance release branch 'v1.x', 'v2.x'
      }
      steps {
        echo "maintenance branch build"
        mavenBuild("clean source:jar deploy")
      }
    }

    stage('Build Pull Request') {
      when {
        expression { ${env.CHANGE_ID} != null }  // Pull request
      }
      steps {
        echo "pull request build"
        mavenBuild("clean verify")
      }
    }

    stage('Build something interesting') { // in no special case so not sure about the stage name to use..
      when {
        allOf {
          expression { ${env.CHANGE_ID} == null }  // Pull request
          expression { ${env.BRANCH_NAME} !=~ /v\d+\.x/ }
          expression { ${env.BRANCH_NAME} != 'master' }
        }
      }
      steps {
        echo "Build something interesting"
        //  Don't fail the build on test failure, let withMaven mark as unstable: -DtestFailureIgnore=true
        mavenBuild("-DtestFailureIgnore=true -Dmaven.javadoc.failOnError=false clean verify")
      }
    }
  }
}


def mavenBuild(cmdline) {
  def localRepo = "${env.JENKINS_HOME}/${env.EXECUTOR_NUMBER}"
  def settingsName = 'settings.xml'
  def mavenOpts = '-Xms1g -Xmx4g -Djava.awt.headless=true'

  withMaven(
          //maven: mvnName,
          //jdk: jdk,
          //globalMavenSettingsConfig: settingsName,
          //mavenOpts: mavenOpts,
          mavenLocalRepo: ".repository"){ // mimic maven-plugin behaviour?
    sh "./mvnw $cmdline" // for temporary nodes
    //sh "mvn $cmdline"
  }
}
