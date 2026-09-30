FROM tomcat:9.0

# Copia il contenuto della tua webapp dentro Tomcat
COPY ./ /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080

CMD ["catalina.sh", "run"]
