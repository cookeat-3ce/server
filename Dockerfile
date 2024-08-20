FROM tomcat:9.0-jdk11

RUN rm -rf /usr/local/tomcat/webapps/*

WORKDIR /usr/local/tomcat/webapps

COPY target/cookeat-1.0.0-BUILD-SNAPSHOT.war app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
