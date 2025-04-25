kubectl apply -f ./env/zookeeper-k8s.yaml
sleep 1
kubectl apply -f ./env/mongo-k8s.yaml
sleep 1
kubectl apply -f ./env/postgres-k8s.yaml
sleep 1
kubectl apply -f ./env/adminer-k8s.yaml
sleep 1
kubectl apply -f ./env/zipkin-k8s.yaml
sleep 1
kubectl apply -f ./env/node-mailer-k8s.yaml
sleep 5
check () {
  echo $(kubectl get pods | grep Running | wc -l)
}
while [[ $(check) -ne 6 ]]; do
  sleep 5
done
kubectl apply -f ./env/kafka-k8s.yaml
sleep 5
