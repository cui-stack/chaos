![](https://img.shields.io/badge/language-java-wheat.svg)
# chaos-boot 基于springboot+dubbo的服务端架构
## 框架集成
|  服务     | 使用技术                    |   进度        |    备注   |
|----------|------------------------------|--------------|-----------|
|  基础框架 | springboot                  |   ✅          |           |
|  服务调用 | dubbo                       |   ✅          |           |
|  注册中心 | zookeeper                   |   ✅          |           |
|  数据映射 | mybatisplus                 |   ✅          |           |
|  数据缓存 | redis                       |   ✅          |           |
|  Api管理  | swagger2                    |   ✅          |           |
|  消息总线 | rabbitmq                    |   ✅          |           |
|  授权认证 | SpringSecurity/Jwt+redis    |   ✅          |           |
|  任务调度 | xxl-Job                     |   ✅          |           |
|  灰度分流 | nginx                       |   ✅          |           |
|  基础框架 | actuator                    |   ✅          |           |
|  服务监控 | springboot-admin            |   ✅          |           |
|  链路追踪 | zipkin                      |   ✅          |           |
|  配置中心 | nacos                       |   🏗          |           |
|  服务容错 | SpringCloud Sentinel        |   🏗          |           |
|  分库分表 | shading-jdbc                |   🏗          |           |
|  监控告警 | prometheus                  |   🏗          |           |
|  数据可视 | grafana                     |   🏗          |           |

## 快速开始
### 1. 建域
```
firepongo.tech
```
### 2. 环境部署
```shell script
cd ./chaos-ops/chaos-boot/chaos-center
docker-compose
```
### 3. 创建项目firepongo
```shell script
cd chaos-boot-code 
run CodeGenerator.main()
input firepongo 
input fo_user,fo_product
```
### 3.1 pom.xml
```xml
<project>
    <parent>
         <groupId>com.cui.tech</groupId>
         <artifactId>chaos-boot-parent</artifactId>
         <version>2.3.0-SNAPSHOT</version>
         <relativePath/>
    </parent>
    <groupId>com.cui.tech</groupId>
    <artifactId>firepongo-service</artifactId>
    <version>0.0.2-SNAPSHOT</version>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>com.cui.tech</groupId>
            <artifactId>chaos-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```
### 3.1.Application.java
```java
@MapperScan({"com.cui.tech.firepongo.service.mapper", "com.cui.tech.chaos.manage.service.mapper"})
@ComponentScan(basePackages = {"com.cui.tech.chaos", "com.cui.tech.firepongo"})
```
### 4. 组件
#### 4.1. 微信小程序登录
```java
@Component("wxLoginService")
public class WxminiLoginService extends WxminiLoginServiceImpl {
    @Override
    public ManageLoginUser getUserInfo(ManageLoginDto loginDto) {}
    @Override
    public void afterLogin(LoginUser loginUser) {}
    @Override
    protected WxMiniLoginUser initUser(WxMiniLoginDto loginDto) {}
}
```
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
