#!/bin/bash
set -ev

mvn docker:build
docker login --username=$DOCKER_USER --password=$DOCKER_PASSWORD --email=$DOCKER_EMAIL
docker push ulock/ulock-server
