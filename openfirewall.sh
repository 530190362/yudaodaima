# 查看防火墙


# 开通 9002 端口
firewall-cmd --zone=public --add-port=9002/tcp --permanent


# 重新加载防火墙
firewall-cmd --reload

# 查看开放的列表
firewall-cmd --list-ports


# 测试防火墙
telnet xx.xx.xx.xx 9002


# 使用命令行的Get请求
curl -X GET "http://xx.xx.xx.xx:9002/homepage/datachange?days=0&type=0" -H "accept: */*"