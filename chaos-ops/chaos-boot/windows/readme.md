#推送本地镜像服务
##推送chaos-admin镜像服务
```
cd chaos-admin
mvn clean package -Dmaven.test.skip=true
docker build -t chaos-admin .
docker tag chaos-admin firepongo.tech:8288/cui/chaos-admin
docker push firepongo.tech:8288/cui/chaos-admin
```
##推送dubbo-admin镜像服务
```
cd ??
mvn clean package -Dmaven.test.skip=true
docker build -t dubbo-admin .
docker tag dubbo-admin firepongo.tech:8288/cui/dubbo-admin
docker push firepongo.tech:8288/cui/dubbo-admin
```
##推送xxl-job-admin镜像服务
```
cd chaos-third/xxl-job/xxl-job-admin
mvn clean package -Dmaven.test.skip=true
docker build -t xxljob .
docker tag xxljob firepongo.tech:8288/cui/xxljob
docker push firepongo.tech:8288/cui/xxljob
```


