## 通过DNSPOD对本机进行动态域名解析的小工具(未完成主要功能)

### 1. 介绍

    本工具使用Java编写，使用DNSPOD的API对本机进行动态域名解析。可以将本机的IP地址绑定到一个域名上。(当前仅支持单网卡，多网卡设备可能会出现问题)

### 2. 使用方法

    (待实现) 通过命令行参数传入想要更新的IP; 例:
        java -jar domain-update ipv4

### 3. 依赖
    - Java 21+
    - Maven
    - DNSPOD API
    - Junit4


目前尚在开发阶段，许可证信息将会在后续补充，当前仅做提交，功能尚不完善，欢迎提出建议和意见。
