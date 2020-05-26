<template>
  <div class="commodity">
    <div class="top">
      <h3 class="title">商品管理</h3>
      <Button @click="value3 = true" type="primary" icon="md-add" class="add"
        >Create</Button
      >
    </div>
    <!--    添加-->
    <template>
      <div>
        <Drawer
          title="Create"
          v-model="value3"
          width="720"
          :mask-closable="false"
          :styles="styles"
        >
          <Form :model="formData">
            <Row :gutter="32">
              <Col span="12">
                <FormItem label="订单编号" label-position="top">
                  <Input
                    v-model="formData.number"
                    placeholder="请输入订单编号"
                  />
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem label="商品名称" label-position="top">
                  <Input v-model="formData.name" placeholder="请输入商品名称" />
                </FormItem>
              </Col>
            </Row>
            <Row :gutter="32">
              <Col span="12">
                <FormItem label="价格(RMB)" label-position="top">
                  <Input
                    v-model="formData.price"
                    placeholder="请输入商品价格"
                  />
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem label="Type" label-position="top">
                  <Select
                    v-model="formData.type"
                    placeholder="please choose the type"
                  >
                    <Option value="文具">文具</Option>
                    <Option value="生活用品">生活用品</Option>
                    <Option value="数码">数码</Option>
                    <Option value="美食">美食</Option>
                    <Option value="其他">其他</Option>
                  </Select>
                </FormItem>
              </Col>
            </Row>
          </Form>
          <div class="demo-drawer-footer">
            <Button style="margin-right: 8px" @click="outCancel()"
              >Cancel</Button
            >
            <Button type="primary" @click="submitGoods()">Submit</Button>
          </div>
        </Drawer>
      </div>
    </template>

    <!--    表格-->
    <template>
      <Table :columns="columns" :data="GoodsList">
        <template slot-scope="{ row, index }" slot="number">
          <Input type="text" v-model="editNumber" v-if="editIndex === index" />
          <span v-else>{{ row.number }}</span>
        </template>

        <template slot-scope="{ row, index }" slot="name">
          <Input type="text" v-model="editName" v-if="editIndex === index" />
          <span v-else>{{ row.name }}</span>
        </template>

        <template slot-scope="{ row, index }" slot="price">
          <Input type="text" v-model="editPrice" v-if="editIndex === index" />
          <span v-else>{{ row.price }}</span>
        </template>

        <template slot-scope="{ row, index }" slot="type">
          <Input type="text" v-model="editType" v-if="editIndex === index" />
          <span v-else>{{ row.type }}</span>
        </template>

        <template slot-scope="{ row, index }" slot="action">
          <div v-if="editIndex === index">
            <Button @click="handleSave(index)">保存</Button>
            <Button @click="editIndex = -1">取消</Button>
          </div>
          <div v-else>
            <Button type="info" @click="handleEdit(row, index)" style="margin-right: 5px;">修改</Button>
            <Button type="error" @click="DelGoods(row)">删除</Button>
          </div>
        </template>
      </Table>
    </template>
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
      pageSize: 10,
      //页数
      lastPage: 10,
      value: false,
      // 表格
      columns: [
        {
          title: "Id",
          key: "id"
        },
        {
          title: "订单编号",
          slot: "number"
        },
        {
          title: "商品名",
          slot: "name"
        },
        {
          title: "价格",
          slot: "price"
        },
        {
          title: "类型",
          slot: "type"
        },
        {
          title: "操作",
          slot: "action",
            align: 'center'
        }
      ],
      editIndex: -1, // 当前聚焦的输入框的行数
      editId: "", // 第一列输入框，当然聚焦的输入框的输入内容，与 data 分离避免重构的闪烁
      editNumber: "", // 第二列输入框
      editName: "", // 第三列输入框
      editPrice: "", // 第四列输入框
      editType: "", // 第五列输入框
      //添加
      value3: false,
      styles: {
        height: "calc(100% - 55px)",
        overflow: "auto",
        paddingBottom: "53px",
        position: "static"
      },
      formData: {
        number: "",
        name: "",
        price: "",
        type: ""
      }
    };
  },
  created() {
    this.InitGetList();
  },
  methods: {
    // 取消添加商品
    outCancel() {
      this.formData = {};
      this.value3 = false;
    },
    // 添加商品
    submitGoods() {
      if (
        this.formData.number !== "" &&
        this.formData.name !== "" &&
        this.formData.price !== "" &&
        this.formData.type !== ""
      ) {
        axios({
          url: "http://localhost:8080/goods/addGoods",
          method: "post",
          params: {
            number: this.formData.number,
            name: this.formData.name,
            price: this.formData.price,
            type: this.formData.type
          }
        }).then(res => {
          console.log(res);
          if (res.data.code === 100) {
            this.$Message.success(res.data.message);
            this.value3 = false;
            this.formData = {};
            this.InitGetList();
          } else {
            this.$Message.error(res.data.message);
            this.value3 = true;
          }
        });
      } else {
          this.$notify({
              title: '错误',
              message: '不允许有空值',
              type:"error",
              position: 'top-left'
          });
      }
    },
    //删除
    DelGoods(row) {
      axios({
        url: "http://localhost:8080/goods/delNumber",
        method: "post",
        params: {
          number: row.number
        }
      }).then(res => {
        console.log(res);
        if (res.data.code === 100) {
          this.$Message.success(res.data.message);
          this.InitGetList();
        } else {
          this.$Message.success(res.data.message);
        }
      });
    },

    handleEdit(row, index) {
      this.editId = row.id;
      this.editNumber = row.number;
      this.editName = row.name;
      this.editPrice = row.price;
      this.editType = row.type;
      this.editIndex = index;
    },
    handleSave(index) {
      axios({
        url: "http://localhost:8080/goods/updateGoods",
        method: "post",
        params: {
          number: this.editNumber,
          name: this.editName,
          price: this.editPrice,
          type: this.editType,
          id: this.editId
        }
      }).then(res => {
        console.log(res);
        if (res.data.code === 100) {
          this.$Message.success(res.data.message);
          this.GoodsList[index].number = this.editNumber;
          this.GoodsList[index].name = this.editName;
          this.GoodsList[index].price = this.editPrice;
          this.GoodsList[index].type = this.editType;
          this.editIndex = -1;
        } else {
          this.$Message.error(res.data.message);
          this.editIndex = -1;
        }
      });
    },
    // 查询列表（初始化）
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
          this.lastPage = res.data.data.lastPage;
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
      font-size: 15px;
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
.demo-drawer-footer {
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0;
  border-top: 1px solid #e8e8e8;
  padding: 10px 16px;
  text-align: right;
  background: #fff;
}
</style>
