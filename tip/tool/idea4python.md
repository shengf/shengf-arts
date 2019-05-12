
# IDEA 配置Python编译运行环境


1. 安装python插件
Plugins ——> Browse Repositories ——> Category选择Language，搜索Python，安装后重启Idea。
2. File ——> New ——> Project 这时候就可以创建Python项目了
3. python代码示例：

```

def test_fun():
    print("test_fun begin:")
    list = [1,2]
    i=2
    while(i<10):
        s = list[i-2] + list[i-1]
        list.append(s)
        i=i+1
    print(list[0:9])

if __name__ == '__main__':
    test_fun()

```

4. 参考：https://blog.csdn.net/qq_38188725/article/details/80623710