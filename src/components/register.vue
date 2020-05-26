<template>
  <div class="reg animated jackInTheBox">
    <h4 class="title">注册</h4>
    <div class="icon">
      <el-tooltip
        class="item"
        effect="dark"
        content="返回登录页面"
        placement="top"
      >
        <Icon
          type="md-arrow-round-back"
          style="font-size: 20px;cursor: pointer"
          @click="ToLogin()"
        />
      </el-tooltip>
    </div>
    <template>
      <Form
        ref="formCustom"
        :model="formCustom"
        :rules="ruleCustom"
        :label-width="80"
        class="reg_from"
      >
        <FormItem label="Name" prop="name" style="margin-bottom: 24px;">
          <Input v-model="formCustom.name" placeholder="Enter your name" />
        </FormItem>
        <FormItem label="Passwd" prop="passwd" style="margin-bottom: 24px;">
          <Input type="password" v-model="formCustom.passwd" />
        </FormItem>
        <FormItem label="Confirm" prop="passwdCheck" style="margin-bottom: 24px;">
          <Input type="password" v-model="formCustom.passwdCheck" />
        </FormItem>
        <FormItem label="E-mail" prop="mail" style="margin-bottom: 24px;">
          <Input v-model="formCustom.mail" placeholder="Enter your e-mail" />
        </FormItem>
        <FormItem>
          <Button type="primary" @click="handleSubmit('formCustom')" style="width: 75px; height:32px"
            >Submit</Button
          >
          <Button @click="handleReset('formCustom')" style="margin-left: 8px;width: 75px; height:32px"
            >Reset</Button
          >
        </FormItem>
      </Form>
    </template>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Please enter your password"));
      } else {
        if (this.formCustom.passwdCheck !== "") {
          // 对第二个密码框单独验证
          this.$refs.formCustom.validateField("passwdCheck");
        }
        callback();
      }
    };
    const validatePassCheck = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("密码不为空"));
      } else if (value !== this.formCustom.passwd) {
        callback(new Error("密码不一致"));
      } else {
        callback();
      }
    };
    return {
      formCustom: {
        name: "",
        passwd: "",
        passwdCheck: "",
        mail: ""
      },
      ruleCustom: {
        passwd: [{ required: true, validator: validatePass, trigger: "blur" }],
        passwdCheck: [
          { required: true, validator: validatePassCheck, trigger: "blur" }
        ],
        name: [
          {
            required: true,
            message: "The name cannot be empty",
            trigger: "blur"
          }
        ],
        mail: [
          {
            required: true,
            message: "Mailbox cannot be empty",
            trigger: "blur"
          },
          { type: "email", message: "Incorrect email format", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    // 返回登录页面
    ToLogin() {
      this.$router.push("/login");
    },
    handleSubmit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          axios({
            url: "http://localhost:8080/user/register",
            method: "post",
            params: {
              user: this.formCustom.name,
              password: this.formCustom.passwd,
              email: this.formCustom.mail
            }
          }).then(res => {
            console.log(res);
            if (res.data.code === 100) {
              this.$Message.success(res.data.message);
              this.$router.push("/login");
            } else {
              this.$Message.error(res.data.message);
            }
          });
        } else {
          this.$Message.error("Fail!");
        }
      });
    },
    handleReset(name) {
      this.$refs[name].resetFields();
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
  width: 350px;
  height: 480px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -175px;
  margin-top: -240px;
  padding: 20px 38px 34px 20px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.8);
  display: flex;
  .title {
    position: absolute;
    left: 50%;
    top: 55px;
    transform: translateX(-50%);
    font-size: 21px;
  }
  .reg_from {
    margin-top: 120px;
  }
  .icon {
    position: absolute;
  }
}
</style>
