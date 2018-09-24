pipeline {
   agent any
   stages {
      stage ('Build App' /*, compliance-check: "build-checks" */ ) {
         steps {
            withMaven() {
               sh "./mvnw verify"
               jacoco classPattern: 'target/classes', sourcePattern: 'src/main/java'
            }
         }
         // perform compliance checks related to the "Build" stage
      }

      stage ('Deploy App') {
         steps {
            // Deploy to K8S with grafeas enabled
         }
      }
   }
}