FROM tomcat:7.0

# target .war to tomcat container
COPY ./Builds/* /usr/local/tomcat/webapps/
