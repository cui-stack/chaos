安装docker
[下载]()DockerToolbox-18.03.0-ce.exe
```
1. 启动 Docker Quickstart Terminal
2. 断网 boot2docker.iso C:\Users\cjj_a\.docker\machine\cache
3. 配置
    1.启动 Docker Quickstart
    2.进入default   
    docker-machine ssh default
    3.切换到root 用户
    sudo -i
    4.修改文件 /var/lib/boot2docker/profile 文件中的 EXTRA_ARGS 信息，向其中加入对应的访问路径
    vi /var/lib/boot2docker/profile
        --insecure-registry firepongo.tech:8288
        (harbor访问地址)
    5.配置加速
    sudo sed -i "s|EXTRA_ARGS='|EXTRA_ARGS='--registry-mirror=https://9b3ogh7r.mirror.aliyuncs.com |g" /var/lib/boot2docker/profile
    exit exit
    docker-machine restart

docker info
```
登录harbor服务
```
docker login firepongo.tech:8288
admin Adminadmin12
```
推送chaos-admin镜像服务
```
cd chaos-admin
mvn clean package -Dmaven.test.skip=true
docker build -t chaos-admin .
docker tag chaos-admin firepongo.tech:8288/cui/chaos-admin
docker push firepongo.tech:8288/cui/chaos-admin
```
推送dubbo-admin镜像服务
```
cd ??
mvn clean package -Dmaven.test.skip=true
docker build -t dubbo-admin .
docker tag dubbo-admin firepongo.tech:8288/cui/dubbo-admin
docker push firepongo.tech:8288/cui/dubbo-admin
```
推送xxl-job-admin镜像服务
```
cd chaos-third/xxl-job/xxl-job-admin
docker build -t xxljob .
docker tag xxljob firepongo.tech:8288/cui/xxljob
docker push firepongo.tech:8288/cui/xxljob
```


