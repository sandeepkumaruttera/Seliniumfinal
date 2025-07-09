pipeline {
    agent any

    stages {
        stage('get code') {
            steps {
       git branch: 'CommitingBranch', url: 'https://github.com/pvr08/Seliniumfinal_21_3.git'           }
        }
       
        stage('Test code') {
            steps {
                sh 'mvn clean -X test'
            }
        }
        
    }
}