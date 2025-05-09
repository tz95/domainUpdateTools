## 通过DNSPOD对本机进行动态域名解析的小工具(当前未完成主要功能)

### 1. 介绍

    本工具使用Java编写，使用DNSPOD中国区的API对本机进行动态域名解析。可以将本机的IP地址绑定到一个域名上。(当前通过其他站点的API获取，无网络或其它站点停止API可能会出现问题)

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

```
    

    (待实现) 通过命令行参数传入想要更新的本机IP类型; 例:
        java -jar domain-update ipv4

### 3. 依赖
    - Java 21+
    - Maven
    - DNSPOD API
    - Junit4


目前尚在开发阶段，许可证信息将会在后续补充，当前仅做提交，功能尚不完善，欢迎提出建议。

本项目仅作学习，请勿用于商业用途。
