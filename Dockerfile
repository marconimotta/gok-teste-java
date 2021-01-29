FROM openjdk:8-jre-alpine
ADD /target/*.jar template-api.jar
ENV JAVA_OPTS="-Xms128m -Xmx512m -Duser.timezone=America/Sao_Paulo"
ENTRYPOINT exec java $JAVA_OPTS -jar template-api.jar