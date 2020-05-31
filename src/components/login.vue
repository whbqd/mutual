<template>
  <div class="login_box animated bounce">
    <div class="container">
      <!--      左侧图片-->
      <div class="img_left">
        <img :class="{ bz: name !== 'LOGIN' }" src="../assets/bz.jpg" alt="" />
        <img
          :class="{ bz: name !== 'SIGNUP' }"
          src="../assets/bz2.jpg"
          alt=""
        />
      </div>
      <!--      右侧-->
      <div class="panel">
        <!--        头像-->
        <div class="img_top"><img src="../assets/tx.png" alt="" /></div>
        <div class="dev">
          <span
            @click="
              (name = 'LOGIN'),
                (UserName = ''),
                (Password = ''),
                (Repeat = ''),
                (Email = '')
            "
            :class="{ active: name === 'LOGIN' }"
            id="login"
            >Login</span
          ><span> / </span
          ><span
            @click="
              (name = 'SIGNUP'),
                (UserName = ''),
                (Password = ''),
                (Repeat = ''),
                (Email = '')
            "
            :class="{ active: name === 'SIGNUP' }"
            id="signup"
            >SignUp</span
          >
        </div>
        <div class="form">
          <!--          账号-->
          <div class="input">
            <input
              :class="{ f: userFocus || UserName !== '' }"
              @blur="userFocus = false"
              @focus="userFocus = true"
              type="text"
              v-model="UserName"
            />
            <span class="title" data-placeholder="UserName" />
          </div>
          <!--          密码-->
          <div class="input">
            <input
              :class="{ f: pwdFocus || Password !== '' }"
              @blur="pwdFocus = false"
              @focus="pwdFocus = true"
              type="password"
              v-model="Password"
            />
            <span class="title" data-placeholder="Password" />
          </div>
          <!--          确认密码-->
          <div :class="{ repeat: name !== 'SIGNUP' }" class="input">
            <input
              :class="{ f: repeatFocus || Repeat !== '' }"
              @blur="repeatFocus = false"
              @focus="repeatFocus = true"
              type="password"
              v-model="Repeat"
            />
            <span class="title" data-placeholder="Repeat" />
          </div>
          <!--          邮箱-->
          <div :class="{ email: name !== 'SIGNUP' }" class="input">
            <input
              :class="{ f: emailFocus || Email !== '' }"
              @blur="emailFocus = false"
              @focus="emailFocus = true"
              type="email"
              v-model="Email"
            />
            <span class="title" data-placeholder="Email" />
          </div>
          <!--          忘记密码-->
          <span class="forget"
            ><router-link to="/checking">Forget?</router-link></span
          >
          <!--          按钮-->
          <button @click="submit()">{{ name }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      name: "LOGIN",
      UserName: "",
      Password: "",
      Repeat: "",
      Email: "",

      userFocus: false,
      pwdFocus: false,
      repeatFocus: false,
      emailFocus: false
    };
  },
  methods: {
    submit() {
      if (this.name === "SIGNUP") {
        this.register();
      } else {
        this.login();
      }
    },
    // 注册
    register() {
      if (
        this.UserName === "" ||
        this.Password === "" ||
        this.Repeat === "" ||
        this.Email === ""
      ) {
        this.$notify({
          type: "error",
          title: "null",
          position: "top-left"
        });
        return false;
      }
      if (this.Password !== this.Repeat) {
        this.$notify({
          type: "error",
          title: "Password",
          message: "Passwords are inconsistent",
          position: "top-left"
        });
        return false;
      }
      axios({
        url: "http://localhost:8080/user/register",
        method: "post",
        params: {
          user: this.UserName,
          password: this.Password,
          email: this.Email
        }
      })
        .then(res => {
          console.log(res);
          if (res.data.code === 100) {
            this.$notify({
              type: "success",
              title: "Register",
              message: "register was successful",
              position: "top-left"
            });
            this.name = "LOGIN";
          } else {
            this.$notify({
              type: "error",
              title: "UserName",
              message: "repeat of user name",
              position: "top-left"
            });
          }
        })
        .catch(err => {
          console.log(err);
          this.$notify({
            type: "error",
            title: "Error",
            message: "Server exception",
            position: "top-left"
          });
        });
    },
    login() {
      if (this.UserName === "" || this.Password === "") {
        this.$notify({
          type: "error",
          title: "Error",
          message: "Null",
          position: "top-left"
        });
        return false;
      }
      axios({
        url: "http://localhost:8080/user/login",
        method: "post",
        params: {
          user: this.UserName,
          password: this.Password
        }
      })
        .then(res => {
          console.log(res);
          if (res.data.code === 100) {
            this.$notify({
              type: "success",
              title: "Login",
              message: "login was successful",
              position: "top-right",
              duration: 1500
            });
            window.localStorage.setItem("token", res.data.data);
            this.$router.push("/index");
          } else {
            this.$notify({
              type: "error",
              title: "Error",
              message: "Error Incorrect username or password",
              position: "top-left"
            });
          }
        })
        .catch(err => {
          console.log(err);
          this.$notify({
            type: "error",
            title: "Error",
            message: "Server exception",
            position: "top-left"
          });
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.login_box {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  /*模块*/
  .container {
    display: flex;
    width: 877px;
    min-width: 877px;
    height: 640px;
    border-radius: 7px;
    overflow: hidden;
    /*左侧图片*/
    .img_left {
      img {
        width: 32rem;
      }
    }
    /*右侧*/
    .panel {
      /*margin: 12rem 0 4rem;*/
      width: 365px;
      background: #fff;
      height: 100%;
      text-align: center;
      .img_top {
        margin: 20px 0 20px;
        img {
          width: 130px;
        }
      }
      /*文字导航*/
      .dev {
        span {
          color: #ccc;
          font-size: 1.4rem;
          cursor: pointer;
        }
      }
      .form {
        box-sizing: border-box;
        width: 100%;
        padding: 0 70px;
        margin: 2.3rem 0 0;
        .input {
          position: relative;
          opacity: 1;
          height: 2rem;
          width: 100%;
          margin: 1.9rem auto;
          transition: 0.4s;
          input {
            outline: none;
            width: 100%;
            border: none;
            border-bottom: 2px solid #95a5a6;
            background: none;
            z-index: 99999;
            height: 20px;
            font-size: 15px;
          }
          .title {
            &::before {
              content: attr(data-placeholder);
              font-size: 18px;
              color: #2c3e50;
              position: absolute;
              left: 0;
              top: -5px;
              transition: 0.3s;
            }
            &::after {
              content: "";
              width: 0;
              height: 2px;
              background: linear-gradient(120deg, #95a5a6, #34495e, #2c3e50);
              position: absolute;
              left: 0;
              bottom: 11px;
              z-index: 10000;
              transition: 0.3s;
            }
          }
        }
        .forget > * {
          position: relative;
          left: -83px;
          font-size: 13px;
          cursor: pointer;
          color: #2f3640;
        }
        button {
          border: none;
          outline: none;
          margin: 1.5rem 0 0;
          width: 100%;
          height: 3.5rem;
          border-radius: 3rem;
          background: linear-gradient(90deg, #636e72, #2d3436);
          cursor: pointer;
          color: white;
          transition: 0.4s;
          &:hover {
            box-shadow: 0 0 8px #2d3436;
          }
        }

        .repeat,
        .email {
          margin: 0 !important;
          height: 0 !important;
          opacity: 0 !important;
        }
      }
    }
  }
}
.active {
  color: #34495e !important;
}
.f + .title::after {
  width: 100% !important;
}

.f + .title::before {
  top: -25px !important;
  font-size: 15px !important;
}
.bz {
  width: 0 !important;
  height: 0 !important;
  opacity: 0 !important;
  /*transition: 0.3s !important;*/
}
@media screen and (max-width: 940px) {
  .container {
    width: 365px !important;
    min-width: 365px !important;
    justify-content: center;
  }
  .img_left {
    display: none;
  }
}
</style>
