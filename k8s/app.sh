#!/usr/bin/env bash

kubectl apply -f ./config-server-k8s.yml
sleep 1
kubectl apply -f ./client-k8s.yml
sleep 1
kubectl apply -f ./product-k8s.yml
sleep 1
kubectl apply -f ./bid-k8s.yml
sleep 1
kubectl apply -f ./payment-k8s.yml
sleep 1
kubectl apply -f ./notification-k8s.yml
sleep 1
kubectl apply -f ./env/nginx-k8s.yml
sleep 1
