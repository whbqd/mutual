### 1.pom.xml 依赖

```xml
 <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot</artifactId>
            <version>2.2.6.RELEASE</version>
        </dependency>
</dependency>
```

### 2.在resources下创建mapping包 -> UserMapping.xml 并配置

![image-20200519171847909](C:\Users\18086\AppData\Roaming\Typora\typora-user-images\image-20200519171847909.png)

>  UserMapping.xml 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--业务层所在位置-->
<mapper namespace="com.dy.mapper.UserMapper">
								<!--实体类所在位置-->
    <resultMap id="BaseResultMap" type="com.dy.entity.User">
        <!--key-->
        <result column="id" jdbcType="INTEGER" property="id" />
        <result column="user" jdbcType="VARCHAR" property="user" />
        <result column="password" jdbcType="VARCHAR" property="password" />
        <result column="email" jdbcType="VARCHAR" property="email" />
    </resultMap>
	<!--id=方法名 resultType 对应实体类所在位置-->
    <select id="Sel" resultType="com.dy.entity.User">
        select * from user where id = #{id}
    </select>
</mapper>
```





### 3.在application.properties中配置数据源 和 mybatis 的xml及实体类所在包

> 数据源：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/servlet_db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=000000
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

> mybatis 的xml及实体类所在包

```properties
mybatis.mapper-locations=classpath:mapping/*Mapping.xml
mybatis.type-aliases-package=com.dy.entity
```

### 4.创建包controller、entity、mapper、service。

![image-20200519172516421](C:\Users\18086\AppData\Roaming\Typora\typora-user-images\image-20200519172516421.png)

> User
>
> ```java
> @Data
> @AllArgsConstructor
> @NoArgsConstructor
> public class User {
>     private Integer id;
>     private String user;
>     private String password;
>     private String email;
> }
> ```
>
> UserMapper
>
> ```java
> @Repository
> public interface UserMapper {
>     User Sel(int id);
> }
> ```
>
> UserService
>
> ``` java
> @Service
> public class UserService{
>     @Autowired
>     UserMapper userMapper;
>     public User Sel(int id) {
>         return userMapper.Sel(id);
>     }
> }
> ```
>
> UserController
>
> ```java
> @RestController
> @RequestMapping("/user")
> public class UserController {
>     @Autowired
>     private UserService userService;
> 
>     @RequestMapping("getUser/{id}")
>     public User GetUser(@PathVariable int id) {
>         return userService.Sel(id);
>     }
> }
> ```

### 最后在DemoApplication.java 中添加MapperScan扫描

```java
@MapperScan("com.dy.mapper")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```

## 

### DemoApplication只能扫描到同一级目录下的文件包

