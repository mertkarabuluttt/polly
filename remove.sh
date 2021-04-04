#! /usr/bin/env bash
docker-compose down
docker volume prune
docker network prune
docker rmi -f polly_backend

