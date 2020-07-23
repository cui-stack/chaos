#中台环境部署
##配置域名
``` 
firepongo.tech
```
##
```shell script
cd /usr/local/
mkdir -p nexus/data && chown -R 200 nexus/data
mkdir -p center
```
##安装harbor
```
cd /usr/local/center
https://github.com/goharbor/harbor/releases (坚果云)
tar -zxvf  harbor-offline-installer-v1.10.2.tgz
cd harbor
vim harbor.yml
    hostname: center.okyakid.com
    http:port 8098
    harbor_admin_password:
注释https
./prepare
./install.sh
docker-compose up -d
http://center.okyakid.com:8098/
```
##启动portainer
```
docker run -d -p 9000:9000 \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
--name prtainer-test \
docker.io/portainer/portainer
```
##安装gitlab-runner
```
sudo docker run -d --name gitlab-runner --restart always   \
  -v /srv/gitlab-runner/config:/etc/gitlab-runner    \
  -v /var/run/docker.sock:/var/run/docker.sock   \
  -v /root/.m2:/root/.m2                   \
  -v /usr/bin/docker:/usr/bin/docker        \
  gitlab/gitlab-runner:latest

sudo docker exec -it gitlab-runner  gitlab-runner register 
    http://git.okyakid.com/
    ek6g2xz8LgTRTCTug4hG
    maven,docker
    docker
    docker:19.03.1
sudo vim /srv/gitlab-runner/config/config.toml
    privileged = true
    volumes = ["/cache","/certs/client","/root/.m2:/root/.m2","/var/run/docker.sock:/var/run/docker.sock"]

docker restart gitlab-runner
```
##安装poli
```
 docker run -d -p 6688:6688 --name poli zhonglu/poli:0.11.0
 wget https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.12/mysql-connector-java-8.0.12.jar
 docker cp mysql-connector-java-8.0.18.jar poli:/app/jdbc-drivers
 docker restart poli
 http://firepongo.tech:6688/poli/login
 admin adminadmin
```
##拉取chaos-admin镜像服务
```
docker pull firepongo.tech:8288/cui/chaos-admin
```
##拉取dubbo-admin镜像服务
```
docker pull firepongo.tech:8288/cui/dubbo-admin
```
##推送xxl-job-admin镜像服务
```
docker pull firepongo.tech:8288/cui/xxljob
```

##启动服务
```
打包center.zip
cd /usr/local/
上传并解压center.zip
cd center
docker-compose up -d
```
