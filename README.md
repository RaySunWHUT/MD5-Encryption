# MD5-Encryption
This is the implement of course named "Computer System Skills Training" of WHUT.

# Demand:

1. **GUI 界面**

2. 实现 软件注册(**license**) 功能:

   1. 输入 **注册码** 进行注册（注册码：由DDL根据 硬盘硬件**序列号** 或 网卡 **MAC** 地址 加密生成）。
      1. 注册码错误不能注册。
      2. 其他计算机无论注册码是否正确，均不能注册，以保护软件版权。

   **idea**: electron 实现跨平台应用；验证： **MAC地址** 不同，即使注册码相同，依然无法注册。

3. 登录: 

   1. 可以根据 **用户名(userName)**、**密码(password)** 登录.
   2. 调用 DLL 对 **密码(password)** 进行加密，并将 用户名(userName) 和 加密后密码(encrypted password) 写入 **系统注册表**(HKEY_LOCAL_MACHINE).

4. 加密(encrypt):

   1. 调用 DLL 对文本内容(text)进行加密。
   2. 实现方式: 
      1. **手动输入** 文本内容(text)。
      2. 打开txt、word获取相应文本内容(text)。
   3. 可以显示加密前、加密后文本内容(text)。

5. **解密测试(Decrypt Test)**: 

   1. 利用在线加解密系统 **测试** DLL 加密结果的**正确性**。



# Solution:

1. **实现方式**: 

   ​    Java 调用 C DDL.

2. **算法**: 

   1. MD5，以**512位**分组来处理输入的信息，且每一分组又被划分为**16个32位子分组**，经过了一系列的处理后，算法的输出由**4个32位分组**组成，将这**4个32位分组级联**后将生成一个**128位[散列值](https://baike.baidu.com/item/散列值/10398613)**.
   2. DES.
   3. AES.

3. **DLL 设计及实现**:

   1. 进程调用不属于其的可执行代码。
   2. 不同App可 **同时** 访问内存中的 DLL 的内容。
   3. 实现具有 MD5 加密接口的 DLL，提供对外调用的 **加密接口 —— encrypt**。
      1. 支持显示调用、隐式调用。

4. **DLL 应用**:

   1. 模拟用户登录加密.
   2. **软件注册码** 进行版本管理(version control): Registration Code.
   3. 文本加密.



# View Design:

 1. 软件注册: license.
 2. 软件首页: index.
 3. 登录: login.
 4. 主要 **功能模块(functional module)**:
     	1. 加密(encrypt):
          	1. 输入文本:
               	1. 文本框：手动输入。
                   	2. 上传按钮：选择.txt / .doc / .docx文件。
                      	2. 显示：
                           	1. 显示加密前后文本内容。
                  	2. 解密(decrypt):
                  	1. 对文本进行解密:
                       	1. 文本列表。
                           	2. 选择是否解密。
                               	3. 解密结果显示。



# Thinking:

 1. 总体设计：

    总体设计分为三大部分:

     	1. C语言封装动态链接库 .dll 和 .so。
          	1. C语言实现MD5加密算法。
     	2. Java访问、读写注册表(registry)；连接动态链接库DLL。
          	1. 获取Mac网卡地址。
          	2. 获取硬件硬件序列号。
          	3. 连接dll接口。
          	4. 编写project back end。
     	3. Electron框架实现GUI。
           	1. 整合HTML、css、JavScript。
          	2. 编写project front end。
          	3. 实现跨平台桌面程序。

# Workflow:

Part1: 注册、登录

1. 打开软件 → 输入软件注册码(MAC or Disk SN) → 验证注册码 → 验证登录密码 → 登录成功！

   程序流程如下:

​	![enter](https://github.com/RaySunWHUT/MD5-Encryption/blob/master/assets/enter.png)



Part2: 加密、解密测试

 1. 登录成功 → 加密 → 手动输入/上传  → 显示加密前、加密后的文本； 

 2. 登录成功 → 解密 → 选择文本 → 解密测试系统正确性。

    程序流程如下:

​	![function](https://github.com/RaySunWHUT/MD5-Encryption/blob/master/assets/function.png)
# References:
1. https://www.cnblogs.com/anywell/p/10242635.html

2. https://blog.csdn.net/qq_33113661/article/details/88991909

3. https://www.cnblogs.com/anywell/p/10242635.html

4. https://yq.aliyun.com/articles/586741?utm_content=m_1000012592

5. https://segmentfault.com/a/1190000019402908?utm_source=tag-newest



