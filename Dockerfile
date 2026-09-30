FROM tomcat:9.0

# Copia TUTTI i file della tua webapp dentro ROOT
COPY ./ /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080

CMD ["catalina.sh", "run"]
