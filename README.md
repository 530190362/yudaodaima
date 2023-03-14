# 基于 SpingtBoot 后台管理系统

---

[toc]

## 1、选型

---

- 核心技术

| 名称            | 版本          | 简介               | 官网                                       |
| --------------- | ------------- | ------------------ | ------------------------------------------ |
| java            | 1.8           | 开发语言           | https://www.java.com/zh-CN/                |
| spring-boot     | 2.3.6.RELEASE | 基础框架           | https://spring.io/projects/spring-boot     |
| mybatis         |               | ORM框架            | https://mybatis.org/                       |
| mybatis-plus    |               | MyBatis 的增强工具 | https://baomidou.com/                      |
| redis           |               | 数据缓存           | https://redis.io/                          |
| mysql           | 5.7.X         | 数据库             | https://www.mysql.com/                     |
| MongoDB         |               | 文档数据库         | https://www.mongodb.com/                   |
| neo4j           |               | 图数据库           | https://neo4j.com/                         |
| spring security |               | 权限控制           | https://spring.io/projects/spring-security |
| AOP             |               | 全局日志记录       |                                            |
| docker          | 20.10.20      | 容器管理           | https://docs.docker.com/get-docker/        |
| git             |               | 版本控制           | https://git-scm.com/                       |
| hutool          |               | 常用工具类型       | https://www.hutool.cn/                     |
| lombok          |               | 对象注解           | https://projectlombok.org/                 |
| swagger         |               | 接口文档           | https://swagger.io/                        |
| maven           |               | 资源管理           | https://maven.apache.org/                  |

- 工具选型

| 名称                          | 简介                      | 官网                                             |
| ----------------------------- | ------------------------- | ------------------------------------------------ |
| IntelliJ IDEA                 | 最强集成开发环境          | https://www.jetbrains.com/zh-cn/idea/            |
| chorme                        | 最强浏览器                | https://www.google.com/intl/zh-CN/chrome/        |
| Navicat Premium 16            | mysql可视化访问界面       | https://www.navicat.com.cn/navicat-16-highlights |
| Another Redis Desktop Manager | redis可视化访问界面       | https://goanother.com/cn/                        |
| NoSQLBooster for MongoDB      | MongoDB可视化访问界面     | https://www.nosqlbooster.com/                    |
| Sourcetree                    | Git可视化访问界面         | https://www.sourcetreeapp.com/                   |
| Visual Studio Code            | 最强轻便开发环境/丰富插件 | https://code.visualstudio.com/                   |
| Kafka Tool 2                  | kafa可视化访问界面        | https://www.kafkatool.com/download.html          |
| PrettyZoo                     | zookeeper可视化访问界面   | https://github.com/vran-dev/PrettyZoo            |
| draw.io                       | 流程图                    | https://draw.io/index.html                       |

- 集群配置

| 名称                 | 开发环境Host:Port    | 开发环境账号:密码  | 开发环境URL                          | 生产环境Host:Port | 生产环境URL                             |
| -------------------- | -------------------- | ------------------ | ------------------------------------ | ----------------- | --------------------------------------- |
| 前端服务             | localhost:9001       | root:123456        |                                      |                   |                                         |
| 后端服务             | localhost:9002       | root:123456        |                                      |                   |                                         |
| swagger 接口文档     |                      |                    | http://localhost:9002/swagger-ui/    |                   | http://124.221.163.103:9002/swagger-ui/ |
| mysql 单节点         | 124.221.163.103:9003 | root:123456        |                                      |                   |                                         |
| redis 单节点         | 124.221.163.103:9004 | 123456             |                                      |                   |                                         |
| MongoDB 单节点       | 124.221.163.103:9005 | root:123456        |                                      |                   |                                         |
| neo4j 单节点         | 124.221.163.103:9006 | neo4j:123456       | http://124.221.163.103:9006/browser/ |                   |                                         |
| Portainer 运维可视化 | 124.221.163.103:9000 | admin:admin1234567 | http://124.221.163.103:9000/         |                   |                                         |
| IDEA连接docker       | 8999                 |                    | 需要密钥文件,见服务器                |                   |                                         |

- 模块

| 名称                      | 英文        |
| ------------------------- | ----------- |
| 数仓开发模块(临时)        | dw          |
| 团队资产模块(临时)        | ass         |
| 权限模块                  | ums         |
| 数仓运维平台模块-数据标准 | norm        |
| 数仓运维平台模块-首页     | homepage    |
| 数仓运维平台模块-数据资产 | dataasset   |
| 数仓运维平台模块-资源管理 | res         |
| 数仓运维平台模块-任务同步 | task        |
| 数仓运维平台模块-数据探查 | explore     |
| 数仓运维平台模块-数据集成 | source      |
| 数仓运维平台模块-数据质量 | dataquality |
| 数仓运维平台模块-通用     | common      |



## 2、环境搭建

```shell
##################################################### 常用命令
docker ps
docker ps -a
docker logs <容器ID>
docker rm  <容器ID>
docker rmi  <镜像ID>
docker exec -it <容器ID> /bin/bash

##################################################### docker
百度...

##################################################### redis
export REDIS_PORT=6379
export DOCKER_USERNAME=myredis
export REDIS_VERSION=6.0.8
export REDIS_DATA=/data/redis

mkdir -p ${REDIS_DATA}
上传 ${REDIS_DATA}/redis.conf

docker run -d -p ${REDIS_PORT}:6379 --name myredis --privileged=true \
-v ${REDIS_DATA}/redis.conf:/etc/redis/redis.conf \
-v ${REDIS_DATA}/data:/data \
redis:${REDIS_VERSION} \
redis-server /etc/redis/redis.conf


##################################################### mysql
export DOCKER_USERNAME=mymysql
export MYSQL_VERSION=5.7
export MYSQL_PORT=9003
export MYSQL_DATA=/data/mysql
export MYSQL_ROOT_PASSWD=123456

mkdir -p ${MYSQL_DATA}/conf
上传 ${MYSQL_DATA}/conf/my.cnf 

docker run -d -p ${MYSQL_PORT}:3306 --privileged=true \
--name ${DOCKER_USERNAME} \
-v ${MYSQL_DATA}/log:/var/log/mysql \
-v ${MYSQL_DATA}/data:/var/lib/mysql \
-v ${MYSQL_DATA}/conf:/etc/mysql/conf.d \
-e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWD} \
mysql:${MYSQL_VERSION}

##################################################### neo4j
export DOCKER_USERNAME=myneo4j
export NEO4J_PORT=9006
export NEO4J_VERSION=3.5
export NEO4J_DATA=/data/neo4j
export NEO4J_ROOT_PASSWD=123456

mkdir -p ${NEO4J_DATA}

docker run -d -p ${NEO4J_PORT}:7474  -p 7687:7687 \
--name ${DOCKER_USERNAME} \
-v ${NEO4J_DATA}/data:/data \
-v ${NEO4J_DATA}/logs:/logs \
-v ${NEO4J_DATA}/import:/var/lib/neo4j/import \
-v ${NEO4J_DATA}/plugins:/plugins \
--env NEO4J_AUTH=neo4j/${NEO4J_ROOT_PASSWD} \
neo4j:${NEO4J_VERSION}


##################################################### MongoDB
export DOCKER_USERNAME=mymongo
export MONGODB_PORT=9005
export MONGODB_VERSION=4.4.8
export MONGODB_DATA=/data/mongodb
export MONGODB_ROOT_USER=root
export MONGODB_ROOT_PASSWD=123456

mkdir -p ${MONGODB_DATA}

docker run -d --privileged=true \
--name ${DOCKER_USERNAME} \
-p ${MONGODB_PORT}:27017 \
--name ${DOCKER_USERNAME} \
-v ${MONGODB_DATA}:/data/db \
-e MONGO_INITDB_ROOT_USERNAME=${MONGODB_ROOT_USER} \
-e MONGO_INITDB_ROOT_PASSWORD=${MONGODB_ROOT_PASSWD} \
mongo:${MONGODB_VERSION}


##################################################### Portainer 运维可视化
export PORTAINER_NAME=myportainer
export PORTAINER_PORT=9000
docker run -d -p 8000:8000 -p ${PORTAINER_PORT}:9000 --name ${PORTAINER_NAME} --restart=always     -v /var/run/docker.sock:/var/run/docker.sock   -v /data/portainer_data:/data portainer/portainer

```



## 3、约定规范

---

- ***mysql***
  - 库名与应用名称尽量一致
  - 表名、字段名必须使用小写字母或数字，禁止出现数字开头
  - 表名不使用复数名词
  - 表的命名最好是加上 ***业务名称_表的作用***。如`edu_teacher`
  - 表必备三字段：`id, gmt_create, gmt_modified`
    - id 必为主键，类型为 `bigint unsigned`、单表时自增、步长为 1
    - 使用分库分表集群部署，则id类型为`verchar`，非自增，业务中使用分布式id生成器
    - 修改时间 与  创建时间  的类型均为 `datetime `  类型
  - 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表
  - 表达是与否概念的字段，必须使用 `is_deleted`  的方式命名，数据类型是 `unsigned tinyint `（1 表示是，0 表示否）
  - 小数类型为 decimal，禁止使用 float 和 double
  - 如果存储的字符串长度几乎相等，使用 char 定长字符串类型
  - varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表 `MongoDB`，用主键来对应，避免影响其它字段索 引效率
  - 唯一索引名为 uk_字段名；普通索引名则为 idx_字段名。 uk_ 即 unique key；idx_ 即 index 的简称
  - 不得使用外键与级联，一切外键概念必须在应用层解决。外键与级联更新适用于单机低并发，不适合分布式、高并发集群；级联更新是强阻塞，存在数据库更新风暴的风险；外键影响数据库的插入速度
- ***java web***
  - ***mvvm*** 架构  前后端分离

## 4、项目工程(单节点)

---

- `bigdata-backstage-backend` 根目录（父工程）
  - `common` 通用配置
  - `config` 配置参数
  - `domain`  通用实体类
  - `generator` 后端代码生成器
  - `modules` 各个模块
  - `security` 安全认证
  - `DoApplication.java` 启动程序
  

## 5、项目部署

---

```SHELL
1、普通
 
java -jar bigdata-backstage-backend-1.0.0.jar
nohup java -jar bigdata-backstage-backend-1.0.0.jar &

2、容器化部署

# TODO  ...等待编写
```



## 6、环境说明

---

````shell
前端
XX.XX.XX.40
/home/micros/bigdata

后端
XX.XX.XX.38
/home/micros/bigdata-backstage
````



## #、参考连接

---

- Idea快捷键

```shell
# 单个方法折叠/展开
Ctrl -
Ctrl +

# 全部方法折叠/展开
Ctrl Shift -
Ctrl Shift +

# 向下粘贴当前行
ctrl + D
```

- 脚手架
  - https://github.com/macrozheng/mall-tiny
- 代码生成器
  - https://baomidou.com/
