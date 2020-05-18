<template>
  <div class="checking animated bounceInRight">
    <div class="Toup">
      <router-link to="/"><span class="el-icon-back" />login</router-link>
    </div>
    <h1>验证</h1>
    <!--账号-->
    <div class="dy">
      <input
        :class="{ f: userFocus || user !== '' }"
        @blur="userFocus = false"
        @focus="userFocus = true"
        class="input"
        type="text"
        v-model="user"
        maxlength="12"
      />
      <span class="title" data-placeholder="账号" />
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
    <!--下一步-->
    <input @click="ToRevise()" class="btn" type="button" value="NEXT" />
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
      user: "",
      userFocus: false,
      email: "",
      emailFocus: false
    };
  },
  methods: {
    /**
     * @return {boolean}
     */
    ToRevise() {
      //判断是否有空值
      if (this.user === "" || this.email === "") {
        this.$message.warning("账号或邮箱不能为空!");
        return false;
      }
      axios({
          url: "https://www.whbqd.xyz/Login/checking",
          methods: "get",
          params: {
              user: this.user,
              email: this.email
          }
      })
        .then(res => {
            console.log("#checking ▼")
            console.log(res);
          if (res.data.msg === false) {
            this.$message.error("账号或邮箱错误!");
            return false;
          }
          this.$router.push(`/revise?user=${this.user}&email=${this.email}`);
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

.checking {
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
  background: rgba(255, 255, 255, 0.8);
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
        top: 2px;
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
  color: #eb2f06;
  font-weight: bold;
}

//下一步
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
