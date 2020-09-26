# windows

## docker
[下载]()DockerToolbox-18.03.0-ce.exe

    双击 Docker Quickstart Terminal
    断网 
    cp boot2docker.iso C:\Users\cjj_a\.docker\machine\cache
    双击 Docker Quickstart
    docker-machine ssh default
    sudo -i
    vi /var/lib/boot2docker/profile
    --insecure-registry firepongo.tech:8288
    sudo sed -i "s|EXTRA_ARGS='|EXTRA_ARGS='--registry-mirror=https://9b3ogh7r.mirror.aliyuncs.com |g" /var/lib/boot2docker/profile
    exit 
    exit 
    docker-machine restart
    docker info
    docker login firepongo.tech:8288 (输入harbor账号密码)

## nodejs
    https://nodejs.org/zh-cn/download/
    node -v | npm -v
    npm config set registry https://registry.npm.taobao.org

## git
    打开 git bash
    git config --global user.name "cui"
    git config --global user.email "632381896@qq.com"
    cd ~/.ssh
    ssh-keygen -t rsa -C "632381896@qq.com"
    登陆GitHub，打开"Account settings"，"SSH Keys"页面：
    Add SSH Key--Title--Key--id_rsa.pub
