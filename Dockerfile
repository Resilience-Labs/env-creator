FROM openjdk:11-jre-slim-buster

ENV SUFIX SUFIX
ENV SECRETS SECRETS
ENV VARS VARS

COPY . .

ENTRYPOINT ["/entrypoint.sh"]