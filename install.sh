#!/bin/sh

################################################################################
# 《数仓管理运维管理平台》
#
# 用法:
#   1. 修改参数配置
#   2. 修改权限  chmod +x install.sh
#   2. 运行脚本  sh ./install.sh
#   3. 查看结果  success
#
# 注意事项:
################################################################################

################################################################################
# Mysql参数配置
MYSQL_HOST=hadoop104
MYSQL_PORT=30811
MYSQL_USER=root
MYSQL_PASSWD=123456
MYSQL_DB=bigdata-backstage
# 前后端安装包名称
JAR_NAME=bigdata-backstage-backend-1.0.0.jar
SQL_NAME=init.sql
VUE_NAME=dist.tar
RUN_NAME=run.sh
# 前后端安装路口
JAR_DIR=/home/micros/service/bigdata-backstage-backend
VUE_DIR=/home/web/bigdata-backstage-frontend

# JVM参数,及其日志
JVM_OPTS="-Dname=${JAR_NAME} -Duser.timezone=Asia/Shanghai -Xms1024M -Xmx1024M -XX:PermSize=512M -XX:MaxPermSize=512M"
LOG_FILE=${JAR_DIR}/logs/bigdata-backstage-backend.log
################################################################################

# 部署 mysql(略) ...
# 部署 redis(略) ...


################################################################################
# 辅助函数
print_ok(){
    echo -e "\033[32m === $1 === \033[0m"
}

print_error(){
    echo -e "\033[31m >>> $1 <<< \033[0m"
}
################################################################################


################################################################################
# 核心函数
function init() {
  print_ok "判断是否存在后端部署路径"
  if [ ! -d "$JAR_DIR" ]; then
    mkdir -p "$JAR_DIR"
    mkdir -p "$JAR_DIR/logs"
  fi

  print_ok "判断是否存在后端部署路径"
  if [ ! -d "$VUE_DIR" ]; then
    mkdir -p "$VUE_DIR"
    cp "$VUE_NAME" "$VUE_DIR"
  fi

  if [ ! -x "$(command -v mysql)" ]; then
    print_error  "未安装mysql客户端，程序退出"
    exit 1
  fi

  if [ ! -x "$(command -v java)" ]; then
    print_error  "未安装java客户端，程序退出"
    exit 1
  fi

  print_ok "修改run.sh AppName 和 LOG_FILE 的参数值 , 文件拷贝的路径: $JAR_DIR"
  cp "$JAR_NAME" "$JAR_DIR"
  cp "$RUN_NAME" "$JAR_DIR"
  sed -i "s#AppName=.*#AppName=${JAR_NAME}#g" ${JAR_DIR}/${RUN_NAME}
  sed -i "s#LOG_FILE=.*#LOG_FILE=${LOG_FILE}#g" ${JAR_DIR}/${RUN_NAME}

}

function init_do_mysql() {
  print_ok "正在创建数据库 ${MYSQL_DB} ..."
  CREATE_DB_SQL="CREATE DATABASE IF NOT EXISTS ${MYSQL_DB} DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;"
  mysql -h${MYSQL_HOST} -P${MYSQL_PORT} -u${MYSQL_USER} -p${MYSQL_PASSWD} -e "${CREATE_DB_SQL}"
  if [ $? -ne 0 ]; then
    print_error "创建数据库 ${MYSQL_DB} 失败"
    exit 1
  fi
  print_ok "正在导入数据库 ${MYSQL_DB} ... 执行 init.sql ..."
  mysql -h${MYSQL_HOST} -P${MYSQL_PORT} -u${MYSQL_USER} -p${MYSQL_PASSWD} ${MYSQL_DB} < ${SQL_NAME}
    if [ $? -ne 0 ]; then
      print_error "导入数据库 ${MYSQL_DB} 失败"
      exit 1
    fi
}


# 开启java 任务
function init_do_java() {
  # 关闭jar
	PID=""
	query(){
		PID=$(ps -ef |grep java|grep $JAR_DIR|grep -v grep|awk '{print $2}')
	}
	query
	if [ x"$PID" != x"" ]; then
		kill -TERM "$PID"
		print_ok "关闭java进程 $PID"
		while [ x"$PID" != x"" ]
		do
			sleep 1
			query
		done
		print_ok "关闭java进程成功"
	else
	  print_ok "未发现java进程"
	fi
  print_ok "正在启动后端服务 ..."
  nohup java "${JVM_OPTS}" -jar ${JAR_DIR}/${JAR_NAME} >${LOG_FILE} 2>&1 &
  if [ $? -ne 0 ]; then
    print_error "启动后端服务失败"
    exit 1
  fi
  print_ok "启动后端服务成功"
}

# 开启 前端 任务
function init_do_vue() {
  print_ok "正在启动前端服务 ..."
}
################################################################################


################################################################################
# 核心程序封装
# shellcheck disable=SC2112
function main() {
  init
  init_do_mysql
  init_do_java
  init_do_vue
  print_ok "success"
}
################################################################################


################################################################################
# 开启程序
main
################################################################################