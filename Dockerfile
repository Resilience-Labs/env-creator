FROM openjdk:11-jre-slim-buster

COPY . .
RUN ls
ENV SUFIX SUFIX
ENV SECRETS SECRETS
ENV VARS VARS
ENTRYPOINT ["/entrypoint.sh"]