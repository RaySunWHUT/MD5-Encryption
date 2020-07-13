md5.c: 完成对DLL主要逻辑代码的使用.

md5.h: 声明DLL中使用的函数，同时暴露DLL加密接口(**MD5Encrypt**).

ps: 值得一提的是，生成解决方案(build solution) 时，需要采用 release 模式，并且根据调用dll语言的运行环境位数(32位 / 64位)选择，生成x86(32位) 的 DLL，还是x64(64位)的 DLL。

否则，会报错误。