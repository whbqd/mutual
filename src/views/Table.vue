<template>
  <div class="Table">
    <div class="user">
      <table class="el-table tableUser">
        <thead>
          <td>#</td>
          <td>id</td>
          <td>用户名</td>
          <td>密码</td>
          <td>邮箱</td>
          <td>删除</td>
          <td>修改</td>
        </thead>
        <tbody>
          <tr v-for="(items, index) in UserList" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ items.id }}</td>
            <td>{{ items.user }}</td>
            <td>{{ items.password }}</td>
            <td>{{ items.email }}</td>
            <!--删除-->
            <td>
              <el-button
                class="del"
                type="primary"
                icon="el-icon-delete"
                @click="delClick(items.id)"
                :title="items.user"
              ></el-button>
            </td>
            <!--编辑-->
            <td>
              <el-button
                class="update"
                type="primary"
                icon="el-icon-edit"
                @click="reviseClick(items.id)"
              ></el-button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!--修改-->
    <div class="modify">
      <!--标题-->
      <h3 class="title">编辑</h3>
      <h2 class="foot">{{ id }}</h2>
      <!--input块-->
      <div class="ite">
        <span><i>*</i> 用户名</span>
        <el-input
          class="inputV"
          :placeholder="oneUserList.user"
          v-model="user"
          clearable
        >
        </el-input>
      </div>
      <div class="ite">
        <span><i>*</i> 密码</span>
        <el-input
          class="inputV"
          :placeholder="oneUserList.password"
          v-model="password"
          show-password
        ></el-input>
      </div>
      <div class="ite">
        <span><i>*</i> 邮箱</span>
        <el-input
          class="inputV"
          :placeholder="oneUserList.email"
          v-model="email"
          clearable
        >
        </el-input>
      </div>
      <!--底部按钮-->
      <div class="submit">
        <el-button @click="CallOff()" class="qx">取消</el-button>
        <el-button type="primary" class="xg" @click="revise(id)"
          >修改</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import $ from "jquery";
export default {
  data() {
    return {
      // 用户数组
      UserList: [],
      // 用户id
      id: 0,
      //修改框 => 用户名
      user: "",
      // 密码
      password: "",
      // 邮箱
      email: "",
      isDisabled: false,
      //点击编辑的单用户数组
      oneUserList: []
    };
  },
  created() {
    this.getUserAll();
  },
  methods: {
    //获取全部信息
    getUserAll() {
      axios({
        url: "https://www.whbqd.xyz/Login/UserAll",
        methods: "get"
      })
        .then(res => {
          this.UserList = res.data.userList;
          console.log(res);
        })
        .catch(err => {
          err;
          this.$message.error("服务器超时");
        });
    },
    delClick(id) {
      this.$confirm("确认删除吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log(id);
          this.cutout(id);
        })
        .catch(() => {
          console.log("取消删除");
        });
    },
    // 根据id删除行
    cutout(id) {
      axios({
        url: "https://www.whbqd.xyz/Login/delete",
        methods: "get",
        params: {
          id
        }
      })
        .then(res => {
          console.log(res);
          if (res.data.msg) {
            this.$message.success("删除成功!!!");
            this.getUserAll();
          } else {
            this.$message.error("删除失败!!!");
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("服务器超时!!!");
        });
    },
    // 修改
    reviseClick(id) {
      $(".modify").fadeToggle(300);
      this.id = id;
      axios({
        url: "https://www.whbqd.xyz/Login/idAll",
        methods: "post",
        params: {
          id
        }
      })
        .then(res => {
          console.log(res);
          this.oneUserList = res.data;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("服务器超时！");
        });
    },
    // 取消
    CallOff() {
      this.user = "";
      this.password = "";
      this.email = "";
      $(".modify").fadeToggle(300);
    },
    //确认
    revise(id) {
      if (this.user === "" || this.password === "" || this.email === "") {
        this.$message({
          type: "warning",
          showClose: true,
          message: "信息不全!!!"
        });
        return false;
      }
      // 修改 信息
      axios({
        url: "https://www.whbqd.xyz/Login/reviseByid",
        methods: "post",
        params: {
          id,
          user: this.user,
          password: this.password,
          email: this.email
        }
      })
        .then(res => {
          console.log(res);
          if (res.data) {
            this.$message.success("修改成功!");
            $(".modify").fadeToggle(300);
            this.getUserAll();
            this.user = "";
            this.password = "";
            this.email = "";
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("服务器超时！");
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
.Table {
  box-sizing: border-box;
  padding: 10px;
  height: 668px;
  position: relative;
  /*表格div*/
  .user {
    overflow-x: hidden;
    overflow-y: auto;
    height: 595px;
    &::-webkit-scrollbar {display:none}
    /*表格*/
    .tableUser {
      border-radius: 4px;
      box-shadow: 0 0 15px #57606f;
      td {
        text-align: center;
      }
      /*删除标志*/
      .del {
        width: 55px;
        height: 39px;
        background-color: #2d3436;
        border-color: #2d3436;
        &:hover {
          background-color: #57606f;
          border-color: #57606f;
        }
      }

      /*修改标志*/
      .update {
        width: 55px;
        height: 39px;
        background-color: #2d3436;
        border-color: #2d3436;
        &:hover {
          background-color: #57606f;
          border-color: #57606f;
        }
      }
    }
  }
  /*修改界面*/
  .modify {
    display: none;
    width: 400px;
    height: 400px;
    box-sizing: border-box;
    padding: 50px 30px 10px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    border-radius: 5px;
    color: #000;
    box-shadow: 3px 3px 10px #57606f;
    z-index: 999;
    /*标题*/
    .title {
      position: relative;
      top: -20px;
      height: 20px;
      line-height: 20px;
      border-left: 4px solid #2d3436;
      padding-left: 5px;
    }
    /*id标识*/
    .foot {
      position: absolute;
      right: 10px;
      top: 8px;
      height: 20px;
      line-height: 20px;
      color: #57606f;
    }
    //行输入块
    .ite {
      height: 80px;
      line-height: 80px;
      /*字体*/
      span {
        font-size: 14px;
        //*
        i {
          color: #ff6b81;
        }
      }

      /*输入框*/
      .inputV {
        width: 250px;
        position: absolute;
        right: 50px;
      }
    }

    /*底部按钮*/
    .submit {
      position: absolute;
      right: 50px;
      bottom: 30px;
      .qx,
      .xg {
        width: 70px;
        height: 39px;
      }
    }
  }
}
</style>
