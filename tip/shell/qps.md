

# 指定时间QPS统计

- 项目名：test-project
- 请求日志名：http-request-test-project.log.2019-05-04
- 请求URL：/test-project/api/test
- 日志格式为：
```
2019-05-04 19:41:35.221 @@@traceId=3315122fa074ad44@@@ filter 1556922695221 [url=/test-project/api/test] [method=POST] [sc=200] [$duration=952] mode=0 [ip=113.214.193.106] [referer=https://xxx.com/index.html] [useragent=Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.3(0x17000321) NetType/WIFI Language/zh_CN] [logVersion=0]
```

- 统计19点40到20点的QPS最高的10s的数据：
```
cat http-request-test-project.log.2019-05-04 | grep -a "url=/test-project/api/test]" | grep -aE '2019-05-04 19:4|2019-05-04 19:5' | awk -F "@" '{print $1}' | cut -d '.' -f 1 | sort | uniq -c | sort -r -n |head -n 10
```
