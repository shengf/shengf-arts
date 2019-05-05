
# 灰度发布方式整理

## 1. 代码中埋开关，做if-else判断
几乎每个公司都会有类似的实现，据说阿里数据库从Oracle迁移到MySQL时，使用了一个状态变量进行控制。从而打到平滑迁移的效果；另外，Amazon这种实践也是比较多的。

优点：快速回滚，不需要重新发布和重启系统；
缺点：对代码有侵入，分支逻辑引入复杂性

代码中埋开关的具体实践有：

- 服务提供方的接口参数中添加version相关字段，不同的version走不同的代码branch（注意：如果旧版本中没有version字段，需要给version设定默认值如0，如果需要调用新版本服务需要调用方传入具体version）——这种情况灰度比例的提升实际上是服务调用方决定的

- 对请求参数（如userId）进行hash，如果落在灰度范围内，则走灰度分支（可以通过分布式动态配置，如etcd、disconf、captain等，来动态设定一个0-100的灰度比例，调整灰度比例时不需要重新部署或者重启服务）


## 2. 金丝雀发布，新版本节点和旧版本节点同时存在
> 先说一下为什么叫“金丝雀”，17世纪，英国矿井工人发现，金丝雀对瓦斯这种气体十分敏感。空气中哪怕有极其微量的瓦斯，金丝雀也会停止歌唱；而当瓦斯含量超过一定限度时，虽然鲁钝的人类毫无察觉，金丝雀却早已毒发身亡。当时在采矿设备相对简陋的条件下，工人们每次下井都会带上一只金丝雀作为“瓦斯检测指标”，以便在危险状况下紧急撤离。

> 而金丝雀发布/灰度发布是在原有版本可用的情况下，同时部署一个新版本应用作为“金丝雀”，测试新版本的性能和表现，以保障整体系统稳定的情况下，尽早发现、调整问题。这种发布形式大多数用于缺少足够测试，或者缺少可靠测试，或者对新版本的稳定性缺乏信心的情况下。

也就是说金丝雀发布需要旧版本和新版本同时在线提供服务，逐步给1%、2%、5%、10%、20%、50%的机器发布新版本，也就达到了灰度的目的。

具体实践：

- 接入层如nginx根据IP进行路由，如IP末位<16的路由到灰度机器，其他的路由到旧版本机器。

## 3.SET部署

互联网常常把一定号段的用户路由到某个一组指定的服务中，而这一组服务就是常说的set。set部署可以说是金丝雀发布的一种实践，set部署的具体做法有：

- 按照业务/接口隔离，前端进行nginx转发
- 按照用户（如：userId）隔离部署


## 4. A/B Test
A/B Test，简单来说，就是为同一个产品目标制定两个或更多个方案（比如两个页面一个用红色的按钮、另一个用蓝色的按钮），让一部分用户使用A方案，另一部分用户使用B方案，然后通过日志记录用户的使用情况，并通过结构化的日志数据分析相关指标，如点击率、转化率等，从而得出那个方案更符合预期设计目标，并最终将全部流量切换至符合目标的方案。

A/B Test 主要倾向于产品维度的灰度，比如UI设计、业务流程等，而不是安全稳定发布新版本。


另外，其他的部署方式有：

## 5. 影子部署

影子部署是指在现有版本A旁边发布新版本B，将版本A进来的请求同时分发到版本B，同时对生产环境流量无影响。可见，影子部署是一种非常重的方式，一般适用于大型重构，对新版本上线没有信息的情况。

网易开源的[TCPCopy](https://github.com/session-replay-tools/tcpcopy)是一个分布式在线压力测试工具，可以将线上流量拷贝到测试机器，实时的模拟线上环境，达到在程序不上线的情况下实时承担线上流量的效果，尽早发现bug，增加上线信心，实际上非常类似于影子部署的效果。


## 6. 其他参考
- [新浪的动态策略灰度发布系统：ABTestingGateway](http://www.cnblogs.com/beautiful-code/p/6278779.html)
- [灰度发布系统的实现](http://f.dataguru.cn/thread-716228-1-1.html)
- [知乎：实现一套灰度发布系统需要考虑哪些问题？]（https://www.zhihu.com/question/20584476）
- [Regal: 用于"灰度发布"或 A/B Testing的智能分组引擎](https://github.com/boylegu/regal)
- [saas灰度发布的技术实现](https://www.jianshu.com/p/9eb58d8da485)