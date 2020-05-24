<template>
  <div class="index">
    <!--上-->
    <div class="top">
      <span class="title"
        ><span class="el-icon-s-tools"></span>后台管理系统</span
      >
      <div class="userImg">
        <span class="out" @click="outLogin">退出登录</span>
        <img src="../assets/default_colleagues.jpg" alt="" />
        <span>{{ UserName }}</span>
      </div>
    </div>
    <!--中-->
    <div class="container">
      <!--左侧-->
      <div class="left">
        <!--个人主页-->
        <div class="homepage">
          <router-link to="/index/Homepage" class="Personal"
            ><span class="el-icon-s-custom icon" /> 个人主页</router-link
          >
        </div>
        <!--功能区1-->
        <div class="t1">
          <ul class="gn">
            <li class="li1" @click="gn1Hide()">
              <span class="gn1Icon1 el-icon-menu"></span>用户
              <span class="gn1Icon2 el-icon-arrow-down"></span>
            </li>
            <li class="li2">
              <router-link to="/index/Table" class="tb">Table</router-link>
            </li>
            <li class="li2">
              <router-link to="/index/From" class="tb">From</router-link>
            </li>
            <li class="li2">
              <router-link to="/index/List" class="tb">列表</router-link>
            </li>
          </ul>
        </div>
        <!-- 功能区2 -->
        <div class="t1">
          <ul class="gn">
            <li class="li1" @click="gn2Hide()">
              <span class="gn2Icon1 el-icon-s-data"></span>商品
              <span class="gn2Icon2 el-icon-arrow-down"></span>
            </li>
            <li class="gn2li2">
              <router-link to="/index/commodity">商品管理</router-link>
            </li>
            <li class="gn2li2">
              <router-link to="#####">组件2</router-link>
            </li>
            <li class="gn2li2">
              <router-link to="######">组件3</router-link>
            </li>
          </ul>
        </div>
      </div>
      <!--右侧主体-->
      <div class="main">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";
export default {
  data() {
    return {
      // 功能1与2的点击图标转换判断
      gn1n: true,
      gn2n: true,
      // 用户名
      UserName: ""
    };
  },
  created() {
    axios({
      url: "http://localhost:8080/user/view/getUserOfLogin",
      method: "post",
      headers: {
        token: window.localStorage.getItem("token")
      }
    }).then(res => {
      this.UserName = res.data.data.user;
    });
  },
  methods: {
    // 功能1与2的下拉效果及图标转换
    gn1Hide() {
      $(".li2").slideToggle(300);
      if (this.gn1n) {
        $(".gn1Icon2").removeClass("el-icon-arrow-down");
        $(".gn1Icon2").addClass("el-icon-arrow-right");
        this.gn1n = false;
      } else {
        $(".gn1Icon2").removeClass("el-icon-arrow-right");
        $(".gn1Icon2").addClass("el-icon-arrow-down");
        this.gn1n = true;
      }
    },
    gn2Hide() {
      $(".gn2li2").slideToggle(300);
      if (this.gn2n) {
        $(".gn2Icon2").removeClass("el-icon-arrow-down");
        $(".gn2Icon2").addClass("el-icon-arrow-right");
        this.gn2n = false;
      } else {
        $(".gn2Icon2").removeClass("el-icon-arrow-right");
        $(".gn2Icon2").addClass("el-icon-arrow-down");
        this.gn2n = true;
      }
    },
    // 退出登录点击事件
    outLogin() {
      axios({
        url: "http://localhost:8080/user/view/logout",
        method: "post",
        headers: {
          token: window.localStorage.getItem("token")
        }
      }).then(res => {
        console.log(res);
        if (res.data.code === 100) {
          this.$message.success(res.data.message);
        } else {
          this.$message.error(res.data.message);
        }
      });
      // 清空token
      window.localStorage.removeItem("token");
      // 跳转到登录页
      this.$router.push("/login");
    }
  }
};
</script>

<style lang="scss" scoped>
* {
  color: #f5f6fa;
  text-decoration: none;
}
.index {
  height: 100%;
  box-sizing: border-box;
  /*上*/
  .top {
    height: 50px;
    line-height: 50px;
    background-color: #353b48;
    /*标题*/
    .title {
      font-size: 18px;
      font-weight: bold;
      margin-left: 10px;
      /*icon*/
      span {
        font-size: 18px;
        margin-right: 3px;
      }
    }

    /*用户图片及用户名 退出登录*/
    .userImg {
      display: flex;
      float: right;
      margin-right: 5px;
      height: 50px;
      /*退出登录*/
      .out {
        font-size: 14px;
        margin-right: 10px;
        cursor: pointer;
        &:hover {
          color: #ced6e0;
        }
      }
      /*用户图片*/
      img {
        height: 40px;
        border-radius: 50%;
        margin: auto 0;
      }

      /*用户名*/
      span {
        font-size: 15px;
        margin-left: 5px;
      }
    }
  }
  /*中*/
  .container {
    height: 100%;
    display: flex;
    /*左侧*/
    .left {
      background-color: #1e272e;
      width: 160px;
      /*个人主页*/
      .homepage {
        width: 100%;
        height: 25px;
        line-height: 25px;
        margin: 20px 0;
        text-align: center;
        box-sizing: border-box;
        /*文字*/
        .Personal {
          display: inline-block;
          width: 100%;
          position: relative;
          left: -8px;
          font-size: 15px;
          /*图标*/
          .icon {
            font-size: 18px;
            position: relative;
            left: 1px;
          }
        }
        .router-link-active {
          color: #7d5fff;
        }
      }
      /*功能区1*/
      .t1 {
        width: 160px;
        /*ul*/
        .gn {
          list-style: none;
          margin: 20px 0 0;
          padding: 0;
          text-align: center;
          box-sizing: border-box;
          /*li 父*/
          .li1 {
            height: 20px;
            font-size: 16px;
            line-height: 20px;
            cursor: pointer;
            margin-bottom: 15px;
            /*功能1 icon1*/
            .gn1Icon1 {
              font-size: 16px;
              margin-right: 3px;
            }

            /*功能1 icon2*/
            .gn1Icon2 {
              position: relative;
              left: 15px;
            }

            /*功能2 icon1*/
            .gn2Icon1 {
              font-size: 16px;
              margin-right: 3px;
            }

            /*功能2 icon2*/
            .gn2Icon2 {
              position: relative;
              left: 15px;
            }
          }

          /*功能1 li 子*/
          .li2 {
            height: 40px;
            line-height: 40px;
            margin-left: -15px;
            background-color: #2f3136;
            cursor: pointer;
            font-weight: bold;
            font-size: 15px;
            /*li的选中状态*/
            .router-link-active {
              color: #7d5fff;
            }
            .tb {
              display: inline-block;
              width: 100%;
            }
          }

          /*功能2 li 子*/
          .gn2li2 {
            height: 40px;
            line-height: 40px;
            margin-left: -15px;
            background-color: #2f3136;
            cursor: pointer;
            font-weight: bold;
            font-size: 15px;
            /*li的选中状态*/
            .router-link-active {
              color: #7d5fff;
            }
          }
        }
      }
    }
    /*右侧主体*/
    .main {
      background-color: #576574;
      flex: 1;
      /*组件名称*/
      .ComName {
        margin: 10px 10px 0;
        border-left: 4px solid #353b48;
        color: #2f3640;
        height: 20px;
        line-height: 20px;
        padding-left: 5px;
      }
    }
  }
}
</style>
