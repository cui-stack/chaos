![](https://img.shields.io/badge/language-java-483D8B.svg)
![](https://img.shields.io/badge/language-javascript-483D8B.svg)
![](https://img.shields.io/badge/language-TypeScript-483D8B.svg)
![](https://img.shields.io/badge/license-MIT-483D8B.svg)

#  简介
    chaos是一个前后端分离的快速开发架构，chaos架构包括服务端架构、中后台架构，微信原生小程序架构等部分组成。   
    1.服务端架构chaos-cloud（java）基于SpringCloudAlibaba、dubbo、nacos、MybatisPlus、jwt、redis、Swagger2等组成。
    2.中后台架构chaos-umi（TypeScript）基于react（hooks）、umi、AntDesignPro
    3.中后台架构chaos-vue（javascript）基于vue2、ElementUI、axios
    4.微信原生小程序架构chaos-weapp（javascript）基于vant

#  特性
##  chaos-cloud
    1.通用登录服务（ManageLogin\wxminiLogi\AppLogin）  
    2.API鉴权和拦截

##  chaos-admin
    1.平台、管理员、角色、资源等的管理功能
    2.日志收集和展示

##  chaos-vue/chaos-umi
    1.登录
    2.
##  chaos-weapp
    ？ 

# 快速开始
    本架构面向于全栈开发，使用前需要了解以下知识
    1.IDEA、vscode、git、lombook   
    2.maven、npm  
    3.linux、docker   

## 构建
    1.安装开发环境（windows）
      执行chaos-ops下startup.cmd
    2.安装服务器环境(linux)
      执行chaos-ops下startup.csh（修改为.sh文件）
    3.启动chaos-admin-service
    4.启动chaos-admin-back
      访问127.0.0.1:8080；默认账号密码（admin/admin123）   
    5.启动chaos-service
    6.启动chaos-manage
    7.启动chaos-client
    8.启动chaos-vue/chaos-umi  
      访问127.0.0.1:8080可访问管理后台，默认账号密码（admina/admin123）（同时启动需要注意端口！）  
    9.启动chaos-weapp（原生微信小程序）
    
## 功能截图
![wx](image/1.png)
![wx](image/3.png)
![wx](image/4.png)
![wx](image/5.png)

## 开发流程
    chaos架构约定了数据库表的设计和API的设计，通常完成一个项目包含以下几个步骤
    1.完成数据库设计，/chaos/chaos-ops/init_mu_table.sql提供了默认建表语句
    2.使用代码生成器chaos-cloud-code生成服务端代码
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
    3.使用代码生成器生成前端页面代码
      在chaos-vue下执行npm run tep user
      默认输出目录/chaos/chaos-vue/src/views/
      添加路由/chaos/chaos-vue/router/index.js  
      {
         path: '/user',
         meta: {title: "用户列表"},
         component: () => import('@/views/user/index')
      }
    4.在chaos-admin-back添加资源和给角色授权页面，详情如下
    
![wx](image/chaos-vue-1.png)
![wx](image/chaos-vue-2.png)
![wx](image/chaos-vue-3.png)
      
    5.登录chao-vue后台

## 生产部署
    脚本目录:/chaos/chaos-ops/deploy/
    docker-compose.yml 安装redis，nacos，nginx
    nginx.conf 默认nginx配置

# 设计理念 
## 后端的设计和规范：  
    1.数据库使用mysql。   
    2.数据库表默认需要id、mu、create_time，modify_time，is_delete，version字段。   
    3.服务端代码结构包括：model（实体和接口定义），service（服务实现），manage（后台接口），client（前台接口）。  
    4.实体定义：MuModel对应数据库表结构、Data对应协议结构、Data默认等同于排除默认字段的MuModel结构。   
    5.服务间提供dubbo、feign两种调用方式，service间使用dubbo调用，web层（manage，client）使用feign调用。    
    6.service处理事务，包括本地事务和分布式事务（通过seata，待补充支持）。  
    7.web层默认提供Restful服务（只使用post），manage服务提供add，delete，update，one，list，page；client提供提供one，list，page。   
    8.对于基础业务可以通过chaos-cloud-code代码快速生成（通过代码生成可以节约88%的开发时间）。  

## 前端的设计和规范  
    1.前端fetch处理协议级业务，包括http code逻辑，token逻辑，lastPost逻辑。  
    2.提供Data.js处理后端服务接口包括（add，remove，update，one，list，page）和（search，query，submit）。    
    3.提供PageData.js，定义页面数据。    
    4.使用vuex处理全局数据，提供admin，app模块。??    
    5.使用mixin，提供page分页的通用mixin。??   
    6.对于通用页面可以通过npm run tmp创建（通过代码生成可以节约88%的开发时间）。

# 联系我们
![wx](image/wx_mine_486x629.jpg)
## QQ群号：1067845715 

# Poweredby 阿暴@[火猩科技](https://firepongo.gitee.io/)



