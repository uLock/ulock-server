FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD log4j2-prod.xml log4j2-prod.xml
ADD ulock-server-0.0.1-SNAPSHOT.war app.war
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dlogging.config='log4j2-prod.xml'","-jar","/app.war"]
