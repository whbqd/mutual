<template>
  <div class="login animated rollIn">
    <h1>登录</h1>
    <!--账号-->
    <div class="dy">
      <input
        :class="{ f: UserFocus || user !== '' }"
        @blur="UserFocus = false"
        @focus="UserFocus = true"
        class="input"
        type="text"
        v-model="user"
      />
      <span class="title" data-placeholder="账号" />
    </div>
    <!--密码-->
    <div class="dy">
      <input
        :class="{ f: PwdFocus || password !== '' }"
        @blur="PwdFocus = false"
        @focus="PwdFocus = true"
        class="input"
        type="password"
        v-model="password"
      />
      <span class="title" data-placeholder="密码" />
    </div>
    <div class="forget"><a href="#">忘记密码？</a></div>
    <!--登录-->
    <input
      @click="login(user, password)"
      class="btn"
      type="button"
      value="登录"
    />
    <!--注册-->
    <div class="register">
      没有账号，点击注册? <router-link to="/register">注册</router-link>
    </div>
  </div>
</template>

<script>
    import axios from "axios";
    import $ from "animate.css";

    export default {
  $,
  name: "login",
  data() {
    return {
      UserFocus: false,
      user: "",
      PwdFocus: false,
      password: ""
    };
  },
  methods: {
    login() {
      axios({
        url: "http://localhost:8080/Backend/login",
        methods: "get",
        params: {
          user: this.user,
          password: this.password
        }
      })
        .then(res => {
          if (res.data.msg === false) {
            this.$message.error("账号或密码错误！");
          } else {
            this.$message.success("登录成功！");
          }
          console.log(res);
        })
        .erarr(error => {
          error;
          this.$message.error("服务器异常！");
        });
    }
  }
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}
.login {
  box-sizing: border-box;
  width: 360px;
  height: 550px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -180px;
  margin-top: -275px;
  padding: 20px 40px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.7);
  h1 {
    margin: 60px 0;
    text-align: center;
  }
}

//input块
.dy {
  border-bottom: 2px solid #bdc3c7;
  position: relative;
  margin: 46px 0;

  .input {
    box-sizing: border-box;
    width: 100%;
    height: 40px;
    border: none;
    background: none;
    outline: none;
    padding: 0 5px;
    font-size: 15px;
  }

  .title {
    &::before {
      content: attr(data-placeholder);
      font-size: 18px;
      color: #bdc3c7;
      position: absolute;
      left: 5px;
      top: 9px;
      transition: 0.5s;
      z-index: -1;
    }

    &::after {
      content: "";
      width: 0;
      height: 2px;
      background: linear-gradient(#3498db, #a29bfe);
      position: absolute;
      left: 0;
      bottom: -2px;
      z-index: 10000;
      transition: 0.5s;
    }
  }
}

.f + .title::after {
  width: 100%;
}

.f + .title::before {
  top: -17px;
}

//忘记密码
.forget {
  width: 100%;
  position: relative;

  a {
    font-size: 14px;
    color: #a29bfe;
    position: absolute;
    right: 0;
    top: -20px;
    text-decoration: none;
    font-weight: bold;

    &:hover {
      color: #74b9ff;
    }
  }
}

//登录
.btn {
  width: 100%;
  height: 50px;
  border: none;
  border-radius: 4px;
  outline: none;
  font-size: 20px;
  color: #fff;
  margin: 15px 0;
  cursor: pointer;
  background: linear-gradient(120deg, #74b9ff, #a29bfe, #fab1a0);
  background-size: 200%;
  transition: 0.4s;

  &:hover {
    background-position: right;
  }
}

//注册
.register {
  width: 100%;
  text-align: center;
  margin-top: 25px;
  font-size: 14px;

  a {
    margin-left: 7px;
  }
}
</style>
