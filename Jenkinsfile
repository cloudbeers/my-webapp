pipeline {
   agent any
   stages {
      stage ('Build App' /*, compliance-check: "build-checks" */ ) {
         steps {
            withMaven() {
               sh "./mvnw verify"
            }
         } // withMaven collects jacoco reports
         // perform compliance checks related to the "Build" stage
      }

      stage ('Deploy App') {
         steps {
            echo "deploy app..."
         }
      }
   }
}