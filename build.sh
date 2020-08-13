mvn clean install
docker build -t 041266/users-app .
docker push 041266/users-app
kubectl apply -f .
#kubectl expose deployment users-app --name=users-app --dry-run -o yaml > service.yaml

#https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html