mvn clean install
docker build -t geekhitesh/users-app .
docker push geekhitesh/users-app
kubectl apply -f .
#kubectl expose deployment users-app --name=users-app --dry-run -o yaml > service.yaml