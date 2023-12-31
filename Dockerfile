# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD ./bigdata-backstage-backend-1.0.0.jar /bigdata-backstage-backend-1.0.0.jar
# 声明服务运行在9002端口
EXPOSE 9002
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/bigdata-backstage-backend-1.0.0.jar"]
# 指定维护者的名字
MAINTAINER bigdata