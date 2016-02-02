#!/bin/bash
set -ev

mvn docker:build
docker login -e="$DOCKER_EMAIL" -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
docker push ulock/ulock-server
