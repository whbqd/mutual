<template>
  <div class="revise animated bounce">
    <div class="Toup">
      <router-link to="/checking"
        ><span class="el-icon-back" />previous</router-link
      >
    </div>
    <h1>修改</h1>
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
    <!--提交-->
    <input @click="submit()" class="btn" type="button" value="SUBMIT" />
  </div>
</template>

<script>
    import axios from "axios";
    import $ from "animate.css";

    export default {
  $,
  name: "revise",
  data() {
    return {
      password: "",
      PwdFocus: false,
      Repwd: "",
      RepwdFocus: false
    };
  },
  methods: {
    submit() {
      if (this.password === "" || this.Repwd === "") {
        this.$message.warning("密码不能为空！");
        return false;
      }
      if (this.password !== this.Repwd) {
        this.$message.error("密码不一致！");
        return false;
      }
      axios({
          url: "https://www.whbqd.xyz/Login/revise",
          methods: "get",
          params: {
              user: this.$route.query.user,
              password: this.password
          }
      })
        .then(res => {
            console.log("#revise ▼");
            console.log(res);
          if (res.data.msg === true) {
            this.$message.success("修改成功!");
            this.$router.push("/");
          } else {
            this.$message.error("修改失败!");
          }
        })
        .catch(err => {
          err;
          this.$message.error("服务器异常!");
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

.revise {
  box-sizing: border-box;
  width: 370px;
  height: 540px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -175px;
  margin-top: -275px;
  padding: 20px 48px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.7);
  //返回login页面
  .Toup {
    position: absolute;
    left: 10px;
    top: 12px;
    font-size: 18px;
    a {
      color: #ff7675;
      text-decoration: none;
      cursor: pointer;
      &:hover {
        color: #e17055;
      }
      span {
        font-size: 22px;
        position: relative;
        top: 4px;
      }
    }
  }
  //标题
  h1 {
    margin: 60px 0;
    text-align: center;
    color: #ff7675;
  }
}
//input块
.dy {
  border-bottom: 1px solid #ff7675;
  position: relative;
  margin: 70px 0;

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
      color: #ff7675;
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
      background: linear-gradient(#fab1a0, #d63031);
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
  color: #eb4d4b;
  font-weight: bold;
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
  background: linear-gradient(120deg, #ef5777, #c04851, #ff7979);
  background-size: 200%;
  transition: 0.4s;

  &:hover {
    background-position: right;
  }
}
</style>
