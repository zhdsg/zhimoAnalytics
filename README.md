本地开服，需要/etc/hosts中添加
10.10.100.11 Hadoop-NN-01
10.10.100.11 Hadoop-DN-01
10.10.100.11 Hadoop-DN-02

确认kafka是否写成功，可以ssh到10.10.100.2，输入如下命令：
kafka-console-consumer --zookeeper Hadoop-NN-01:2181 --topic useraction --from-beginning

resources/static/js/analytics.js文件会被客户端加载，向服务器端发送相关事件。
在react项目中，需要analytics-react-router来初始化相关配置。
http://gitlab.uuke.co/repo/analytics-react-router

