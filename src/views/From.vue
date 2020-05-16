<template>
  <div class="From">
    <!--标题-->
    <h3 class="title">表单</h3>
    <!--表单-->
    <div class="From_box">
      <el-form ref="form" :model="form" label-width="80px" class="From_el">
        <el-form-item label="姓名">
          <el-input v-model="form.name" class="form_input"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" style="width: 100px"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="form.tel" class="form_input" type="tel"></el-input>
        </el-form-item>
        <el-form-item label="出生年月">
          <el-col :span="11">
            <el-date-picker
                type="date"
                placeholder="选择日期"
                style="width: 100%;"
                v-model="form.date"
            ></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="优势">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="Vue" name="type"></el-checkbox>
            <el-checkbox label="Angular" name="type"></el-checkbox>
            <el-checkbox label="React" name="type"></el-checkbox>
            <el-checkbox label="Spring Boot" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="form.desc" style="width: 71%"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即完善</el-button>
          <el-button>重置</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
export default {
    data() {
        return {
            form: {

                //优势  复选框
                type: [],
                sex: "男"
            }
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
