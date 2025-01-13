FROM eclipse-temurin:21
COPY  ProxyServer.jar   /home/ec2-user/HeadlampJetty/workspace/ProxyServer/ProxyServer.jar
COPY  --from=m2copy *   /home/ec2-user/.m2
COPY  HeadlampJettyCopy /home/ec2-user/HeadlampJetty 
WORKDIR /home/ec2-user/HeadlampJetty/workspace/ProxyServer
COPY  --from=libcopy *  /var/www/html/example.com/public_html/lib    
COPY  --from=jscopy  *  /var/www/html/example.com/public_html/JS/
COPY  --from=jkscopy  headlamp.jks /home/ec2-user/HeadlampJetty/workspace/ProxyServer/headlamp.jks 
COPY  --from=htmlcopy index.html /var/www/html/example.com/public_html/html/index.html 
EXPOSE 80
EXPOSE 443
CMD java --add-opens java.base/java.time=ALL-UNNAMED -jar ProxyServer.jar