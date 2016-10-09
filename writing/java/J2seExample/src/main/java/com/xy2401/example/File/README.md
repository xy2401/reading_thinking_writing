#流与文件

借用 Java核心技术·卷2高级特性 的第一章标题 流与文件 Streams and Files

《Java核心技术·卷2》这本书中第一章讲的非常不好，基本就是照本宣科的讲解了一下api。内容完全不全。单纯我抱着 学习NIO的心情去看。看完后就记得一个File 和Path了。其它都记不得了。囧

《写给大忙人看的Java Se 8》中第二章也讲解了一下 Stream api 不过貌似不是 文件流的 Streams。
第九章倒是讲解了一下 Path File 也就几页。
网上再找了一些资料[Java NIO 系列教程] 
[Java NIO 系列教程]:http://ifeve.com/java-nio-all/

##历史
 
做个整理彻底了解一下 java io & java nio

*    java.io 包的类基本上都是从jdk1、1.1的时候就有了
java.io.File /InputStream/OutputStream  Since: JDK1.0
java.io.FileWriter/PrintWriter Since:JDK1.1

*    java.nio 包从jdk1.4开始有的 nio(new IO)
java.nio.Buffer/ByteBuffer
java.nio.charset.Charset
java.nio.channels.FileChannel/FileLock/Selector/Pipe 
Since:1.4 

*    new I/O2 貌似在服务器/中间件上用的比较多，下面两个类完全说明不了AIO，囧
java.nio.file.Files /java.nio.file.Paths Since:1.7 
 

官方文档关于java.io包的说明 简单的一句话  
>Provides for system input and output through data streams, serialization and the file system.  
提供了系统输入和输出数据流,序列化和文件系统。


官方文档关于java.nio的说明 好几段说明
>Defines buffers, which are containers for data, and provides an overview of the other NIO packages.  
定义buffers,是数据的容器,并提供其他NIO包的概述  
The central abstractions of the NIO APIs are:
NIO api的核心抽象:  
1.   Buffers, which are containers for data;  
Buffers, 数据容器;  
2.   Charsets and their associated decoders and encoders, which translate between bytes and Unicode characters;
Charsets 及其相关解码器和编码器,字节和Unicode字符之间的转换;  
3.   Channels of various types, which represent connections to entities capable of performing I/O operations; and  
各种类型的Channels(管道),可以连接执行I/O操作的实体;和  
4.   Selectors and selection keys, which together with selectable channels define a multiplexed, non-blocking I/O facility.  
Selectors 和selection keys,和 可选择的channels(管道) 一起 定义一个多路复用,非阻塞I/O设备.
  

aio(asynchronous I/O)  异步IO non-blocking I/O 非堵塞IO 这两个大概是同义词吧，目前还不太了解 NIO NIO2 的关系，在JDK1.7中到底添加了些什么啊?