#!/bin/bash

SUFIX=$SUFIX SECRETS=$SECRETS VARS=$VARS java -jar /app.jar
echo $(cat outout)