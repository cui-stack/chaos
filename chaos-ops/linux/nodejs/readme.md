#安装nodejs环境
##安装nodejs+npm
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
