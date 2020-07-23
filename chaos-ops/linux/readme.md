#linux 
##基础命令安装+基本目录
```
yum -y install lrzsz unzip zip
```
##安装JDK
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
##安装docker
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
##安装docker-compose
```
yum -y install docker-compose  &&  yum install bash-completion && docker-compose -v
```
