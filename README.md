# cas-oauth2-example

#### 项目介绍

​	这是一个 CAS 5开启 OAuth2 支持的例子。

#### 准备
依赖项目 [spring-boot-shiro](https://github.com/johntostring/spring-boot-shiro) 未发布到Maven中央仓库，需先行install到本地。
```bash
cd cas-server/src/main/resources

keytool -exportcert -alias cas -keystore cas.keystore -storepass 123456 -rfc -file ./cas.cer

keytool -import -v -trustcacerts -alias cas -file cas.cer -keystore $JAVA_HOME/jre/lib/security/cacerts -keypass 123456 -storepass changeit
```

#### 添加 hosts
```
127.0.0.1	cas.example.org
```

#### 启动 cas-server

客户端服务注册使用JSON方式注册，见cas-server/src/main/resources/services下json。

`mvn clean package`

`java -jar cas-server/target/cas.war`

访问 https://cas.example.org:8443

#### 启动 cas-client

`mvn clean package spring-boot:run`

访问 `http://localhost:9000/ping` 会跳转CAS OAuth2认证流程
访问 `http://localhost:9000/foo` 无需认证
访问 `http://localhost:9000/logout` 退出

