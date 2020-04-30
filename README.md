# mutual

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### 登录 API
* url:  http://47.102.223.209:8080/Login/login
* 请求方式  ： get post
* 请求参数 : {
* user => 用户名 ,
* password => 密码
* }
* 返回值 : {
*   msg: boolean => 登录状态
*        =>  true {
*               user,
*               password,
*               token
*           }
* }


### 注册api
* url:  http://47.102.223.209:8080/Login/register
*请求方式  get,post
*请求参数 {
*   user,
*   password,
*   email
* }
*返回值 {
*   user => 账号,
*   password => 密码,
*   email => 邮箱,
*   msg => 注册状态（true, false）,
*   repeatUsername => 重复账号（true, false）
* }
* 返回格式  json
* */



### 判断用户名及邮箱Api
*url:   http://47.102.223.209:8080/Login/checking
*请求方式  get,post
*请求参数 {
*   user,
*   email
* }
* 返回值 {
*   user,
*   email,
*   msg => 判断状态码
* }


### 修改api
* url:    http://47.102.223.209:8080/Login/revise
* 请求方式 get,post
* 请求参数 {
*   user,
*   password
* }
* 返回值 {
*   user,
*   password,
*   msg => 修改状态码
* }

### 查询全部信息api
* url:  http://47.102.223.209:8080/Login/UserAll
* 请求方式： get, post
* 请求参数： null
* 返回值： {
*   userList => 查询结果对象
*   msg => 查询状态码
* }



