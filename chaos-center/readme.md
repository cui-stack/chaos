### 5. 使用混沌后台
#### 5.1 pom.xml
```xml
<dependency>
    <groupId>com.cui.tech</groupId>
    <artifactId>chaos-manage</artifactId>
    <version>0.0.2-SNAPSHOT</version>
</dependency>
```
#### 5.2 Application.java
```java
@ComponentScan(basePackages = {"com.cui.tech.chaos"})
```
#### 5.3 使用chaos-manage-back
```shell script
cd chaos-manage-back
npm run dev
```
### 6. 使用研发中台
```shell script
cd chaos-boot-center
npm run dev
```
