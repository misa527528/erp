# ERP 沙盘
---
author: [haiyuanhe](http://haiyuanhe.github.io)|[misa527528](https://github.com/misa527528/)


## 安装
1. 安装的时候注意Artificial 和 Modules 的配置
2. Modules 配置
```
    ├─src
    ├──main
    ├───java>(src)
    ├────com.cqupt.mis.erp   
    ├────resources           >(resources)
    ├───webapp
    ├────WEB-INF
    ├─────web.xml
    └─pom.xml
```

## issue
1. 持久层mybatis mapper 和XML
2. action层加入抽象的filter ,将response统一封装
3. log4j 的详细配置.
4. 缓存的加入.
5. 测试/单元测试 和 压力测试
6. 自动化发布/不同的环境不同的发布
