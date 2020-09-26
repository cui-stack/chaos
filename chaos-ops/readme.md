# chaos-ops基于docker的运维架构
## 框架集成

|环境|介绍|备注|
|---|---|---|
|linux|docker/compose/k8s||

## 服务矩阵

|服务|使用技术|方式|版本|进度|备注|
|---|---|-----------|-----------|-----------|-----------|
|容器管理|portainer|docker|1.6.29|✅||
|代码仓库|gitlab/gitee|docker||✅||
|项目构建|jenkins|tar|2.249.1|✅||
|构件仓库|nexus|docker|3.27.0-03|✅||
|镜像仓库|harbor|compose|1.9.4|✅||
|CI执行器|gitlab-runner|docker||✅||
|注册中心|zookeeper|compose||✅||
|数据库|mysql|cloud||✅||
|KV数据库|redis|compose||✅||
|消息总线|rabbitmq|compose||✅||
|任务管理|xxljob|?||✅||
|链路追踪|zipkin|compose||✅||
|RPC服务|dubbo-admin|compose||✅||
|微服务管理|chaos-admin|compose||✅||
|代理服务|nginx|compose||✅||
|日志数据|kibana|docker|7.6.2|✅||
|搜索引擎|elasticsearch|docker|7.6.2|✅||
|数据报表|poli|docker||✅||
|配置中心|nacos|docker||✅||
