pipeline{
    environment{
        // K8S_DOCKER_HUB_USER_ID = ""
         K8S_DOCKER_HUB_PASSWORD = ""
         //K8S_JOB_NAME = ""
         //K8S_BUILD_NUMBER = ""
    }
	agent any
	stages{
		//stage('Stage selection'){
		//	steps{
			//	script{
		//			selectedStages = "${Stage_selection}".split(',')
		//			for (String individualStage:selectedStages){
		//				echo "Selected Stage : ${individualStage}"
		//			}
					
		//		}
		//	}
		//}
		//stage("Git Clone"){
		//	steps{
		//		script{
		//			echo "${APPL_URL}"
		//			cleanWs()
					//if("${selectedStages}".contains("Git Clone")){
		//				git credentialsId: 'GitHub-takttimedev', url: "https://github.com/takttimedev/kubernetes-demo-users-app.git"
					//}
					//else{
					//	echo "Git Clone Skipped"
					//}
		//		}
		//	}
		//}   
		stage("Maven Build"){
			steps{
				script{
					//if("${selectedStages}".contains("Maven Build")){
						def mavenHome = tool name: "M3", type: "maven" 
						def mavenCMD = "${mavenHome}/bin/mvn"
						sh "${mavenCMD} clean package"
					//}
					//else{
					//	echo "Maven Build Skipped"
					//}
				}
			}
		}   
		 
		stage("Docker Build"){
			steps{
				script{
					//if("${selectedStages}".contains("Docker Build")){
						withCredentials([usernamePassword(credentialsId: 'dockerhub-041266', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER_ID')]) {
							env.K8S_DOCKER_HUB_USER_ID = "${DOCKER_HUB_USER_ID}"
							//K8S_DOCKER_HUB_PASSWORD = "${DOCKER_HUB_PASSWORD}"
							env.K8S_JOB_NAME = "${JOB_NAME}"
							env.K8S_BUILD_NUMBER = "${BUILD_NUMBER}"
							sh "docker build -t ${K8S_DOCKER_HUB_USER_ID}/${JOB_NAME}:${BUILD_NUMBER} ."
						}
					//}
					//else{
					//	echo "Docker Build Skipped"
					//}
				}
			}
		}    
		stage("Docker Push"){
			steps{
				script{
					//if("${selectedStages}".contains("Docker Push")){
						   withCredentials([usernamePassword(credentialsId: 'dockerhub-041266', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER_ID')]) {
								sh "docker login -u ${DOCKER_HUB_USER_ID} -p ${DOCKER_HUB_PASSWORD}"
								sh "docker push ${K8S_DOCKER_HUB_USER_ID}/${JOB_NAME}:${BUILD_NUMBER}"
							}
					//}
					//else{
					//	echo "Docker Push Skipped"
					//}
				}
			}
		}
		stage("K8S Push") {
			steps{
				script{
					//if("${selectedStages}".contains("K8S Push")){
						echo "${K8S_DOCKER_HUB_USER_ID}"
						//echo "${K8S_DOCKER_HUB_PASSWORD}"
						echo "${K8S_JOB_NAME}"
						echo "${K8S_BUILD_NUMBER}"
						sh "export K8S_DOCKER_HUB_USER_ID"
						sh "envsubst < deployment.tmpl > deployment.yaml"
						sh "kubectl delete -f deployment.yaml || true"
						sh "kubectl create -f deployment.yaml --validate=false"
						sh "kubectl apply -f service-nodeport.yaml --validate=false"   
					//}
					//else{
					//	echo "K8S Push Skipped"
					//}
				}
			}
		}
	}
}
