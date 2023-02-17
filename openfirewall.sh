# 查看防火墙


# 开通 9002 端口
firewall-cmd --zone=public --add-port=9002/tcp --permanent


# 重新加载防火墙
firewall-cmd --reload

# 查看开放的列表
firewall-cmd --list-ports