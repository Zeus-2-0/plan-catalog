#!/bin/bash
## Remove existing docker images
docker image rm zeusprogetto/plan-catalog
mvn clean package
docker build -t zeusprogetto/plan-catalog:latest .
docker push zeusprogetto/plan-catalog