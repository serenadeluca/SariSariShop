FROM tomcat:9.0

RUN sed -i 's/port="8080"/port="80"/' /usr/local/tomcat/conf/server.xml

COPY ./WEB-INF /usr/local/tomcat/webapps/ROOT/WEB-INF
COPY ./META-INF /usr/local/tomcat/webapps/ROOT/META-INF

COPY ./src/main/java /app/src/main/java

RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes && \
    find /app/src/main/java -name "*.java" > /tmp/sources.txt && \
    javac -encoding UTF-8 \
    -cp "/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/*" \
    -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes \
    @/tmp/sources.txt

COPY ./css /usr/local/tomcat/webapps/ROOT/css
COPY ./js /usr/local/tomcat/webapps/ROOT/js
COPY ./img /usr/local/tomcat/webapps/ROOT/img
COPY ./pages /usr/local/tomcat/webapps/ROOT/pages

COPY ./home.jsp /usr/local/tomcat/webapps/ROOT/home.jsp
COPY ./barra_di_ricerca.jsp /usr/local/tomcat/webapps/ROOT/barra_di_ricerca.jsp
COPY ./favicon2.ico /usr/local/tomcat/webapps/ROOT/favicon2.ico

EXPOSE 80

CMD ["catalina.sh", "run"]
