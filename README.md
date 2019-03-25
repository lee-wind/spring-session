# spring-session
spring-session(Java 手稿--Continue)  

未完成-To Be Continue(暂停Java总结,先安心搞前端,"吃饭要紧"...)  

环境：VirtualBox + Centos7  
前提：  
1、mysql服务启动，建表，sql在src/main/resources/sql  
2、mybatis-generator生成sql映射文件  
3、redis集群搭建（用于session共享）  
4、/etc/hosts增加:  
1)、cloud.wind.com 192.168.56.102（页面域）  
2)、sun.wind.com 192.168.56.102（接口域）  

已完成:  
1、ajax跨域请求：CROSFilter设置response.setHeader;  
2、cookie跨域设置,setDomain为wind.com;  
3、session跨域不起作用：xhr.withCredentials = true;  
4、XSS预防（包括json参数过滤）：XSSFilter;  
5、AOP鉴权, Interceptor鉴权, AOP打印请求、响应参数，AOP统一处理异常，AOP打印sql执行时间;  
6、注册,登录,退出,导入用户(json形式)  

测试地址(打开控制台看请求头跟请求结果):  
1、http://cloud.wind.com:8085/public/login.html  
2、http://cloud.wind.com:8085/public/ajax.html  
接口前缀：http://sun.wind.com:8085  
