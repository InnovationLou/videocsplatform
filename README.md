
# 精品课程平台（后端）

### 软件工程课程设计
swagger地址:localhost:8222/swagger-ui.html

druid监控地址:localhost:8222/druid/login

```javascript
//druid监控账户
username=admin
pwd=123
```

### 开发日志

- 2020.6.4

    - 完成基础系统框架搭建
    
    - 集成shiro+JWT实现认证
    
    - 配置数据库和mybatisplus
    
    - 添加swagger2和druid监控
    
        >Ques:登录后每次请求的token都需要查库，mybatis一级缓存似乎无效
        
        >Info：SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7d133fb7] was not registered for synchronization because synchronization is not active
              JDBC Connection [com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@788e3702] will not be managed by Spring
              ==>  Preparing: select * from user where username= 'user01' 
             关于spring事务失效:https://blog.csdn.net/dhklsl/article/details/88354216
        
        >Solve：未开启mybatis事务，且由于登录方法和获取用户的方法在同一个类中spring无法提供事务支持，即@Transactional注解所标注的获取用户方法失效
---

- 2020.6.5
    -  修复 mybatis缓存无效以及spring事务无效的bug
    
        >Solve：spring每次的请求都是独立的sqlSession，固一级缓存无法发挥作用，打开全局二级缓存配置，并在UserMapper上配置二级缓存之后即可（每次校验token不需要查库）
    
    - 完成所有业务接口
    - 添加控制器通知RestControllerAdvice统一处理前端异常信息
    - /videocsplatform/course/course某个课程信息包含分P播放信息   接口存在bug
    
- 2020.6.6
    - 修复业务接口bug
        - /videocsplatform/course/course
        
    - 添加apache license,添加开发环境和发布环境配置

    - 整合thymeleaf 使用AdminLTE前端模板搭建后台管理系统，完成静态资源映射，配置资源访问