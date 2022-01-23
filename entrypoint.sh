#!/bin/bash

SUFIX=$SUFIX SECRETS=$SECRETS VARS=$VARS java -jar /app.jar
echo "::set-output name=env_output::$(cat output)"