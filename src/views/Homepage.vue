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
          <span>前端工程师 | 某某某事业群 • VUE平台</span>
        </div>
      </div>
      <ul class="data">
        <li>
          <span class="Name">项目</span>
          <span class="count">56</span>
        </li>
        <li>
          <span class="Name">团队排名</span>
          <span class="count">8/24</span>
        </li>
        <li>
          <span class="Name">项目数</span>
          <span class="count">2,223</span>
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
                <span>player</span>
              </div>
              <div class="itemBottom">
                <span
                  >音乐播放器 - 网易云Api。用音乐歌唱青春,用心灵创造生活</span
                >
                <a href="https://www.whbqd.xyz/player" target="_blank">详情></a>
              </div>
            </div>
          </td>
          <!--项目2-->
          <td class="br l1t2d">
            <div class="item">
              <div class="itemTop">
                <img src="../assets/wyy.png" alt="" />
                <span>网易云</span>
              </div>
              <div class="itemBottom">
                <span>非cli项目，播放器, 天气预报</span>
                <a
                  href="https://www.whbqd.xyz/vue"
                  target="_blank"
                  style="right: 50px"
                  >music></a
                >
                <a href="https://www.whbqd.xyz/vue/weather.html" target="_blank"
                  >天气></a
                >
              </div>
            </div>
          </td>
        </tr>
        <!--第二行-->
        <tr>
          <!--项目3-->
          <td>
            <div class="item">
              <div class="itemTop">
                <img src="../assets/github.jpg" alt="" />
                <span>GitHub</span>
              </div>
              <div class="itemBottom">
                <span>Java前后端分离源码</span>
                <a
                  href="https://github.com/whbqd/mutual/tree/Api"
                  target="_blank"
                  >详情></a
                >
              </div>
            </div>
          </td>
          <!--项目4-->
          <td class="br">
            <div class="item">
              <div class="itemTop">
                <img src="../assets/vue2.png" alt="" />
                <span>login</span>
              </div>
              <div class="itemBottom">
                <span>用户登录 - 前后端分离。</span>
                <a href="https://www.whbqd.xyz/login" target="_blank">详情></a>
              </div>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  name: "Homepage",
  data() {
    return {
      username: window.sessionStorage.getItem("username")
    };
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
    line-height: 20px;
    margin-bottom: 10px;
    padding-left: 3px;
    margin-left: 8px;
  }
  /*个人标语块*/
  .top {
    width: 100%;
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
    height: 390px;
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
      width: 550px;
      height: 300px;
      border-collapse: collapse;
      tr {
        box-sizing: border-box;
        width: 450px;
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
</style>
