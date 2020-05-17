<template>
  <div class="From">
    <!--标题-->
    <h3 class="title">表单</h3>
    <!--表单-->
    <div class="From_box">
      <h1>{{ username }}</h1>
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="80px"
        class="From_el"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" class="form_input"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="年龄"
          prop="age"
          :rules="[
            { required: true, message: '年龄不能为空' },
            { type: 'number', message: '年龄必须为数字值' }
          ]"
        >
          <el-input v-model.number="form.age" style="width: 100px"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="form.tel" class="form_input" type="tel"></el-input>
        </el-form-item>
        <el-form-item label="出生年月" prop="date">
          <el-col :span="11">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              style="width: 100%;"
              v-model="form.date"
            ></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="优势" prop="type">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="Vue" name="type"></el-checkbox>
            <el-checkbox label="Angular" name="type"></el-checkbox>
            <el-checkbox label="React" name="type"></el-checkbox>
            <el-checkbox label="Spring Boot" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="地址" prop="desc">
          <el-input
            type="textarea"
            v-model="form.desc"
            style="width: 71%"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit('form')"
            >立即完善</el-button
          >
          <el-button @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: window.sessionStorage.getItem("username"),
      form: {
        // 姓名
        name: "",
        // 性别
        sex: "男",
        // 年龄
        age: "",
        // 联系电话
        tel: "",
        // 出生年月
        date: "",
        //优势
        type: [],
        // 地址
        desc: ""
      },
      // 约束
      rules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 1, max: 8, message: "长度在 1 到 8 个字符", trigger: "blur" }
        ],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        tel: [
          {
            required: true,
            message: "电话不能为空",
            trigger: "blur"
          }
        ],
        type: [
          {
            type: "array",
            required: true,
            message: "请至少选择一个特长",
            trigger: "change"
          }
        ],
        desc: [{ required: true, message: "请填写地址", trigger: "blur" }],
        date: [
          {
            type: "date",
            required: true,
            message: "请选择日期",
            trigger: "change"
          },
          {
            type: "date",
            required: true,
            message: "请选择日期",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    // 提交
    onSubmit(form) {
      this.$refs[form].validate(valid => {
        if (valid) {
          console.log(valid);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 重置
    resetForm(form) {
      this.$refs[form].resetFields();
    }
  }
};
</script>

<style lang="scss" scoped>
.From {
  box-sizing: border-box;
  height: 100%;
  padding: 15px 10px;
  /*标题*/
  .title {
    margin: 0 0 10px;
    border-left: 4px solid #2f3640;
    color: #2f3640;
    height: 20px;
    line-height: 20px;
    padding-left: 4px;
  }
  /*表单*/
  .From_box {
    padding: 20px 15px 0;
    width: 580px;
    height: 85%;
    background-color: #fff;
    border-radius: 6px;
    position: relative;
    .From_el {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      .form_input {
        width: 100%;
      }
    }
  }
}
</style>
