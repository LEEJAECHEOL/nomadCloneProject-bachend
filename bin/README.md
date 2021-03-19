# nomad Clone Project

### 1. 의존성
- Spring Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- Spring Web
- com.auth0 (3.10.3)

```xml
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.10.3</version>
</dependency>
```

### 2. DB 생성
```spl
create user 'nomad'@'%' identified by '1234';
GRANT ALL PRIVILEGES ON *.* TO 'nomad'@'%';
create database nomad;
use nomad;
```

### 3. application.yml 세팅
```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true
      
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/nomad?serverTimezone=Asia/Seoul
    username: nomad
    password: 1234

  jpa:
    hibernate:
      ddl-auto: create #create update none
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true

```