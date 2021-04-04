#! /usr/bin/env bash
docker-compose -f postgresql.yaml up -d
sleep 10
docker-compose -f backend.yaml up -d
