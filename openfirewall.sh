# 查看防火墙
###
 # @Author: homjay666 zhj19960621
 # @Date: 2023-02-17 16:03:59
 # @LastEditors: homjay666 zhj19960621
 # @LastEditTime: 2023-02-17 16:31:08
 # @FilePath: \undefinedd:\gfdn\bigdata-backstage-backend\openfirewall.sh
 # @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
### 


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