![](https://img.shields.io/badge/language-java-orange.svg)
# [chaos-boot](https://github.com/cui-stack/chaos/)
##框架集成
|  服务     | 使用技术                 |   进度        |    备注   |
|----------|-------------------------|---------------|-----------|
|  基础框架 | springboot              |   ✅          |           |
|  服务调用 | dubbo                   |   ✅          |           |
|  注册中心 | zookeeper               |   ✅          |           |
|  数据映射 | mybatisplus             |   ✅          |           |
|  数据缓存 | redis                   |   ✅          |           |
|  Api管理  | swagger2                |   ✅          |           |
|  配置中心 | nacos                   |   🏗          |           |
|  消息总线 | rabbitmq                |   ✅          |           |
|  授权认证 | Spring Security         |   ✅          |Jwt+redis  |
|  任务调度 | xxl-Job                 |   ✅          |           |
|  对象存储 | OSS                     |   🏗          |           |
|  灰度分流 | nginx                   |   🏗          |           |
|  服务容错 | SpringCloud Sentinel    |   🏗          |           |
|  分库分表 | shading-jdbc            |   🏗          |           |
##监控告警
|  服务     | 使用技术                 |   进度        |    备注   |
|----------|-------------------------|---------------|-----------|
|  基础框架 | actuator            |   ✅          | 添加生产准备特性     |
|  服务监控 | springboot-admin    |   ✅          |                     |
|  监控告警 | prometheus          |   🏗          |开源监控告警解决方案   |
|  数据可视 | grafana             |   🏗          |大规模指标数据的可视化 |
|  链路追踪 | zipkin              |   ✅          |链路追踪              |
##使用
1. pom.xml引入
```
 <parent>
        <groupId>com.cui.tech</groupId>
        <artifactId>chaos-parent</artifactId>
        <version>2.1.0-SNAPSHOT</version>
        <relativePath/>
 </parent>
 <dependencies>
    <dependency>
        <groupId>com.cui.tech</groupId>
        <artifactId>chaos-spring-boot-starter</artifactId>
    </dependency>
 </dependencies>
```
2. 代码生成
```
chaos-code run CodeGenerator
输入 firepongo 
输入 fo_user,fo_product
```
3. 展示
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/1.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/2.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/3.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/5.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/6.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/8.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/9.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/10.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/11.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/12.jpg)
![avatar](https://gitee.com/c-stack/chaos/blob/master/chaos-boot/chaos-capture/13.jpg)




