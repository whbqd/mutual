<template>
  <div class="UserAll">
    <h1>DATA</h1>
    <table class="el-table User" v-if="UserList.length !== 0">
      <thead>
        <td>index</td>
        <td>id</td>
        <td>name</td>
        <td>password</td>
        <td>email</td>
        <td>DELETE</td>
      </thead>
      <tbody>
        <tr v-for="(items, index) in UserList" :key="index" class="UserData">
          <td>{{ index + 1 }}</td>
          <td>{{ items.id }}</td>
          <td>{{ items.user }}</td>
          <td>{{ items.password }}</td>
          <td>{{ items.email }}</td>
          <td>
            <span
              class="el-icon-error"
              @click="cutout(items.id)"
              title="删除"
            />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    import axios from "axios";

    export default {
  name: "UserAll",
  data() {
    return {
      UserList: []
    };
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      axios({
        url: "http://localhost:8080/Backend/UserAll",
        methods: "get"
      })
        .then(res => {
          console.log("#", res);
          this.UserList = res.data.userList;
        })
        .catch(err => {
          err;
          this.$message.error("服务器异常！");
        });
    },
    cutout(id) {
      axios({
        url: "http://localhost:8080/Backend/delete",
        methods: "get",
        params: {
          id
        }
      })
        .then(res => {
          console.log("respID", res);
          if (res.data.msg) {
            this.$message.success("删除成功！");
          } else {
            this.$message.error("删除失败!");
          }
          this.getData();
        })
        .catch(err => {
          err;
          this.$message.error("服务器异常！");
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.UserAll {
  width: 1000px;
  margin: 0 auto;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
.User {
  border-radius: 10px;
  td {
    text-align: center;
  }
  .UserData {
    span {
      font-size: 25px;
      cursor: pointer;
      &:hover {
        color: #ff7675;
      }
    }
  }
}
</style>
