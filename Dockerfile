FROM openjdk:21
EXPOSE 9002
# ADD target/*.jar *.jar
#ENTRYPOINT ["sh", "-c", "java -jar /*.jar"]
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.brihaspathee.zeus.PlanCatalogApplication"]