#准备开发环境
##安装docker
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
    6.验证
    docker info
    7.登录harbor服务
    docker login firepongo.tech:8288
    admin Adminadmin12
```
