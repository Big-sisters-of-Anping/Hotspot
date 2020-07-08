# Hotspot

### 功能描述

本项目Hotspot的后端代码，使用如下技术：

* SpringBoot 作为后台开发框架
* MyBatis 作为持久层框架
* MySQL 作为数据库管理系统



### 开发环境



#### 部署

##### 部署流程



##### 云服务器（已部署）

- 公网IP：47.104.248.28
- 开放端口：22(ssh)、3306(MySQL)、8080(Hotspot)、80(HTTP, Nginx代理)
- 用户名：root
- 密码：WQY525yue
- Hotspot项目Swagger主页：http://47.104.248.28:8080/swagger-ui.html



### 项目结构

```lisp
.
├── pom.xml		; maven配置文件
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
   │   │               │       ├── OrderScheduleTask.java
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
   │       ├── mapper	;MyBatis映射文件（对应Dao文件夹中的每个namespace）
   │       │   ├── NoticeDao.xml
   │       │   ├── OrderDao.xml
   │       │   ├── ParkDao.xml
   │       │   ├── SpotDao.xml
   │       │   ├── UserDao.xml
   │       │   └── WishDao.xml
   │       ├── mybatis-config.xml	;MyBatis配置
   │       ├── static	;静态文件（为了更快地加载html界面，一些依赖下载到了本地）
   │       │   ├── html
   │       │   │	 └── orderStatistic.html ;预约统计界面
   │       │   ├── bootstrap-datetimepicker-master	;Bootstrap的datetimepiker依赖包
   │       │   ├── bootstrap-3.3.7-dist	;Bootstrap依赖包
   │       │   └── jquery	;jquery依赖包
   │       └── databaseGeneratedSQL.sql	;项目对应的数据库生成语句
   └── test ;测试文件文件夹
```