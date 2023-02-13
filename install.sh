#!/bin/sh

################################################################################
# 《数仓管理运维管理平台》
#
# 用法:
#   1. 修改Mysql参数配置
#   2. 运行脚本  sh ./install.sh
#   3. 查看结果  success
#
# 注意事项:
################################################################################

# Mysql参数配置
MYSQL_HOST: hadoop103
MYSQL_PORT: 3306
MYSQL_USER: root
MYSQL_PASSWD: 123456
MYSQL_DB: backstage
JAR_NAME: bigdata-backstage-backend-1.0.0.jar
SQL_NAME: init.sql


# 部署 mysql(略) ...
# 部署 redis(略) ...

# 执行 SQL 文件
# shellcheck disable=SC2112
function fun_do_mysql() {
  echo "正在创建数据库 ${MYSQL_DB} ..."
  CREATE_DB_SQL="CREATE DATABASE IF NOT EXISTS ${MYSQL_DB} DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;"
  mysql -h${MYSQL_HOST} -P${MYSQL_PORT} -u${MYSQL_USER} -p${MYSQL_PASSWD} -e ${CREATE_DB_SQL}
  echo "正在执行 init.sql ..."
  mysql -h${MYSQL_HOST} -P${MYSQL_PORT} -u${MYSQL_USER} -p${MYSQL_PASSWD} ${MYSQL_DB} < ${SQL_NAME}

}

# 开启java 任务
# shellcheck disable=SC2120
function fun_do_java() {
  echo "正在开启后端服务 ..."
  kill -9 $(ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}')
  nohup java -jar ${JAR_NAME} > /dev/null 2>&1 &
}

# 开启 前端 任务

function fun_do_vue() {
  echo "正在开启前端服务"
  cd "$1"
  npm run dev
}

# 主程序
# shellcheck disable=SC2112
function main() {
  fun_do_mysql
  fun_do_java
  fun_do_vue
}


#开启
main