
来源：微信公众号**阿里技术**发布的文章: [深度 | API 设计最佳实践的思考](https://mp.weixin.qq.com/s/qWrSyzJ54YEw8sLCxAEKlA)


## 设计准则

### 提供清晰的思维模型 provides a good mental model

### 简单 is simple
- Make things as simple as possible, but no simpler.

### 容许多个实现 allows multiple implementations

## 最佳实践

### 优秀的参考
通过 File API 体会 API 设计的最佳实践（想想 File API 是怎么设计的）

### Document well 写详细的文档

### Carefully define the "resource" of your API 仔细的定义“资源”

### Choose the right level of abstraction 选择合适的抽象层

### Naming and identification of the resource 命名与标识

### Conceptually what are the meaningful operations on this resource? 对于该对象来说，什么操作概念上是合理的？
需要定义哪些操作需要支持 —— 概念上合理(Conceptually reasonable)

### For update operations, prefer idempotency whenever feasible 更新操作，尽量保持幂等性
- Create类型： client-side generated de-deduplication token（客户端生成的唯一ID），在反复重试时使用同一个Unique ID，便于服务端识别重复
- Updated类型： Incremental（数量增减） vs. SetNewTotal（设置新的总量）, 前者重试的时候会出错，但是容易实现并发（可借助Deduplication token 解决幂等性）；后者比较容易具备幂等性，但是可能导致并行的更新相互覆盖（或者相互阻塞）。
- Delete型：？？

### Compatibility 兼容
- 这里的兼容指的是向后兼容，而兼容的定义是不会 Break 客户端的使用，也即老的客户端能否正常访问服务端的新版本（如果是同一个大版本下）不会有错误的行为。
- 如何做不兼容的API变更？不兼容变更需要通过一个 Deprecation process，在大版本发布时来分步骤实现。
- 强烈不建议采用“同步升级”的模式来处理不兼容API变更。

### Batch mutations 批量更新
- 除非对于客户来说提供原子化+事务性的批量很有意义（ all-or-nothing），否则实现服务端的批量更新有诸多的弊端，而客户端批量更新则有优势

### Be aware of the risks in full replace 警惕全体替换更新模式的风险

### Don't create your own error codes or error mechanism 不要试图创建自己的错误码和返回错误机制



原文参考文献：

【1】File wiki
https://en.wikipedia.org/wiki/Computer_file

【2】阿白，域模型设计系列文章
https://yq.aliyun.com/articles/2255

【3】Idempotency, wiki
https://en.wikipedia.org/wiki/Idempotence

【4】Compatibility
https://cloud.google.com/apis/design/compatibility

【5】API Design patterns for Google Cloud
https://cloud.google.com/apis/design/design_patterns

【6】API design best practices, Microsoft
https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design

【7】Http status code
https://en.wikipedia.org/wiki/List_of_HTTP_status_codes

【8】A philosophy of software design, John Ousterhout