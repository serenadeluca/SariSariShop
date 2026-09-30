FROM tomcat:9.0

# Copia tutta la webapp nella ROOT di Tomcat
COPY ./WEB-INF /usr/local/tomcat/webapps/ROOT/WEB-INF
COPY ./META-INF /usr/local/tomcat/webapps/ROOT/META-INF
COPY ./css /usr/local/tomcat/webapps/ROOT/css
COPY ./js /usr/local/tomcat/webapps/ROOT/js
COPY ./img /usr/local/tomcat/webapps/ROOT/img
COPY ./pages /usr/local/tomcat/webapps/ROOT/pages

COPY ./home.jsp /usr/local/tomcat/webapps/ROOT/home.jsp
COPY ./barra_di_ricerca.jsp /usr/local/tomcat/webapps/ROOT/barra_di_ricerca.jsp
COPY ./favicon2.ico /usr/local/tomcat/webapps/ROOT/favicon2.ico

EXPOSE 80

CMD ["catalina.sh", "run"]
