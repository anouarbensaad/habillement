FROM tomcat:7.0

# target .war to tomcat container
COPY ./ma-gpro-1.0.1.0-SNAPSHOT.war /usr/local/tomcat/webapps/