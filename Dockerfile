FROM ubuntu:latest
LABEL authors="fnac"

ENTRYPOINT ["top", "-b"]