![](https://img.shields.io/badge/language-java-483D8B.svg)
![](https://img.shields.io/badge/language-javascript-483D8B.svg)
![](https://img.shields.io/badge/language-TypeScript-483D8B.svg)
![](https://img.shields.io/badge/license-MIT-483D8B.svg)

# 架构简介
## chaos是什么？   
chaos是一个通用型互联网软件架构，chaos是一个全栈快速开发架构；通过chaos可以快速搭建一个互联网应用      

## chaos的应用？   
chaos适用于较小用户量（千万级用户以下）的互联网场景的软件开发（包括并不限于APP，小程序，管理后台）     

## chaos的愿景？    
chaos希望通过整合互联网优秀的框架，约定协议，整合第三方服务，提供基础软件架构，提升研发效率和质量                

## chaos的组成？    
chaos-cloud是基于SpringCloud Alibaba，nacos，dubbo，openfeign等框架的服务端架构     
chaos-vue是基于VUE，vuex，elementUI的前端架构       
chaos-weapp是基于微信原生，vant的微信小程序架构

## chaos的能力？ 
chaos-admin是基于chaos架构和设计理念开发的管理系统（提供管理员，角色，权限，统一登录，日志等等功能）      
chaos-cloud提供了通用登录服务，API鉴权等等功能    
chaos-vue提供了通用后台UI模块，通用请求模块等等功能    
chaos-weapp提供了基础的微信原生小程序模板

# 快速开始
## 知识储备
本架构面向于全栈开发，语言主要使用java，javaScript，使用架构前需要了解：
1. 使用IDEA，vscode，git完成项目开发    
2. 使用maven，npm完成代码组件构建
3. 了解SpringCloud，VUE项目构建方式
4. 了解docker使用（可选）   

## 构建服务
1.  安装服务端基础模块 
```   
在/chaos/chaos-cloud-base/chaos-cloud-dependencies/目录执行 mvn install
在/chaos/目录执行  mvn install
打开插件 enable lombook 
```
2. 安装mysql，redis，nacos，nginx服务   
```
默认地址:127.0.0.1
执行数据库脚本 /chaos/chaos-cloud/resource/init_chaos.sql
启动nacos ./bin/startup.sh -m standalone
```
3. 启动chaos-admin-service
```  
启动AdminApplication.main()
```
4. 启动chaos-admin-back
```
在/chaos/chaos-admin/chaos-admin-back执行
npm install --registry=http://www.firepongo.tech:4873
npm run dev
访问127.0.0.1:8080可访问管理后台，默认账号密码（admin/admin123）   
```
5. 启动chaos-service
```
执行ChaosApplication.main()
```
6. 启动chaos-manage
```
执行ChaosManageApplication.main()
```
7. 启动chaos-client
```
执行ChaosClientApplication.main()
```
8. 启动chaos-vue
```
在/chaos/chaos-vue执行
npm install
npm run dev
访问127.0.0.1:8080可访问管理后台，默认账号密码（admina/admin123） （同时启动需要注意端口！）  
```
9.启动chaos-weapp（原生微信小程序）
```
在https://mp.weixin.qq.com/  微信公众平台-小程序-开发-开发管理-开发设置-开发者ID-获取（appid，secret）
下载https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html
在chaos/chaos-cloud/chaos-client application-base.yml 配置（appid，secret）
执行ChaosClientApplication.main()
在微信开发者工具导入chaos/chaos-weapp，构建打开（默认关闭了登录功能）
在chaos/chaos-weapp执行 npm install
在微信开发者工具打开‘使用npm模块’，并构建npm
```
## 功能截图
![wx](image/1.png)
![wx](image/3.png)
![wx](image/4.png)
![wx](image/5.png)

# 项目实战
## 开发流程
chaos架构约定了数据库表的设计和API的设计，通常完成一个项目包含以下几个步骤
```
1. 完成数据库设计，/chaos/chaos-cloud/resource/init_mu_table.sql提供了默认建表语句
2. 使用代码生成器chaos-cloud-app生成服务端代码
   在chaos/chaos-cloud-base/chaos-cloud-code
   修改CodeHelper.initDataSource()中数据库配置
   执行CodeGenerator.main()
   输入模块名: chaos 输入表名 chaos_user
   默认输出路径C:\Work\projects\code
   搬运代码:
      code\src\main\java\com\firepongo\chaos\api ->chaos-model
      code\src\main\java\com\firepongo\chaos\service ->chaos-service
      code\src\main\java\com\firepongo\chaos\client ->chaos-client
      code\src\main\java\com\firepongo\chaos\manage ->chaos-manage
3. 使用代码生成器生成前端页面代码
   在chaos-vue下执行npm run tep user
   默认输出目录/chaos/chaos-vue/src/views/
   添加路由/chaos/chaos-vue/router/index.js  
   {
      path: '/user',
      meta: {title: "用户列表"},
      component: () => import('@/views/user/index')
   }
4. 在chaos-admin-back添加资源和给角色授权页面，详情如下图
5. 重新登录chao-vue后台
```
![wx](image/chaos-vue-1.png)
![wx](image/chaos-vue-2.png)
![wx](image/chaos-vue-3.png)

## 生产部署
```
脚本目录:/chaos/chaos-cloud/resource/
docker-compose.yml 安装redis，nacos，nginx
nginx.conf 默认nginx配置
```

# 架构细节
## chaos-cloud
chaos-cloud是基于SpringCloud Alibaba，nacos的微服务架构，提供dubbo，feign两种服务调用
### 已集成特性
1. SpringCloud Alibaba、nacos
2. dubbo
3. openfeign
4. mysql、mybatis-plus
5. redis
6. rabbit
7. lombok、jackson、hutool、qcloudsms
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

### 架构特性
1. MU,DTO,DATA定义  
2. 全局异常机制  
3. 全局拦截机制  
4. 多入口登录（微信小程序登录，后台登录)  
5. 通用分页模型  
6. 通用结果模型  
7. 通用转换DATA<->MuModel 

## chaos-vue
chaos是基于VUE的spa架构
### 已集成特性
1. vue
2. vue-cli3
3. vue-router
4. vuex
5. axios
6. element-ui
7. normalize
8. less

## chaos-weapp
1. 微信原生
2. vant

### 架构特性
1. 通用函数fetch、store、Data、show  

# 设计理念 
## 后端的设计和规范：  
1. 数据库使用mysql。   
2. 数据库表默认需要id、mu、create_time，modify_time，is_delete，version字段。   
3. 服务端代码结构包括：model（实体和接口定义），service（服务实现），manage（后台接口），client（前台接口）。  
4. 实体定义：MuModel对应数据库表结构、Data对应协议结构、Data默认等同于排除默认字段的MuModel结构。   
5. 服务间提供dubbo、feign两种调用方式，service间使用dubbo调用，web层（manage，client）使用feign调用。    
6. service处理事务，包括本地事务和分布式事务（通过seata，待补充支持）。  
7. web层默认提供Restful服务（只使用post），manage服务提供add，delete，update，one，list，page；client提供提供one，list，page。   
8. 对于基础业务可以通过chaos-cloud-code代码快速生成（通过代码生成可以节约88%的开发时间）。  

## 前端的设计和规范  
1. 前端fetch处理协议级业务，包括http code逻辑，token逻辑，lastPost逻辑。  
2. 提供Data.js处理后端服务接口包括（add，remove，update，one，list，page）和（search，query，submit）。    
3. 提供PageData.js，定义页面数据。    
4. 使用vuex处理全局数据，提供admin，app模块。    
5. 使用mixin，提供page分页的通用mixin。   
6. 基于elementUI，提供通用组件，包括paging，sideMenu，等等。  
7. 对于通用页面可以通过`npm run tmp`创建（通过代码生成可以节约88%的开发时间）。

# 联系我们
![wx](image/wx_mine_486x629.jpg)
## QQ群号：1067845715 

# Poweredby 阿暴@[火猩科技](https://firepongo.gitee.io/)



