FROM java:8
VOLUME /tmp
EXPOSE 8080
RUN mkdir /conf
ADD log4j2-prod.xml /conf/log4j2-prod.xml
ADD ulock-server-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dlogging.config='/conf/log4j2-prod.xml'","-jar","/app.war"]
