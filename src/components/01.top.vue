<template>
    <div class="top">
        <div class="se">
            <i class="el-icon-search"></i>
            <input type="text" v-model="inputValue" @keyup.enter="toSearch" placeholder="搜索">
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                inputValue:''
            }
        },
        methods:{
            toSearch(){
                if(this.inputValue == "") {
                    alert('空')
                } else {
                    this.$router.push(`/result?key=${this.inputValue}`)
                }

            }
        }

    }
</script>


<style>
    .top {
        width: 100%;
        height: 60px;
        background-color: #f9f9f9;
        box-sizing: border-box;
    }
    .se {
        width: 190px;
        height: 60px;
        float: right;
        position: relative;
    }
    .se [type="text"] {
        width: 130px;
        height: 5px;
        position: absolute;
        top: 50%;
        right: 60px;
        transform: translateY(-50%);
        padding: 10px 28px;
    }
    .se i {
        position: absolute;
        top: 50%;
        right: 225px;
        transform: translateY(-50%);
        z-index: 3;
    }
</style>
