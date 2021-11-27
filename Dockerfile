FROM tomcat:jdk11-adoptopenjdk-hotspot
COPY /target/testapp-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/testapp.war
EXPOSE 8080 8888