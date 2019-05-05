
# 分别用sort、comm、cat、grep等命令求文本文件的合集、交集、差集

两个个文本文件，1.txt 内容为：
```
1
3
5
7
9
11
```
2.txt 内容：
```
2
4
6
8
10
11
```


## 1. sort
```
## 1. 合集：
> sort 1.txt 2.txt | uniq
1
10
11
2
3
4
5
6
7
8
9

## 2. 交集（uniq -d 仅显示重复出现的行, 这种方法前提是在1.txt内部、2.txt内部没有重复行）：
> sort 1.txt 2.txt | uniq -d
11

## 3. 差集(这种方式先通过sort -m 把几个文件合并，然后通过uniq -u把没有重复的行输出——在1.txt中存在在2.txt中不存在的记录): 
> sort -m <(sort 1.txt | uniq) <(sort 2.txt |uniq) <(sort 2.txt |uniq) | uniq -u
1
3
5
7
9

```

## 2. comm
Attention: Linux命令跟MAC命令有些许差异，以下是MAC下操作结果

```
## comm命令要求输入的是有序的，所以用先做了sort，comm A B的结果分为三列，分别是：A-B，B-A，A交B
> comm <(sort 1.txt | uniq) <(sort 2.txt|uniq)
1
	10
		11
	2
3
	4
5
	6
7
	8
9


## 交集
> comm -1 -2 <(sort 1.txt | uniq) <(sort 2.txt|uniq)
11

## 差集A-B
> comm -2 -3 <(sort 1.txt | uniq) <(sort 2.txt|uniq)
1
3
5
7
9

## 差集B-A
> comm -1 -3 <(sort 1.txt | uniq) <(sort 2.txt|uniq)
10
2
4
6
8

```

## 3. cat
```
## 合集
> cat 1.txt 2.txt |sort |uniq
1
10
11
2
3
4
5
6
7
8
9

## 交集
> cat 1.txt 2.txt |sort |uniq -d
11 

## 删除交集，留下其他的行
> cat 1.txt 2.txt |sort |uniq -u
1
10
2
3
4
5
6
7
8
9


```

## 4. grep
```
## 交集(此处结果中有10是因为1.txt的1和2.txt中的10都含有1)
> grep -F -f 1.txt 2.txt
10
11

## 差集（B-A）：
> grep -F -v -f 1.txt 2.txt
2
4
6
8

## 差集（A-B）：
> grep -F -v -f 2.txt 1.txt
1
3
5
7
9

```