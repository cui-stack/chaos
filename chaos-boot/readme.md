![](https://img.shields.io/badge/language-java-orange.svg)
# [chaos-boot](https://github.com/cui-stack/chaos/)
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

## 使用

1. 代码生成
```shell script
chaos-code run CodeGenerator
输入 firepongo 
输入 fo_user,fo_product
```
2. 使用chaos架构
```
 <parent>
         <groupId>com.cui.tech</groupId>
         <artifactId>chaos-paren</artifactId>
         <version>2.2.0-SNAPSHOT</version>
         <relativePath/>
 </parent>
 <dependencies>
    <dependency>
        <groupId>com.cui.tech</groupId>
        <artifactId>chaos-boot-starter</artifactId>
    </dependency>
 </dependencies>
 
在Application添加
@MapperScan({"com.cui.tech.iya.service.mapper", "com.cui.tech.manage.service.mapper"})
@ComponentScan(basePackages = {"com.cui.tech.chaos", "com.cui.tech.firepongo", "com.cui.tech.manage"})

```

3. 添加后台登录
```java
@Component("mnLoginService")
public class ManageLoginService extends ManageLoginServiceImpl {
    @Override
    public ManageLoginUser getUserInfo(ManageLoginDto loginDto) {}
    @Override
    public void afterLogin(LoginUser loginUser) {}
}

```
4. 添加微信小程序登录
```
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
5. 使用混沌后台
```
    <dependency>
         <groupId>com.cui.tech</groupId>
         <artifactId>chaos-manage</artifactId>
         <version>0.0.2-SNAPSHOT</version>
    </dependency>

    在Application添加
    @ComponentScan(basePackages = {"com.cui.tech.manage"})

    cd chaos-back 
    npm run dev
```
6. 使用研发中台
```
    cd chaos-center
    npm run dev
```
