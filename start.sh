#!/bin/bash

echo "Removing all existing docker containers ..."
result=$(docker ps -aq)

if [[ -n "$result" ]]; then
  docker rm -f $(docker ps -aq)
else
  echo "No containers found ..."
fi

echo "Creating soap-service container ..."

cd soap-service
docker-compose up -d

sleep 5

cd ..

echo "Building actual gateway ..."

cd gateway
mvn clean install

cd ..

echo "Creating gateway container ..."

docker-compose up -d

docker logs -f gateway



