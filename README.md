# cas-oauth2-example

#### 项目介绍

​	这是一个 CAS 开启 OAuth2 支持的例子。

#### 运行

#####1、JRE信任自签名证书
```bash
cd cas-server/src/main/resources
keytool -exportcert -alias cas -keystore cas.keystore -storepass 123456 -rfc -file ./cas.cer
keytool -import -v -trustcacerts -alias cas -file cas.cer -keystore $JAVA_HOME/jre/lib/security/cacerts -keypass 123456 -storepass changeit
```
##### 2、启动 cas-server

`mvn clean package`

`java -jar cas-server/target/cas.war`

##### 3、启动 cas-client

`mvn clean package spring-boot:run`

