# linux centos7 安装

## 基础命令
    yum -y install lrzsz unzip zip

## JDK
    yum search java | grep -i --color JDK
    安装jdk8
    yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel
    java -version
    vim /etc/profile
    
    export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
    export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/jre/lib/dt.jar:$JAVA_HOME/lib/tool.jar
    export PATH=$PATH:$JAVA_HOME/bin
    
    source /etc/profile
    安装jdk11
    yum install java-11-openjdk -y
    export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.10.0.9-1.el7_9.x86_64
    

## docker  
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

## docker-compose
    yum -y install docker-compose  &&  yum install bash-completion && docker-compose -v

## nodejs
    cd /usr/local
    mkdir nodejs
    cd nodejs
    wget https://nodejs.org/dist/v12.18.4/node-v12.18.4-linux-x64.tar.xz
    xz -d node-v12.18.4-linux-x64.tar.xz
    tar -xf node-v12.18.4-linux-x64.tar
    rm -rf node-v12.18.4-linux-x64.tar
    ln -s /usr/local/nodejs/node-v12.18.4-linux-x64/bin/node /usr/bin/node
    ln -s /usr/local/nodejs/node-v12.18.4-linux-x64/bin/npm /usr/bin/npm
    ln -s /usr/local/nodejs/node-v12.18.4-linux-x64/bin/npm /usr/bin/npx
    node -v 
    npm -v 
    npx 
    cnpm  ??
    npm config set registry https://registry.npm.taobao.org

## maven
    apache-maven-3.6.3-bin.zip
    MAVEN_HOME
    PATH %MAVEN_HOME%\bin\
    
    mvn --version
    mvn clean deploy

##  yarn
    cnpm install -g yarn
    yarn config set registry https://registry.npm.taobao.org -g
    yarn config set sass_binary_site http://cdn.npm.taobao.org/dist/node-sass -g

##  pm2
    npm install -g pm2
    pm2 list|logs 
    pm2 start | restart | stop ./bin/www

## nacos
    docker pull nacos/nacos-server
    docker run --env MODE=standalone --name nacos -d \
    -p 8848:8848 nacos/nacos-server
    
    startup.sh -m standalone

## nexus
    docker run -d -p 8081:8081 -p 8082:8082 -p 8083:8083 --name nexus3 \
    -v /usr/local/iya/base/nexus/sonatype-work:/sonatype-work \
    --restart=always sonatype/nexus3

## jenkins
    yum install -y java-1.8.0-openjdk
    wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo #wget -O 
    cat /etc/yum.repos.d/jenkins.repo
    yum install -y jenkins
    
    systemctl start jenkins
    
    ps aux |grep jenkins
    浏览器访问ip:8080

## harbor
    https://github.com/goharbor/harbor/releases (坚果云)
    tar -zxvf  harbor-offline-installer-v2.0.2.tgz
    cd harbor
    vim harbor.yml
        hostname: firepongo.tech
        http:port 8098
        harbor_admin_password:
    注释https
    ./prepare
    ./install.sh
    docker-compose up -d
    http://firepongo.tech:8098/

## gitlab-runner
    sudo docker run -d --name gitlab-runner --restart always   \
      -v /srv/gitlab-runner/config:/etc/gitlab-runner    \
      -v /var/run/docker.sock:/var/run/docker.sock   \
      -v /root/.m2:/root/.m2                   \
      -v /usr/bin/docker:/usr/bin/docker        \
      gitlab/gitlab-runner:latest
    
    sudo docker exec -it gitlab-runner  gitlab-runner register 
        http://git.firepongo.com/
        ek6g2xz8LgTRTCTug4hG
        maven,docker
        docker
        docker:19.03.1
        
    sudo vim /srv/gitlab-runner/config/config.toml
        privileged = true
        volumes = ["/cache","/certs/client","/root/.m2:/root/.m2","/var/run/docker.sock:/var/run/docker.sock"]
    
    docker restart gitlab-runner

## portainer
    docker run -d -p 9000:9000 \
    --restart=always \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --name prtainer \
    docker.io/portainer/portainer

## elasticSearch+kibana
    sysctl -w vm.max_map_count=262144
    docker pull elasticsearch:7.6.2
    docker run -d -e ES_JAVA_POTS="-Xms256m -Xmx256m" \
    -e "discovery.type=single-node" \
    -p 9200:9200 -p 9300:9300 --name search elasticsearch:7.6.2
    docker cp e210fa11d632:/usr/share/elasticsearch/config/ /usr/local/firepongo/base/elasticsearch/
    docker cp e210fa11d632:/usr/share/elasticsearch/data/ /usr/local/firepongo/base/elasticsearch/
    docker cp e210fa11d632:/usr/share/elasticsearch/logs/ /usr/local/firepongo/base/elasticsearch/
    docker cp e210fa11d632:/usr/share/elasticsearch/plugins/ /usr/local/firepongo/base/elasticsearch/
    docker rm -f  search
    
    docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d --name elasticsearch -p 9200:9200 -p 9300:9300  \
    -v /usr/local/iya/base/elasticsearch/config:/usr/share/elasticsearch/config \
    -v /usr/local/iya/base/elasticsearch/data:/usr/share/elasticsearch/data \
    -v /usr/local/iya/base/elasticsearch/logs:/usr/share/elasticsearch/logs \
    -v /usr/local/iya/base/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
    -e "discovery.type=single-node" elasticsearch:7.6.2
    
    docker exec -it elasticsearch /bin/bash
    cd /usr/share/elasticsearch/config
    vim elasticsearch.yml
    http.cors.enabled: true
    http.cors.allow-origin: "*"
    
    cd /usr/share/elasticsearch/plugins/
    mkdir ik
    cd ik
    curl https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.6.2/elasticsearch-analysis-ik-7.6.2.zip
    unzip elasticsearch-analysis-ik-7.6.2.zip
    rm -rf  elasticsearch-analysis-ik-7.6.2.zip
    exit
    docker restart elasticsearch
    
    docker run -d --name kibana -e "I18N_LOCALE=zh-CN" --link=elasticsearch:elasticsearch -p 5601:5601 kibana:7.6.2
    docker run -d --name elasticsearch-head -p 9100:9100 mobz/elasticsearch-head:5

安装nacos
https://github.com/alibaba/nacos/releases 下载nacos稳定版本
cd nacos/bin
startup.cmd -m standalone

docker run --name nacos-standalone -e MODE=standalone -p 8848:8848 -d nacos/nacos-server:latest
docker run --name sentinel -d -p 8858:8858 -d bladex/sentinel-dashboard

## poli
    docker run -d -p 6688:6688 --name poli zhonglu/poli:0.11.0
    wget https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.12/mysql-connector-java-8.0.12.jar
    docker cp mysql-connector-java-8.0.18.jar poli:/app/jdbc-drivers
    docker restart poli
    http://firepongo.tech:6688/poli/login
    admin adminadmin

verdaccio
npm install -g verdaccio --unsafe-perm
ln -s /usr/local/nodejs/node-v12.18.4-linux-x64/bin/verdaccio  /usr/local/bin/verdaccio
vi  /root/.config/verdaccio/config.yaml
在最后一行中加入 
listen: 0.0.0.0:4873
verdaccio

安装pm2
npm install pm2 -g 
ln -s /usr/local/nodejs/node-v12.18.4-linux-x64/lib/node_modules/pm2/bin/pm2  /usr/local/bin/pm2
pm2
pm2 start `which sinopia`
npm adduser --registry http://firepongo.com:4873

使用
http://firepongo.com:4873
npm set registry http://firepongo.com:4873
npm publish

# linux centos7

## 常用命令
    netstat -ntlp
    more log.txt | grep 'demo' -C 10

## springboot
    java -jar service.jar 
    nohup java -jar service.jar >output 2>&1 &

## dubbo
    telnet 192.168.18.211 20880
    ls
    ls -I com.test.DemoService
    invoke com.test.DemoService.queryDemoPageList({"id":"100"},1,2)

## git
    wget -O /tmp/git-2.21.0.tar.gz https://mirrors.edge.kernel.org/pub/software/scm/git/git-2.21.0.tar.gz
    yum install -y curl-devel expat-devel gettext-devel openssl-devel zlib-devel gcc perl-ExtUtils-MakeMaker
    tar -zxf /tmp/git-2.21.0.tar.gz -C /tmp/
    cd /tmp/git-2.21.0
    ./configure --prefix=/usr/local/git
    make && make install
    yum remove git
    vim /etc/profile
    GIT_HOME=/usr/local/git
    export PATH=$PATH:$GIT_HOME/bin
    source /etc/profile
    
    记录账号
    git config --global credential.helper store
    git pull

## gitlab
    账号找回
    su - git
    gitlab-rails console -e production
    user = User.where(id: 1).first
    user.password = '12345678'
    user.password_confirmation = '12345678'
    user.save!
    exit
    root/12345678
    
    centos-gitlab卸载
    gitlab-ctl stop
    rpm -e gitlab-ce
    ps aux | grep gitlab
    kill -9 18777 #（18777 是第一个进程的pid号，根据显示情况输入）
    ps -ef | grep gitlab | xargs kill -s 9
    find / -name gitlab | xargs rm -rf
    
    sudo docker pull gitlab/gitlab-ce:latest
    sudo docker run -d \ --hostname gitlab.xxx.com \ --publish 8443:443 --publish 18181:80 --publish 18122:22 \ --name gitlab \ --restart always \ --volume /mnt/data0/gitlab/config:/etc/gitlab \ --volume /mnt/data0/gitlab/logs:/var/log/gitlab \ --volume /mnt/data0/gitlab/data:/var/opt/gitlab \ gitlab/gitlab-ce:latest
    http://127.0.0.1:18181
    汉化(版本必须一致)
    `wget https://gitlab.com/xhang/gitl... -O gitlab-12-0-stable-zh.tar.bz2
    tar xf gitlab-12-0-stable-zh.tar.bz2
    cat gitlab-12-3-stable-zh/VERSION
    docker run -t -i gitlab/gitlab-ce:latest /bin/bash
    docker cp gitlab-12-0-stable-zh.tar.bz2 ${容器id}:/etc/gitlab
    docker commit -m "added zh" -a "user" ${容器id} gitlab/gitlab-ce:12-0-zh
    sudo docker run -d \ --hostname gitlab.xxx.com \ --publish 8443:443 --publish 18181:80 --publish 18122:22 \ --name gitlab \ --restart always \ --volume /mnt/data0/gitlab/config:/etc/gitlab \ --volume /mnt/data0/gitlab/logs:/var/log/gitlab \ --volume /mnt/data0/gitlab/data:/var/opt/gitlab \ gitlab/gitlab-ce:12-0-zh



## redis
    删库
    FLUSHALL

## rabbitmq

## PostgreSQL.md

## nginx




## mysql
    show profile
    explain
    show processlist;








