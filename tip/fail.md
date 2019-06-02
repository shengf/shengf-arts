
# failover vs. failfast vs. failback vs. failsafe

## 1.failover：失效转移/失效自动切换
Fail-Over的含义为“失效转移”，是一种备份操作模式，当主要组件异常时，其功能转移到备份组件。其要点在于有主有备，且主故障时备可启用，并设置为主。如Mysql的双Master模式，当正在使用的Master出现故障时，可以拿备Master做主使用。

dubbo默认的容错机制：当出现失败，重试其它服务器，通常用于读操作（推荐使用）。 重试会带来更长延迟。

## 2.failfast：快速失败 
从字面含义看就是“快速失败”，尽可能的发现系统中的错误，使系统能够按照事先设定好的错误的流程执行，对应的方式是“fault-tolerant（错误容忍）”。以JAVA集合（Collection）的快速失败为例，当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。例如：当某一个线程A通过iterator去遍历某集合的过程中，若该集合的内容被其他线程所改变了；那么线程A访问集合时，就会抛出ConcurrentModificationException异常（发现错误执行设定好的错误的流程），产生fail-fast事件。

dubbo : 只发起一次调用，失败立即报错，通常用于非幂等性的写操作。如果有机器正在重启，可能会出现调用失败 。

## 3.failback：失效自动恢复 
Fail-over之后的自动恢复，在簇网络系统（有两台或多台服务器互联的网络）中，由于要某台服务器进行维修，需要网络资源和服务暂时重定向到备用系统。在此之后将网络资源和服务器恢复为由原始主机提供的过程，称为自动恢复

dubbo：后台记录失败请求，定时重发。通常用于消息通知操作 不可靠，重启丢失。 可用于生产环境 Registry。

## 4.failsafe：失效安全 
Fail-Safe的含义为“失效安全”，即使在故障的情况下也不会造成伤害或者尽量减少伤害。维基百科上一个形象的例子是红绿灯的“冲突监测模块”当监测到错误或者冲突的信号时会将十字路口的红绿灯变为闪烁错误模式，而不是全部显示为绿灯。

dubbo：出现异常时，直接忽略，通常用于写入审计日志等操作。 调用信息丢失 可用于生产环境 Monitor。

dubbo 服务集群还有 Forking 和 Broadcast 两种容错机制：
- Forking：只要一个成功即返回，通常用于实时性要求较高的读操作。 需要浪费更多服务资源。
- Broadcast：广播调用，所有提供逐个调用，任意一台报错则报错。通常用于更新提供方本地状态 速度慢，任意一台报错则报错 。


## 5.参考：
1. [常见容错机制：failover、failfast、failback、failsafe](https://blog.csdn.net/u011305680/article/details/79730646)
2. [java中的fail-fast(快速失败)机制](https://blog.csdn.net/zymx14/article/details/78394464)
3. [Java快速失败（fail-fast）和安全失败（fail-safe）区别](https://blog.csdn.net/u010889616/article/details/79954413)