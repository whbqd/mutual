<template>
    <div class="mvs">
<!--        分类-->
        <div class="wrap">
            <div class="region-wrap clearfix">
                <span>地区：</span>
                <ul>
                    <li><span :class="{action:dq=='全部'}" @click="dq='全部'">全部</span></li>
                    <li><span :class="{action:dq=='内地'}" @click="dq='内地'">内地</span></li>
                    <li><span :class="{action:dq=='港台'}" @click="dq='港台'">港台</span></li>
                    <li><span :class="{action:dq=='欧美'}" @click="dq='欧美'">欧美</span></li>
                    <li><span :class="{action:dq=='日本'}" @click="dq='日本'">日本</span></li>
                    <li class="none"><span class="none" :class="{action:dq=='韩国'}" @click="dq='韩国'">韩国</span></li>
                </ul>
            </div>
            <div class="type-wrap clearfix">
                <span>类型：</span>
                <ul>
                    <li><span :class="{action:lx=='全部'}" @click="lx='全部'">全部</span><li>
                    <li><span :class="{action:lx=='官方版'}" @click="lx='官方版'">官方版</span></li>
                    <li><span :class="{action:lx=='原声'}" @click="lx='原声'">原声</span><li>
                    <li><span :class="{action:lx=='现场版'}" @click="lx='现场版'">现场版</span></li>
                    <li class="none"><span :class="{action:lx=='网易出品'}" @click="lx='网易出品'">网易出品</span></li>
                </ul>
            </div>
            <div class="order-wrap clearfix">
                <span>排序：</span>
                <ul>
                    <li><span :class="{action:px=='上升最快'}" @click="px='上升最快'">上升最快</span></li>
                    <li><span :class="{action:px=='最热'}" @click="px='最热'">最热</span></li>
                    <li class="none"><span :class="{action:px=='最新'}" @click="px='最新'">最新</span></li>
                </ul>
            </div>
        </div>
<!--        MV-->
        <div class="items">
            <div class="item" v-for="item in MvList">
            <div class="itemIMG" @click="toMv(item.id,item.artistName,item.name,item.playCount,item.cover)">
                <img :src="item.cover" alt="">
                <div class="count">
                    <i class="el-icon-caret-right"></i>
                    <span>{{ item.playCount }}</span>
                </div>

            </div>
            <p class="name">{{ item.name }}</p>
        </div>
        </div>
<!--        分页-->
        <div class="bt">
            <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="total"
                    :current-page="page"
                    @current-change="change">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
         data() {
             return {
                 dq : '全部',
                 lx : '全部',
                 px : '上升最快',
                 MvList : [],
                 total : 20, //总条数
                 page : 1, //页码
                 limit: 12
             }
         },
        watch : {
            dq(){
                this.page=1;
                this.getMv()
            },
            lx(){
                this.page=1;
                this.getMv()
            },
            px(){
                this.page=1;
                this.getMv()
            }
        },
        created() {
            this.getMv()
        },
        methods:{
             getMv(){
                 axios ({
                     url:'https://autumnfish.cn/mv/all',
                     methods:'get',
                     params:{
                         area:this.dq,
                         type:this.lx,
                         order:this.px,
                         //数量
                         limit:this.limit,
                         //分页
                         offset: (this.page - 1) * this.limit
                     }
                 }).then(res => {
                     console.log(res);
                     this.MvList = res.data.data;

                     for (let i = 0; i < this.MvList.length ; i++) {
                         if(this.MvList[i].playCount > 100000) {
                             this.MvList[i].playCount = parseInt(this.MvList[i].playCount / 10000) + '万'
                         }
                     }
                     //接口有时没有count总条数 就判断只把有count的值赋值
                     if(res.data.count) {
                         this.total = res.data.count;
                     }
                 })
             },
            change(val){
                this.page = val;
                this.getMv()
            },
            toMv(id,songsName,name,playCount,cover) {
                this.$router.push(`/Mvdetails?id=${id}&songsName=${songsName}
                                    &name=${name}&playCount=${playCount}&cover=${cover}`)
            }
        }
    }
</script>

<style scoped>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    ul{list-style: none}
    ul li{float: left;}
    .clearfix:after{
        content: '';
        display: block;
        clear: both;
    }
    .mvs {
        width: 1100px;
        margin: 10px auto;
        font-size: 14px;
    }
    .mvs li {
        width: 109px;
        text-align: center;
        border-right: 1px solid #d1d1d1;
    }
    .mvs li span {
        display: inline-block;
        width: 75px;
        height: 28px;
        line-height: 28px;
        cursor: pointer;
    }
    .wrap {
        width: 1100px;
    }
    /*地区*/
    .region-wrap {
        margin-top: 20px;
    }
    .region-wrap > span {
        float: left;
    }

    /*去除边框*/
    .none {border : none !important;}
    /*类型*/
    .type-wrap {
        margin-top: 20px;
    }
    .type-wrap > span {
        float: left;

    }
/*    排序*/
    .order-wrap {
        margin-top: 20px;
    }
    .order-wrap > span {
        float: left;
    }

    .action {
        color: #f40;
        border-radius: 30px;
        background-color: #fae7da;
    }
/*    MV*/
    .items {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }
    .item {
        width: 250px;
        height: 186px;
        margin-top: 30px;
        cursor: pointer;
        overflow: hidden;
    }
    .item .itemIMG {
        position: relative;
    }
    .item .itemIMG img{
        width: 250px;
        height: 141px;
        border-radius: 7px;
    }
    .itemIMG .count {
        position: absolute;
        right: 7px;
        top: 0;
        height: 30px;
        color: #fff;

    }
    .count i {
        font-size:20px;
    }

    .name {font-size: 16.5px}
/*    底部分页*/
    .bt {
        width: 1100px;
        text-align: center;
        margin-top: 10px;
    }


</style>
