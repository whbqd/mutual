<template>
  <div class="Homepage">
    <!--标题-->
    <h3 class="title">个人主页</h3>
    <!--个人标语-->
    <div class="top">
      <!--左侧图片+标语-->
      <div class="leftpage">
        <!--图片-->
        <img src="../assets/default_colleagues.jpg" alt="" />
        <!--标语-->
        <div class="wzright">
          <p>上午好, XXX, 准备吃什么呢?</p>
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
  </div>
</template>

<script>
export default {
  name: "Homepage"
};
</script>

<style lang="scss" scoped>
/*个人主页*/
.Homepage {
  padding: 0 9px 0;
  /*标题*/
  .title {
    border-left: 4px solid #353b48;
    color: #2f3640;
    height: 20px;
    line-height: 20px;
    margin-bottom: 10px;
    padding-left: 3px;
  }
  /*个人标语块*/
  .top {
    width: 100%;
    height: 150px;
    background-color: #2f3640;
    border-radius: 3px;
    display: flex;
    position: relative;
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
}
</style>
