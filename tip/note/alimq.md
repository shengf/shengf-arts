
## AliMQ 使用笔记

1. AliMQ要求同一个consumerId即groupId下所有的Consumer实例的Topic和Tag都必须完全一致，否则会导致“订阅关系不一致”，一旦订阅关系不一致，消息消费的逻辑就会混乱，导致消息丢失。  
参照：https://help.aliyun.com/knowledge_detail/54349.html。

那么，如果要消费指定tag的消息，有哪些实践呢？

（1）consumer采用广播消费模式并且订阅tag都设置为*，这样同一个groupId下的多个节点都可以收到相同的消息，consumer收到消息后，判断消息中的tag与标识当前节点的tag（比如：前缀+IP十六进制）是否一致，如果不一致直接抛弃（注意这个tag判断是在代码中手动判断而不是alimq的tag filter）

需要注意的是：广播模式下，不支持顺序消息；消费进度在客户端维护，出现重复的概率稍大于集群模式；客户端每一次重启都会从最新消息消费。客户端在被停止期间发送至服务端的消息将会被自动跳过。
参考：https://helpcdn.aliyun.com/document_detail/43163.html

（2）consumer采用集群方式模拟广播：因为同一个groupId的tag必须一致，所以为了规避这个问题，可以每个节点设置不同的groupId，这样订阅的tag就可以不一致了，这样每一个不同节点都会收到全量消息，并且可以根据tag过滤。这也是阿里云推荐的方式。
But，在很多情况下，业务代码是没有自动创建groupId的权限的，一般每次groupId变动都需要提工单让运维操作，不能够自动化。

暂时选择第（1）种方案。

2. 持续更新中...
