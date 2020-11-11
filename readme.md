![](https://img.shields.io/badge/language-java-483D8B.svg)
![](https://img.shields.io/badge/language-javascript-483D8B.svg)
![](https://img.shields.io/badge/language-TypeScript-483D8B.svg)
![](https://img.shields.io/badge/license-MIT-483D8B.svg)

# chaos介绍
**chaos**是用来支撑order的模块化解决方案

# 快速开始
## chaos-cloud
包装springcloud

### 构建
```shell script
cd /chaos-cloud
mvn install
mvn deploy
```
### 使用
app服务：定义为不提供Restfull接口的服务
```xml
<dependency>
    <groupId>com.firepongo</groupId>
    <artifactId>chaos-cloud-app</artifactId>
    <exclusions>
</dependency>
```
web服务：定义为提供Restfull接口的服务
```xml
<dependency>
    <groupId>com.firepongo</groupId>
    <artifactId>chaos-cloud-web</artifactId>
    <exclusions>
</dependency>
```
### 集成特性
1. SpringCloud、nacos
2. dubbo
3. openfeign
4. mysql、mybatis-plus
5. redis
6. rabbit
7. lombok、fastjson、hutool、qcloudsms
8. swagger3
9. hibernate-validator
10. sentinel
### 待集成特性
1. Sleuth
2. Seata
3. Arthas
4. Stream
5. Alibaba Cloud ACM
6. Alibaba Cloud SchedulerX
### 架构特性列表  
1. MU,DTO,DATA定义  
2. 全局异常机制  
3. 全局拦截机制  
4. 多入口登录（微信小程序登录，后台登录)  
5. 通用分页模型  
6. 通用结果模型  
7. 通用转换DATA<->MuModel 

## chaos-vue
包装vue
### 构建
```shell script
cd /chaos-vue/chaos-vue
npm run lib
npm publish
cd /chaos-vue/chaos-data
npm publish
```
### 使用
```shell script
npm install chaos-data --save
npm install chaos-vue --save
```
### 集成特性
1. vue
2. vue-cli3
3. vue-router
4. vuex
5. axios
6. element-ui
#### 特性列表  
1. 通用组件Head,CenterHead,Input,Paging,SideMenu  
2. 通用函数fetch,store,Data,show  
3. 通用对象PageData,RuleData  

# 联系我们
# Poweredby 阿暴
![wx](chaos/images/%E6%9C%8D%E5%8A%A1%E6%B2%BB%E7%90%86.jpg)



