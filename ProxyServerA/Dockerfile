FROM eclipse-temurin:latest
COPY  ProxyServer.jar           /home/ec2-user/HeadlampJetty/workspace/ProxyServer/ProxyServer.jar
COPY  --from=awscredcontext config   /root/.aws/config
COPY  --from=awscredcontext credentials   /root/.aws/credentials
COPY  --from=m2context * /home/ec2-user/.m2
COPY  --from=headlampjettycontext . /home/ec2-user/HeadlampJetty
WORKDIR /home/ec2-user/HeadlampJetty/workspace/ProxyServer
COPY  --from=libcontext *  /var/www/html/example.com/public_html/lib    
COPY  --from=jscontext  *  /var/www/html/example.com/public_html/JS/
COPY  --from=jkscontext  headlamp.jks /home/ec2-user/HeadlampJetty/workspace/ProxyServer/headlamp.jks 
COPY  --from=indexcontext index.html /var/www/html/example.com/public_html/html/index.html 
EXPOSE 80
EXPOSE 443
EXPOSE 3306
CMD ["java", "--add-opens", "java.base/java.time=ALL-UNNAMED", "-jar", "ProxyServer.jar"]