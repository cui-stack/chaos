#!/bin/sh
##### DATA #####
PROFILE=base,dev
JAVA_HOME=/usr/local/jdk/bin

##### METHOD #####
## 帮助
help() {
 echo "Usage : sh boot.sh [start|debug|stop|restart|redebug|status]"
 exit 1
}
## 获取应用路径
get_address() {
  cd $(dirname $0)
  cd ..
  echo $(pwd)
}
## 获取应用名
get_app_name() {
  for FILE in `ls ${HOME}`
  do
    if [[ -f ${HOME}"/"${FILE} && ${FILE} == *jar ]]
    then
      echo ${FILE}
      break
    fi
  done
}
## 根据应用名判断是否存在进程
# ${1}第一个参数为应用名
is_app_exist() {
  APP_NAME=${1}
  PID=$(ps -ef|grep ${APP_NAME}|grep -v grep|awk '{print $2}')
  if [ -z "${PID}" ]
  then
    # 返回0表示进程不存在
    return 0
  else
    # 返回1表示进程存在
    return 1
  fi
}
## 启动应用
start() {
  is_app_exist ${APP_NAME}
  if [ $? -eq "1" ]
  then
    echo "app is already running,PID=${PID}"
  else
    cd ${HOME}
nohup ${JAVA_HOME}/java -Xms256m -Xmx256m -jar ${HOME}/${APP_NAME} --spring.profiles.active=${PROFILE} > log.txt 2>&1 &
    echo "app start,PID=${!}"
  fi
}
## 启动应用(调试模式)
debug() {
  is_app_exist ${APP_NAME}
  if [ $? -eq "1" ]
  then
    echo "app is already running,PID=${PID}"
  else
    if [ "${PROFILE}" == "pro" ]
    then
      cd ${HOME}
      nohup ${JAVA_HOME}/java -Xms128m -Xmx128m -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=35650 ${HOME}/${APP_NAME} --spring.profiles.active=${PROFILE} > /dev/null 2>&1 &
      echo "app start,PID=${!}"
    else
      echo "can not running in debug mode"
    fi
  fi
}
## 停止应用
stop() {
  is_app_exist ${APP_NAME}
  if [ $? -eq "1" ]
  then
    kill ${PID}
    sleep 2
    is_app_exist ${APP_NAME}
    if [ $? -eq "1" ]
    then
      echo "app stop failed"
      kill -9 ${PID}
    fi
    echo "app stop"
  else
    echo "app is not running"
  fi
}
## 重启应用
restart() {
  stop
  start
}
## 重启应用(调试模式)
redebug() {
  stop
  debug
}
## 查看应用状态
status() {
  is_app_exist ${APP_NAME}
  if [ $? -eq "1" ]
  then
    echo "app is running, PID=${PID}"
  else
    echo "app is not running"
  fi
}

##### SCRIPT #####
HOME="$(get_address)"
APP_NAME="$(get_app_name)"
case $1 in
"help")
  help
  ;;
"start")
  start
  ;;
"debug")
  debug
  ;;
"stop")
  stop
  ;;
"restart")
  restart
  ;;
"redebug")
  redebug
  ;;
"status")
  status
  ;;
esac
exit 0
                  
