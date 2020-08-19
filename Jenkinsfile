pipeline {
   agent any
    environment{
            REG_SECRET = "reg-sec"
      		K8S_CLUSTER_URL = "https://172.31.62.210:6443"
			K8S_NAMESPACE = "default"
			K8S_TOK="LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJd01EZ3hNREl3TURVME1sb1hEVE13TURnd09ESXdNRFUwTWxvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBS3BWCkYyQnZ1Z0VBRmlRRnVaRWoxakFTbWorZnErZWdMd0l3MFlXSk44SDY3VXY1U0tzTnJuWk9hV0pnNWsxM3p5RloKTU5SWWMwMlFtZzZ1dFJPRUxRT3VrQUFIaW9kQTIyOHZpOU1ka1pnc0pmcXlmRWFQWjNTSzVibElwdWM1TjIxagpBKzVtK0k3Zi9JajFuR25uT2N4TVNmS2FBWC9URmxDRFZwbUJwQkhaMkw0RHpWRWhOcjNxTHJRNmxBakh6WlFMCnU2aDd1ZHVmVW1UQXl0ZTRkWFJSMXRnN2s2ZkpaYlpWOW1jYlErcFhCd3JyODJ5OVd2cnAyc3ZOUnJnV003OVQKNnNMbUgxQktkYnFhNnhZTnI2dTZRalE2SDE2VEY0VnNTdGFHTHVFdC9IS1JsTEFOaG5uOHhyd3ZZdlA2V0ZJbgpmeXZvKzhCR082Y3Y1U2s2ZSs4Q0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFHcVVXMHdqYSt0RkJrbEI2U2Q5citZWU5yckoKOTFBNTQ1R3FDQ0dWekdaSzZQOEFtRHFnTzV6bFhwTXlwSUxTQXEvNnRKcnJIRTg0elhwMDd2YjdubTI5YVBWUApHV0xlSmV0cHBlMm5TbDZ0cGVJdTQ3Yy84OVR3NkNpcGliR1JkYzBuSllld05KYW9qTGh6T1lMTjU3bjlCdnF2CmYrNmhWQzFMZzJUekg0cXNEKzYzdXluZjdIVkNtVE4rajcyckNTUHYyMTBvYnI5UTBtZGZzak95eUxsL2FGWk4KQ2RacEJySC9oYUJYZFdCNTNwVzdrTUdOMUxYd0RBaVkvcENpK3pJc2wrb2tBWWt3ZEgwVnpGeURwd1hza09maQp2cXhkdWowaE1FUHk1UllMRXdLQ0w5RDB6b0lxeUh1aUx3OFdySHRJVGNTNmdra1NUbm96MDNBSGlLQT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo="
    }
    stages {
 	
	stage('K8S Push') {
         steps {
		 script{
			if(!K8S_TOK.isEmpty()){
				  sh "kubectl config set-cluster mine --server=${K8S_CLUSTER_URL} --insecure-skip-tls-verify=true"
				  sh "kubectl config set-credentials mine --token=${K8S_TOK}"
				  sh "kubectl config set-context mine --cluster=mine --user=mine --namespace=${env.K8S_NAMESPACE}"
				  sh "kubectl config use-context mine"
				  sh "kubectl delete -f deployment.yaml || true"
				  sh "kubectl create -f deployment.yaml --validate=false"
				  sh "kubectl apply -f service.yaml -f ingress.yaml --validate=false"
				  sh "kubectl -n ${env.K8S_NAMESPACE} describe ingress test"
			}
		 }
            }
        }
    }
}
