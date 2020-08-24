pipeline{
    environment{
        // K8S_DOCKER_HUB_USER_ID = ""
         K8S_DOCKER_HUB_PASSWORD = ""
         //K8S_JOB_NAME = ""
         //K8S_BUILD_NUMBER = ""
    }
	agent any
	stages{
		stage("Git clone"){
			steps{
				script{
					git credentialsId: 'GitHub-takttimedev', url: "${GIT_URL}"
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
						env.K8S_DOCKER_HUB_USER_ID = "${DOCKER_HUB_USER_ID}"
						//K8S_DOCKER_HUB_PASSWORD = "${DOCKER_HUB_PASSWORD}"
						env.K8S_JOB_NAME = "${JOB_NAME}"
						env.K8S_BUILD_NUMBER = "${BUILD_NUMBER}"
						sh "docker build -t ${K8S_DOCKER_HUB_USER_ID}/${JOB_NAME}:${BUILD_NUMBER} ."
					}
				}
			}
		}    
		stage("Docker Push"){
			steps{
				script{
				   withCredentials([usernamePassword(credentialsId: 'dockerhub-041266', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER_ID')]) {
						sh "docker login -u ${DOCKER_HUB_USER_ID} -p ${DOCKER_HUB_PASSWORD}"
						sh "docker push ${K8S_DOCKER_HUB_USER_ID}/${JOB_NAME}:${BUILD_NUMBER}"
					}
				}
			}
		}
		stage("K8S Push") {
			steps{
				script{
					echo "${K8S_DOCKER_HUB_USER_ID}"
					//echo "${K8S_DOCKER_HUB_PASSWORD}"
					echo "${K8S_JOB_NAME}"
					echo "${K8S_BUILD_NUMBER}"
					//sh "export K8S_DOCKER_HUB_USER_ID"
					//sh "envsubst < deployment.tmpl > deployment.yaml"
					sh "kubectl delete -f deployment.yaml || true"
					sh "kubectl create -f deployment.yaml --validate=false"
					sh "kubectl apply -f service-nodeport.yaml --validate=false"   
				}
			}
		}
	}
}
