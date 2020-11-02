# 混沌中台
## manage
+ 提供后台登录服务
+ 提供后台用户管理管理功能
+ 提供日志跟踪服务
+ 提供基于RBCA用户-角色-资源的简单实现
+ 提供中台相关API实现

### 使用
    <dependency>
        <groupId>com.cui.tech</groupId>
        <artifactId>chaos-manage</artifactId>
        <version>0.0.2-SNAPSHOT</version>
    </dependency>
    
    cd chaos-manage-back
    npm run build | dev

## 自定义镜像
    cd chaos-admin
    mvn clean package -Dmaven.test.skip=true
    docker build -t chaos-admin .
    docker tag chaos-admin firepongo.tech:8288/cui/chaos-admin
    docker push firepongo.tech:8288/cui/chaos-admin

