<template>
  <div class="Homepage">
    <!--标题-->
    <h3 class="title">个人主页</h3>
    <!--个人标语-->
    <div class="top animated bounceInRight">
      <!--左侧图片+标语-->
      <div class="leftpage">
        <!--图片-->
        <img src="../assets/default_colleagues.jpg" alt="" />
        <!--标语-->
        <div class="wzright">
          <p>上午好, {{ username }}, 准备吃什么呢?</p>
          <span>大数据开发 | 某某某班 • 后台管理平台</span>
        </div>
      </div>
      <ul class="data">
        <li>
          <span class="Name">项目</span>
          <span class="count">1</span>
        </li>
        <li>
          <span class="Name">团队排名</span>
          <span class="count">0</span>
        </li>
        <li>
          <span class="Name">项目数</span>
          <span class="count">1</span>
        </li>
      </ul>
    </div>
    <div class="cent animated flipInX">
      <h3>正在进行中的项目</h3>
      <table class="project">
        <tr>
          <!--项目1 -> player-->
          <td>
            <div class="item">
              <div class="itemTop">
                <img src="../assets/timg.jpg" alt="" />
                <span>后台管理系统</span>
              </div>
              <div class="itemBottom">
                <span
                  >用户管理，商品管理</span
                >
                <a href="#" target="_blank">详情></a>
              </div>
            </div>
          </td>
          <!--项目2-->
          <td class="br l1t2d Null">
            空
          </td>
        </tr>
        <!--第二行-->
        <tr>
          <!--项目3-->
          <td class="Null">
            空
          </td>
          <!--项目4-->
          <td class="br Null">
            空
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "Homepage",
  data() {
    return {
      username: "用户名"
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
      this.username = res.data.data.user;
    });
  }
};
</script>

<style lang="scss" scoped>
/*个人主页*/
.Homepage {
  padding: 0;
  /*标题*/
  .title {
    border-left: 4px solid #353b48;
    color: #2f3640;
    height: 20px;
    font-size: 20px;
    line-height: 20px;
    margin-bottom: 10px;
    padding-left: 3px;
    margin-left: 8px;
    margin-top: 10px;
  }
  /*个人标语块*/
  .top {
    width: 100%;
    min-width: 950px;
    height: 150px;
    background-color: #2f3640;
    border-bottom-right-radius: 3px;
    border-top-right-radius: 3px;
    display: flex;
    position: relative;
    margin-bottom: 7px;
    /*左侧标语*/
    .leftpage {
      width: 500px;
      height: 150px;
      display: flex;
      float: left;
      /*图片*/
      img {
        height: 120px;
        border-radius: 50%;
        margin: auto 10px auto;
      }
      /*标语*/
      .wzright {
        height: 150px;
        line-height: 55px;
        margin-left: 10px;
        /*上方标语*/
        p {
          font-size: 21px;
          font-weight: bold;
          margin-top: 20px;
          margin-bottom: 5px;
          margin-left: 7px;
        }
        /*下方标语*/
        span {
          font-size: 15px;
          color: #dcdde1;
        }
      }
    }
    /*右侧数据*/
    .data {
      list-style: none;
      width: 530px;
      height: 150px;
      margin: auto 0;
      position: absolute;
      right: 0;
      padding: 10px;
      box-sizing: border-box;
      text-align: center;
      /*li*/
      li {
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        float: left;
        width: 125px;
        height: 90px;
        margin: 0 21px;
        line-height: 40px;
        /*名称*/
        .Name {
          display: block;
          color: #fff;
          font-weight: bold;
          font-size: 15px;
        }
        .count {
          font-size: 20px;
          font-weight: bold;
        }
      }
    }
  }
  /*项目块*/
  .cent {
    width: 590px;
    height: 391px;
    background-color: #2f3640;
    border-radius: 5px;
    margin-left: 7px;
    box-sizing: border-box;
    /*标题*/
    h3 {
      height: 50px;
      line-height: 38px;
      margin: 0;
      box-sizing: border-box;
      padding: 5px 7px;
    }
    /*表格*/
    .project {
      box-sizing: border-box;
      width: 590px;
      height: 300px;
      border-collapse: collapse;
      tr {
        box-sizing: border-box;
        width: 450px;
        height: 170px;
        td {
          box-sizing: border-box;
          padding: 10px;
          height: 150px;
          white-space: nowrap;
          width: 275px;
          border-right: 1px solid #576574;
          border-top: 1px solid #576574;
          &:hover {
            background-color: #485460;
          }
          &:hover .itemTop > img {
            width: 50px;
            transition: 0.2s;
          }
          &:hover .itemBottom > span {
            color: #d2dae2;
            transition: 0.3s;
          }
        }
        /*最外层右边框*/
        .br {
          border-right: none;
        }
      }
      .item {
        width: 275px;
        height: 150px;
        .itemTop {
          width: 100%;
          height: 40px;
          position: relative;
          img {
            width: 40px;
            border-radius: 50%;
          }
          span {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            left: 58px;
            font-weight: bold;
          }
        }
        .itemBottom {
          width: 275px;
          height: 100px;
          margin-top: 10px;
          position: relative;
          span {
            box-sizing: border-box;
            display: inline-block;
            margin: 20px 2px 0;
            width: 275px;
            height: 100px;
            font-size: 15px;
            white-space: normal;
            line-height: 30px;
          }
          a {
            color: #d1ccc0;
            text-decoration: none;
            position: absolute;
            bottom: 0;
            right: 0;
            &:hover {
              color: #aaa69d;
            }
          }
        }
      }
    }
  }
}
  .Null {
    font-size: 21px;
    text-align: center;
  }
</style>
