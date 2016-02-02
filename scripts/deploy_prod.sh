#!/bin/bash
set -ev

mvn docker:build
docker login -e="$DOCKER_EMAIL" -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
docker push ulock/ulock-server

:'
ssh -tt $REMOTE_USERNAME@$REMOTE_HOST << EOF
docker pull ulock/ulock-server
eval "$(weave env)"
docker stop ulock-server || true
docker rm ulock-server || true
docker run -d --name ulock-server -e DB_PASSWORD=$DB_PASSWORD ulock/ulock-server
exit
EOF
'

echo 'done'
