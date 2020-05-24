<template>
  <div class="commodity">
    <div class="top">
      <h3 class="title">商品管理</h3>
      <el-button type="danger" class="add">添加商品</el-button>
    </div>
    <table class="sp_table el-table">
      <thead>
        <td>#</td>
        <td>id</td>
        <td>订单编号</td>
        <td>商品名称</td>
        <td>价格</td>
        <td>类型</td>
        <td>
          操作
        </td>
      </thead>
      <tbody>
        <tr class="sp_tr" v-for="(items, index) in GoodsList" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ items.id }}</td>
          <td>{{  items.number }}</td>
          <td>{{ items.name }}</td>
          <td>{{ items.price }}</td>
          <td>{{ items.type }}</td>
          <td>
            <el-button type="primary" icon="el-icon-edit" circle></el-button>
            <el-button type="danger" icon="el-icon-delete" circle></el-button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="page">
      <el-pagination
        class="page_el"
        background
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :page-count="lastPage"
        :hide-on-single-page="!(lastPage > 1)"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      //商品列表
      GoodsList: [],
      //页码
      pageNow: 0,
      //一页显示数量
      pageSize: 9,
      //页数
      lastPage: 10,
      value: false
    };
  },
  created() {
    this.InitGetList();
  },
  methods: {
    InitGetList() {
      axios({
        url: "http://localhost:8080/goods/queryAllGoods",
        method: "post",
        headers: {
          token: window.localStorage.getItem("token")
        },
        params: {
          pageNow: 1,
          pageSize: this.pageSize
        }
      }).then(res => {
        console.log(res);
        if (res.data.code === 100) {
          this.GoodsList = res.data.data.list;
          this.lastPage = res.data.data.lastPage;
        }
      });
    },
    //分页栏
    handleCurrentChange(val) {
      console.log(`当前页码${val}`);
      this.getList(val);
    },
    //返回商品列表(页码)
    getList(pageNow) {
      axios({
        url: "http://localhost:8080/goods/queryAllGoods",
        method: "post",
        headers: {
          token: window.localStorage.getItem("token")
        },
        params: {
          pageNow,
          pageSize: this.pageSize
        }
      }).then(res => {
        console.log(res);
        if (res.data.code === 100) {
          this.GoodsList = res.data.data.list;
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.commodity {
  box-sizing: border-box;
  padding: 20px 20px;
  height: 100%;
  /*头部*/
  .top {
    display: flex;
    width: 100%;
    height: 70px;
    justify-content: space-between;
    /*标题*/
    .title {
      margin: 0 0 10px;
      border-left: 4px solid #2f3640;
      color: #2f3640;
      height: 20px;
      line-height: 20px;
      padding-left: 5px;
      margin: auto 0;
    }
    /*添加商品按钮*/
    .add {
      height: 45px;
      width: 155px;
      margin: auto 0;
      border: none;
      background: #353b48;
      &:hover {
        background: #2c3a47;
      }
    }
  }
  /*表格*/
  .sp_table {
    border: none;
    background: #1e272e;
    border-radius: 3px;
    td {
      text-align: center;
      background: #1e272e;
      color: #fff;
      border-bottom: none;
    }
  }
  /*分页*/
  .page {
    display: flex;
    .page_el {
      margin: 10px auto;
      border: none !important;
      li {
        background: #a29bfe !important;
      }
    }
  }
}
.el-table--border::after,
.el-table--group::after,
.el-table::before {
  background: none;
}
</style>
