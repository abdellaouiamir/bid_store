#!/usr/bin/env bash

kubectl apply -f ./env/mongo-k8s.yaml
sleep 1
kubectl apply -f ./env/postgres-k8s.yaml
sleep 1
kubectl apply -f ./env/adminer-k8s.yaml
sleep 1
kubectl apply -f ./env/zipkin-k8s.yaml
sleep 1
kubectl apply -f ./env/node-mailer-k8s.yaml
sleep 1
#kubectl apply -f ./notification-k8s.yml
sleep 1
