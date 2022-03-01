### Application run procedure

Requirements: Java 11
 - Build application and run unit tests:
   - Windows: `mvnw.cmd clean install`
   - Linux: `mvnw clean install`
   
 - Run:
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux: `mvnw spring-boot:run`
- After application starts you can test the api in following way:
- ENDPOINT TEMPLATE
  ```
  curl --request GET --url http://localhost:8080/routing/{origin}/{destination}
  ```
 - EXAMPLE ENDPOINT DETAILS
   ```
   curl --request GET --url http://localhost:8080/routing/CZE/ITA
   ```
