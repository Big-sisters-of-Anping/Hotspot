# Hotspot

### 1. 项目描述

本项目是[Hotspot微信小程序](https://github.com/Big-sisters-of-Anping/Hotspot-front)的后端代码，也包括微信小程序内嵌的网页资源代码。项目涉及如下技术：

* 使用 [SpringBoot](https://spring.io/projects/spring-boot) 作为后台开发框架

* 使用 [Maven](http://maven.apache.org/index.html) 来构建项目以及管理依赖

* 使用 [MyBatis](https://mybatis.org/mybatis-3/) 作为持久层框架

* 使用 [MySQL](https://www.mysql.com/) 作为数据库管理系统

* 使用 [BootStrap](https://www.bootcss.com/) 作为前端框架

* 使用 [Google Chart](https://developers.google.cn/chart/interactive/docs) 来在网页中可视化统计数据

* 使用 [jQuery](https://jquery.com/)  来简化 JavaScript 开发

* 使用 [Swagger](https://swagger.io/) 来可视化接口，帮助前后端协同开发

* 使用 [Nginx](http://nginx.org/en/) 来部署微信小程序内嵌的静态网站（可选）

  > 使用Nginx部署而不是直接利用SpringBoot部署，能够更好地在开发时实现前后端分离



### 2. 部署

#### 2.1 部署流程

0. 环境依赖
   1. 安装 MySQL
   
   2. 安装 JDK
   
      ```shell
      java -version # 检查是否安装成功
      ```
   
   3. 安装 Python3（预约决策算法所需）

   4. 安装 Nginx （可选）
   
1. 数据库建立：

   1. 开启 MySQL 服务

      ```shell
      # CentOS下
      systemctl start mysqld.service
      ```

   2. 根据 MySQL 用户名和密码的设置更改 `application-*.yml` 配置文件中的jdbc配置

   3. 执行 `src/main/resources/databaseGeneratedSQL.sql` 中的所有语句，来创建项目所需的数据库表

2. 运行后台服务：

   1. 在本地使用 Maven 打包项目，并将 `target`  文件夹下生成的jar包传到服务器
   2. 将 `order.py` 上传到jar包同级文件夹中
   3. 在服务器上运行jar包，并将日志输出到 `nohup.out`

   ```shell
   nohup java -jar xxxx.jar &
   ```

3. （可选）为了更好地前后端分离开发，使用 Nginx 部署静态网站（响应微信小程序前端内嵌的HTML）：

   1. 将 `src/main/resources/static` 中的文件上传到服务器上
   2. 在 Nginx 配置文件中将 80 端口的站点目录（即 `root` 字段）配置成 `static` 文件夹路径
   3. 重新启动 Nginx

4. 检查端口：

   ```shell
   netstat -ntlp
   ```

   * 此时，只有当以下端口都处于 LISTEN 状态时，以上步骤才都已经顺利完成
     * 3306: mysql —— 若未 LISTEN，检查步骤1是否成功执行
     * 8080: 本项目	—— 若未 LISTEN，检查步骤2是否成功执行
     * 80（可选）: HTTP（ Nginx 代理）—— 若未 LISTEN，检查步骤3是否成功执行
   * 注意：
     * 若服务器上已经监听了这几个端口，但其他机器依旧不能访问这几个端口
       * 检查是否在服务器租赁商的云服务平台上为你的服务器添加了这几个端口的安全组规则
       * 检查防火墙设置
     * 你也可以用Nginx为需要监听的端口配置统一的代理端口。

5. 检查是否部署成功：

   * 为了方便前后端协同开发，项目中配置了Swagger；访问项目的Swagger主页，若主页成功加载并且接口能够成功执行，则部署完成

#### 2.2 云服务器（已部署）

- 公网IP：47.104.248.28
- 开放端口：22(ssh)、3306(MySQL)、8080(Hotspot)、80(HTTP, Nginx代理)
- 用户名：root
- 密码：WQY525yue
- Swagger主页：http://47.104.248.28:8080/swagger-ui.html



### 3. 项目结构

```lisp
.
├── pom.xml	; maven配置文件
├── order.py	; 预约决策算法
└── src	;主要的开发文件夹
   ├── main
   │   ├── java
   │   │   └── com
   │   │       └── example
   │   │           └── demo
   │   │               ├── DemoApplication.java	;工程启动类
   │   │               ├── config	;配置信息文件夹
   │   │               │   ├── dao	;数据访问配置
   │   │               │   │   ├── DataSourceConfiguration.java	;jdbc连接配置
   │   │               │   │   └── SessionFactoryConfiguration.java	;MyBatis配置
   │   │               │   ├── service	;具体的服务类配置
   │   │               │   │   ├── CorsConfig.java	;跨域服务配置（用于浏览器访问时）
   │   │               │   │   └── TransactionManagementConfiguration.java	;数据库事务配置
   │   │               │   └── swagger	;Swagger配置
   │   │               │       └── SwaggerConfig.java
   │   │               ├── controller	;前端控制器文件夹
   │   │               │   ├── NoticeController.java
   │   │               │   ├── OrderController.java
   │   │               │   ├── ParkController.java
   │   │               │   ├── SearchController.java
   │   │               │   ├── SpotController.java
   │   │               │   ├── UserController.java
   │   │               │   ├── WishController.java
   │   │               │   ├── annotation		;自定义注解
   │   │               │   │   └── TokenLimit.java	;TokenLimit注解
   │   │               │   └── interceptor	;拦截器
   │   │               │       ├── TokenInterceptor.java	;Token拦截器
   │   │               │       └── WebMVCConfig.java		;拦截器配置
   │   │               ├── dao	;数据访问层文件夹
   │   │               │   ├── NoticeDao.java
   │   │               │   ├── OrderDao.java
   │   │               │   ├── ParkDao.java
   │   │               │   ├── SpotDao.java
   │   │               │   ├── UserDao.java
   │   │               │   └── WishDao.java
   │   │               ├── entity	;实体类文件夹
   │   │               │   ├── Notice.java
   │   │               │   ├── Order.java
   │   │               │   ├── Park.java
   │   │               │   ├── Spot.java
   │   │               │   ├── SpotOrderTime.java
   │   │               │   ├── SpotWishTime.java
   │   │               │   ├── User.java
   │   │               │   └── Wish.java
   │   │               ├── handler	;全局处理类文件夹
   │   │               │   └── GlobalExceptionHandler.java	;全局异常处理类
   │   │               ├── service	;数据服务层文件夹
   │   │               │   ├── NoticeService.java
   │   │               │   ├── OrderService.java
   │   │               │   ├── ParkService.java
   │   │               │   ├── SearchService.java
   │   │               │   ├── SpotService.java
   │   │               │   ├── UserService.java
   │   │               │   ├── WishService.java
   │   │               │   └── impl	;数据服务的实现类文件夹
   │   │               │       ├── NoticeServiceImpl.java
   │   │               │       ├── OrderScheduleTask.java	;每晚23:00调用预约决策算法
   │   │               │       ├── OrderServiceImpl.java
   │   │               │       ├── ParkServiceImpl.java
   │   │               │       ├── SearchServiceImpl.java
   │   │               │       ├── SpotServiceImpl.java
   │   │               │       ├── UserServiceImpl.java
   │   │               │       └── WishServiceImpl.java
   │   │               └── util	;工具类
   │   │                   ├── CookieUtil.java	;Cookie解析类
   │   │                   └── TokenUtil.java		;Token管理及检查类（在TokenInterceptor中使用）
   │   └── resources	;资源文件夹
   │       ├── application-*.yml	;不同开发环境对应一个配置文件
   │       ├── application.yml		;指定当前使用的配置
   │       ├── mapper	;MyBatis映射文件（对应../java/com/example/demo/dao中的每个数据访问接口）
   │       │   ├── NoticeDao.xml
   │       │   ├── OrderDao.xml
   │       │   ├── ParkDao.xml
   │       │   ├── SpotDao.xml
   │       │   ├── UserDao.xml
   │       │   └── WishDao.xml
   │       ├── mybatis-config.xml	;MyBatis配置
   │       ├── static	;静态文件（为了更快地加载html界面，一些依赖下载到了本地）
   │       │   ├── html
   │       │   │   └── orderStatistic.html ;预约统计界面
   │       │   ├── bootstrap-datetimepicker-master	;Bootstrap的datetimepicker依赖包
   │       │   ├── bootstrap-3.3.7-dist	;Bootstrap依赖包
   │       │   └── jquery	;jquery依赖包
   │       └── databaseGeneratedSQL.sql	;项目对应的数据库生成语句
   └── test ;测试文件文件夹
```