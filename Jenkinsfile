pipeline {
    agent any
    
    tools {
        maven 'Maven'
    }
    
    stages {
        stage('Clonar Repositorio') {
            steps {
                git branch: 'main', url: 'https://github.com/Blair707/Sucursal_Vehiculos.git'
            }
        }
        
        stage('Compilar con Maven') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        
        stage('Construir imagen Docker') {
            steps {
                sh 'docker build -t vehiculos-app .'
            }
        }
        
        stage('Desplegar contenedor') {
            steps {
                sh '''
                    docker stop vehiculos || true
                    docker rm vehiculos || true
                    docker run -d -p 9090:8080 --name vehiculos vehiculos-app
                '''
            }
        }
    }
}