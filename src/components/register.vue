<template>
  <div class="reg animated jackInTheBox">
    <div class="Tologin">
      <router-link to="/"><span class="el-icon-s-custom" />login</router-link>
    </div>
    <h1>注册</h1>
    <!--账号-->
    <div class="dy">
      <input
        :class="{ f: UserFocus || user !== '' }"
        @blur="UserFocus = false"
        @focus="UserFocus = true"
        class="input"
        type="text"
        v-model="user"
        maxlength="12"
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
        maxlength="16"
      />
      <span class="title" data-placeholder="密码" />
    </div>
    <!--确认密码-->
    <div class="dy">
      <input
        :class="{ f: RepwdFocus || Repwd !== '' }"
        @blur="RepwdFocus = false"
        @focus="RepwdFocus = true"
        class="input"
        type="password"
        v-model="Repwd"
        maxlength="16"
      />
      <span class="title" data-placeholder="确认密码" />
    </div>
    <!--邮箱-->
    <div class="dy">
      <input
        :class="{ f: emailFocus || email !== '' }"
        @blur="emailFocus = false"
        @focus="emailFocus = true"
        class="input"
        type="email"
        v-model="email"
        maxlength="30"
      />
      <span class="title" data-placeholder="邮箱" />
    </div>
    <!--确认-->
    <input @click="register()" class="btn" type="button" value="确认" />
  </div>
</template>

<script>
    import axios from "axios";

    export default {
  name: "register",
  data() {
    return {
      UserFocus: false,
      user: "",
      PwdFocus: false,
      password: "",
      Repwd: "",
      RepwdFocus: false,
      email: "",
      emailFocus: false
    };
  },
  methods: {
    register() {
      //判断是否有空值
      if (
        this.user === "" ||
        this.password === "" ||
        this.Repwd === "" ||
        this.email === ""
      ) {
        this.$message.warning("请将信息补全！");
        return false;
      }
      //判断确认密码是否一致
      if (this.password !== this.Repwd) {
        this.$message.warning("密码不一致");
        return false;
      }
      axios({
        url: "http://localhost:8080/Backend/register",
        methods: "get",
        params: {
          user: this.user,
          password: this.password,
          email: this.email
        }
      })
        .then(res => {
          console.log(res);
          if (res.data.repeatUser) {
            this.$message.error("账号已被注册！");
          } else if (res.data.msg) {
            this.$message.success("注册成功！");
            this.$router.push("/");
          } else if (!res.data.msg) {
            this.$message.error("注册失败！");
          }
        })
        .catch(err => {
          err;
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

.reg {
  box-sizing: border-box;
  width: 390px;
  height: 650px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -185px;
  margin-top: -325px;
  padding: 20px 48px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.7);
  //返回login页面
  .Tologin {
    position: absolute;
    left: 10px;
    top: 12px;
    font-size: 20px;
    a {
      color: #74b9ff;
      text-decoration: none;
      cursor: pointer;
      &:hover {
        color: #0984e3;
      }
    }
  }
  //标题
  h1 {
    margin: 60px 0;
    text-align: center;
    color: #40739e;
  }
}
//input块
.dy {
  border-bottom: 1px solid #40739e;
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
      color: #40739e;
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
      background: linear-gradient(#74b9ff, #6c5ce7);
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
  top: -22px;
  color: #0097e6;
  font-weight: bold;
}

//忘记密码
.forget {
  width: 100%;
  position: relative;

  a {
    font-size: 14px;
    color: #74b9ff;
    position: absolute;
    right: 0;
    top: -20px;
    text-decoration: none;
    font-weight: bold;

    &:hover {
      color: #a29bfe;
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
  background: linear-gradient(120deg, #74b9ff, #a29bfe, #3dc1d3);
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
