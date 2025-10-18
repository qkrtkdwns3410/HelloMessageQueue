#!/bin/bash

# RabbitMQ 초기화 스크립트
# 여러 vhost 생성 및 권한 설정

echo "Creating virtual hosts..."

# vhost 생성
rabbitmqctl add_vhost /dev
rabbitmqctl add_vhost /staging
rabbitmqctl add_vhost /prod
rabbitmqctl add_vhost /analytics

echo "Setting permissions for guestuser..."

# 각 vhost에 guestuser 권한 부여 (configure, write, read)
rabbitmqctl set_permissions -p /dev guestuser ".*" ".*" ".*"
rabbitmqctl set_permissions -p /staging guestuser ".*" ".*" ".*"
rabbitmqctl set_permissions -p /prod guestuser ".*" ".*" ".*"
rabbitmqctl set_permissions -p /analytics guestuser ".*" ".*" ".*"

echo "Virtual hosts created successfully!"
