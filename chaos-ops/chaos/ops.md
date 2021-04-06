1.  执行 
```   
在/chaos/chaos-cloud-base/chaos-cloud-dependencies/目录执行 mvn install
在/chaos/目录执行  mvn install
```
2. 安装mysql，redis，nacos，nginx服务   
```
默认地址:127.0.0.1
执行数据库脚本 /chaos/chaos-cloud/resource/init_chaos.sql
启动nacos ./bin/startup.sh -m standalone
