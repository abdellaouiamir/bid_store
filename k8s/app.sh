kubectl apply -f ./config-server-k8s.yml
sleep 20
kubectl apply -f ./client-k8s.yml
sleep 1
kubectl apply -f ./product-k8s.yml
sleep 1
kubectl apply -f ./notification-k8s.yml
sleep 20
kubectl apply -f ./bid-k8s.yml
sleep 1
kubectl apply -f ./payment-k8s.yml
sleep 10
kubectl apply -f ./env/nginx-k8s.yml
sleep 5
check () {
  echo $(kubectl get pods | grep Running | wc -l)
}
while [[ $(check) -ne 14 ]]; do
  sleep 5
done
