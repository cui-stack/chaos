#  快速开始
1,build chaos-cloud
2,build chaos-gateway
3,部署 chaos-cloud-ops
4,使用 chaos-cloud-code 生成代码
## 组成
chaos-boot-dependencies 包依赖管理
chaos-boot-core 基于springboot,lombok,mysql,mybatis-plus,fastjson,hutool,swagger2,hibernate-validator,qcloudsms,redis+基于MU模型的相关定义
chaos-boot-model 引入dubbo,zookeeper,springboot-admin 开启dubbo
chaos-boot-app 引入rabbit 

## 使用
1.  👣  项目构建
```
cdchaos-boot
mvninstall
```
2.  👣  代码生成
-使用代码生成器
```
cdchaos-boot-code(CodeHelper中配置数据库)
runCodeGenerator.main()
```
-idea输入
```
输入项目名称fo
输入fo_user,fo_product
生成code项目
拷贝代码并使用
```

##  功能列表
```
1. 代码生成,创建MuModel,Service,controller相关服务
2. 通用登录(后台+小程序+客户端)/wxmini/login/manage/login/app/login
3. API登录权限控制@ManageLoginToken@WxminiLoginToken@AppLoginToken
4. API访问量控制@AccessTotalLimit@AccessLimit
5. 后端管理模块chaos-manage,提供平台,管理员,角色,资源管理RestApi
6. chaos-back提供后端管理模块后台,包括在线管理(多环境),日志管理,
7. 全局日志跟踪,后台展示
8. 通用RestApi设计规范,(one,add,list,page,update,delete)
```


# chaos-back 快速使用
## 本地环境
```
数据接口
本地环境
http://localhost:38088/manage/login
开发环境
https://dev.firepongo.tech/manage/login
测试环境
https://test.firepongo.tech/manage/login
生产环境
https://firepongo.tech/manage/login

可以修改host,或者私有部署修改目标域名
```


