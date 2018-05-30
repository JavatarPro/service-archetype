FROM openjdk:8-alpine

WORKDIR /service
ENV JAVA_OPTS ""
ENV SERVICE_PARAMS ""
ADD ${subArtifactIdPrefix}-app/target/${subArtifactIdPrefix}-app.jar /service/
CMD java $JAVA_OPTS -jar ${subArtifactIdPrefix}-app.jar $SERVICE_PARAMS