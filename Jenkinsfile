pipeline{
    environment{
         K8S_DOCKER_HUB_USER_ID = ""
         K8S_DOCKER_HUB_PASSWORD = ""
         K8S_JOB_NAME = ""
         K8S_BUILD_NUMBER = ""
    }
	agent {
        label 'DOCKER'
    }
	stages{
		stage("Git clone"){
			steps{
				script{
					git credentialsId: 'GitHub-takttimedev', url: 'https://github.com/takttimedev/kubernetes-demo-users-app.git'
				}
			}
		}    
		stage("Maven Build"){
			steps{
				script{
					def mavenHome = tool name: "M3", type: "maven" 
					def mavenCMD = "${mavenHome}/bin/mvn"
					sh "${mavenCMD} clean package"
				}
			}
		}    
		stage("Docker Build"){
			steps{
				script{
					withCredentials([usernamePassword(credentialsId: 'dockerhub-041266', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER_ID')]) {
						K8S_DOCKER_HUB_USER_ID = "${DOCKER_HUB_USER_ID}"
						K8S_DOCKER_HUB_PASSWORD = "${DOCKER_HUB_PASSWORD}"
						env.K8S_JOB_NAME = "${JOB_NAME}"
						env.K8S_BUILD_NUMBER = "${BUILD_NUMBER}"
						sh "docker build -t 041266/app-users ."
					}
				}
			}
		}    
		stage("Docker Push"){
			steps{
				script{
				   withCredentials([usernamePassword(credentialsId: 'dockerhub-041266', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER_ID')]) {
						sh "docker login -u ${DOCKER_HUB_USER_ID} -p ${DOCKER_HUB_PASSWORD}"
						sh "docker push 041266/app-users"
					}
				}
			}
		}
		stage("K8S Push") {
			steps{
				script{
					echo "${K8S_DOCKER_HUB_USER_ID}"
					echo "${K8S_DOCKER_HUB_PASSWORD}"
					echo "env.K8S_JOB_NAME"
					echo "env.K8S_BUILD_NUMBER"
					sh "envsubst < deployment.yaml"
					sh "kubectl delete -f deployment.yaml || true"
					sh "kubectl create -f deployment.yaml --validate=false"
					sh "kubectl apply -f service-nodeport.yaml --validate=false"   
				}
			}
		}
	}
}
