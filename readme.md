## 通过DNSPOD对本机进行动态域名解析的小工具(当前Windows下完成更新记录功能)

### 1. 介绍

    本工具使用Java编写，使用DNSPOD中国区的API对本机进行动态域名解析。可以将本机的IP地址绑定到一个域名上。(当前通过网络站点的API获取，无网络或网络站点停止API可能会出现问题)
    (当前网络站点:
        IPV4:Amazon AWS
        IPV6:ipify
        感谢上面站点提供的服务
    )

### 2. 使用方法
    新建一个配置文件，命名为config.properties，放在jar包同目录下的resources文件夹中，内容如下：
```properties
    #配置文件示例
domain=主域名 
#例如 example.com

subdomain=子域名 
#例如 www

login_token=登录口令 
#请前往dnspod获取

format=json 
#返回格式 勿做变动

record_type=记录类型
#请根据DNSPOD控制台给出的类型填入,在此不作说明

record_line=线路类型
#请根据DNSPOD控制台给出的类型填入,在此不作说明

ttl=600
#可自行调整

```
    

    (待实现) 通过命令行参数传入想要更新的本机IP类型; 例:
        java -jar domain-update [ipv4/ipv6]]

### 3. 依赖
    - Java 17+
    - Maven
    - DNSPOD API
    - Junit4
    - JSON-java


目前尚在开发阶段，许可证信息将会在后续补充，当前仅做提交，功能尚不完善，欢迎提出建议。

本项目仅作学习，请勿用于商业用途。
