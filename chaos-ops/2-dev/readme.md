基础命令安装+基本目录
```
yum -y install lrzsz unzip zip
cd /usr/local/
mkdir -p nexus/data && chown -R 200 nexus/data
mkdir -p center
```
安装JDK
```
yum search java | grep -i --color JDK
yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel
java -version
vim /etc/profile
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.232.b09-0.el7_7.x86_64
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/jre/lib/dt.jar:$JAVA_HOME/lib/tool.jar
export PATH=$PATH:$JAVA_HOME/bin
source /etc/profile
```
安装docker
```
yum -y install yum-utils  device-mapper-persistent-data  lvm2
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum -y install docker-ce docker-ce-cli containerd.io
systemctl  start docker && systemctl  enable docker  &&  docker -v
vim  /etc/docker/daemon.json
{
    "registry-mirrors": ["https://9b3ogh7r.mirror.aliyuncs.com"],
    "insecure-registries":["firepongo.tech:8288"] 
}
systemctl  restart docker
```
安装docker-compose
```
yum -y install docker-compose  &&  yum install bash-completion && docker-compose -v
```
安装harbor
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
启动portainer
```
docker run -d -p 9000:9000 \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
--name prtainer-test \
docker.io/portainer/portainer
```
安装gitlab-runner
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
推送chaos-admin镜像服务
```
docker pull firepongo.tech:8288/cui/chaos-admin
```
推送dubbo-admin镜像服务
```
docker pull firepongo.tech:8288/cui/dubbo-admin
```
推送xxl-job-admin镜像服务
```
docker pull firepongo.tech:8288/cui/xxljob
```
启动服务
```
打包center.zip
cd /usr/local/
上传并解压center.zip
cd center
docker-compose up -d
```
安装poli
```
 docker run -d -p 6688:6688 --name poli zhonglu/poli:0.11.0
 wget https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.12/mysql-connector-java-8.0.12.jar
 docker cp mysql-connector-java-8.0.18.jar poli:/app/jdbc-drivers
 docker restart poli
 http://firepongo.tech:6688/poli/login
 admin adminadmin
```
安装nodejs环境
```
https://nodejs.org/dist/
tar -xvf node-v10.16.0-linux-x64.tar.xz
cd /usr/local/
mv /var/ftp/pub/node-v10.16.0-linux-64 . 
mv node-v10.16.0.0-linux-64/ nodejs

vi /ect/profile
export PATH=$PATH:/usr/local/nodejs/bin
source /etc/profile

node -v
npm -v

npm install -g pm2
pm2 start ./bin/www
```
