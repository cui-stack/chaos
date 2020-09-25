#chaos-ops基于docker的运维架构
##框架集成
|架构|介绍|备注|
|---|---|---|
|window|docker环境||
|linux|docker环境||
|chaos-vue|后台前端|/back|
|chaos-center|研发中台|/manage|


#基于阿里云ecs的运维部署方案
##服务矩阵
|服务|使用技术|进度|环境|方式|版本|
|---|---|---|---|-----------|-----------|
|容器管理|portainer|✅|center|docker|1.6.29|
|代码仓库|gitlab|✅|center|docker||
|构件仓库|nexus|✅|center|compose|3|
|镜像仓库|harbor|✅|center|compose|1.9.4|
|CI执行器|gitlab-runner|✅|center|docker||
|注册中心|zookeeper|✅|center/common|compose||
|数据库|mysql|✅|center/common|compose||
|KV数据库|redis|✅|center/common|compose||
|消息总线|rabbitmq|✅|center/common|compose||
|任务管理|xxljob|✅|center/common|compose||
|链路追踪|zipkin|✅|center/common|compose||
|RPC服务|dubbo-admin|✅|center/common|compose||
|微服务管理|chaos-admin|✅|center/common|compose||
|代理服务|nginx|✅|center/common|compose||
##使用

