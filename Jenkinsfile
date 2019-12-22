pipeline {
  agent any
  stages {
    stage('SCM pull') {
      steps {
        git(url: 'https://github.com/SeleniumTestAB/spock-allure', branch: 'master', changelog: true, credentialsId: 'SeleniumTestAB', poll: true)
      }
    }

    stage('sonar analysis') {
      steps {
        withCredentials([string(credentialsId: 'allure-spock', variable: 'SONAR_TOKEN')]) {
          withMaven(maven: 'maven') {
        sh '''mvn compile sonar:sonar \
          -Dsonar.projectKey=spock-allure \
          -Dsonar.host.url=$SONAR_SERVER \
          -Dsonar.login=$SONAR_TOKEN'''
        }
        }
        
      }
    }

    stage('buiild project') {
      steps {
        withMaven(maven: 'maven') {
        sh 'mvn clean install'
        }
      }
    }
    
    stage('Allure report'){
      steps{
        allure([
          includeProperties: false,
          jdk: 'java11',
          properties: [[key: 'allure.issues.tracker.pattern', value: 'http://tracker.company.com/%s']],
          reportBuildPolicy: 'ALWAYS',
          results: [[path: 'target/allure-results'], [path: 'other_target/allure-results']]
          ])
      }
    }

    stage('publish sonar scan') {
      steps {
        findBuildScans()
      }
    }
    

  }
}