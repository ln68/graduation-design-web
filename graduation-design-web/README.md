#毕业设计
声明实体类：教师，学生，课程，方向
持久化
声明组件
登录controller组件。登陆后需要创建token，因此
声明密钥/盐值
加密/解密组件
回到登录方法，token加密置于response header；声明角色代码返给前端
登录拦截器
从header获取/解密token，
controller方法经常需要使用uid，role，置于request attribute；
配置拦截器
创建RequestComponent组件，避免在方法注入
登录测试，获取Authorization，所有权限请求均需在header携带。脚本变量
Teacher权限拦截器，配置
创建权限Controller组件，测试

post请求发不出，没弄明白
POST http://localhost:8080/api/teacher/courses

org.apache.http.conn.HttpHostConnectException: Connect to localhost:8080 [localhost/127.0.0.1] failed: Connection refused: connect


