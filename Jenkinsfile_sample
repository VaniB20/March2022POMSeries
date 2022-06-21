pipeline {
    
    agent any
    
        stages {
            
            stage("Build") {
                steps {
                    echo("Build project")
                }
            }
            
            stage("Run UTs") {
                steps {
                    echo("Run unit test cases")
                }
            }
            
            
            stage("Run SITs") {
                steps {
                    echo("Run integration test cases")
                }
            }
            stage("Deploy DEV") {
                steps {
                    echo("Deploy to dev")
                }
            }
            
            stage("Deploy QA") {
                steps {
                    echo("Deploy to QA")
                }
            }
            
              stage("Run tests on QA") {
                steps {
                    echo("Run test sanity automation on QA")
                }
            }
            
             stage("Deploy Stage") {
                steps {
                    echo("Deploy to Stage")
                }
            }
            
              stage("Run tests on Stage") {
                steps {
                    echo("Run test sanity automation on Stage")
                }
            }
            
             stage("Deploy Prod") {
                steps {
                    echo("Deploy to prod")
                }
            }
          
            
        }
    
    }