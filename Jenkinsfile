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
        sh '''mvn sonar:sonar \\
  



-Dsonar.projectKey=spock-allure \\
  -Dsonar.host.url=$SONAR_SERVER \\
  


-Dsonar.login=$SONAR_TOKEN'''
      }
    }

    stage('buiild project') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('publish sonar scan') {
      steps {
        findBuildScans()
      }
    }

  }
}