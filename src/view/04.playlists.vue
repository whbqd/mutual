<template>
    <div class="playlists">
<!--        上方div块-->
        <div class="introduce">
<!--            左边logo-->
            <div class="ico">
                <img :src="jpcs.coverImgUrl" alt="">
            </div>
<!--            右边-->
            <div class="warp">
                <div class="tag">精品歌单</div>
                <p class="title">{{ jpcs.name }}</p>
                <p class="info">{{ jpcs.description }}</p>
            </div>
        </div>
<!--        中间-->
        <div class="itemize">
            <div class="tab-bar">
                <span :class="{action:selectName=='全部'}" @click="selectName='全部'">全部</span>
                <span :class="{action:selectName=='欧美'}" @click="selectName='欧美'">欧美</span>
                <span :class="{action:selectName=='华语'}" @click="selectName='华语'">华语</span>
                <span :class="{action:selectName=='流行'}" @click="selectName='流行'">流行</span>
                <span :class="{action:selectName=='说唱'}" @click="selectName='说唱'">说唱</span>
                <span :class="{action:selectName=='摇滚'}" @click="selectName='摇滚'">摇滚</span>
                <span :class="{action:selectName=='民谣'}" @click="selectName='民谣'">民谣</span>
                <span :class="{action:selectName=='电子'}" @click="selectName='电子'">电子</span>
                <span :class="{action:selectName=='轻音乐'}" @click="selectName='轻音乐'">轻音乐</span>
                <span :class="{action:selectName=='影视原声'}" @click="selectName='影视原声'">影视原声</span>
                <span :class="{action:selectName=='ACG'}" @click="selectName='ACG'">ACG</span>
                <span :class="{action:selectName=='怀旧'}" @click="selectName='怀旧'">怀旧</span>
                <span :class="{action:selectName=='治愈'}" @click="selectName='治愈'">治愈</span>
                <span :class="{action:selectName=='旅行'}" @click="selectName='旅行'">旅行</span>
            </div>
        </div>
        <div class="items">
            <div class="item" v-for="item in songList">
                <div class="img2">
                    <img :src="item.coverImgUrl" alt="">
                    <div class="tag">播放量：{{ item.playCount }}</div>
                    <i class="el-icon-video-play" @click="toVocal(item.id)"></i>
                </div>
                <p class="name">{{ item.description }}</p>
            </div>
        </div>

<!--        底部分页-->
        <div class="paging">
            <el-pagination
                    background
                    @current-change="change"
                    layout="prev, pager, next"
                    :total="total"
                    :current-page="page"

            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
            //     精品歌单接口参数
                jpcs:[],
            //    歌单列表
                songList:[],
            //    当前选择的数据
                selectName:'全部',
                //总条数

                total:0,
                //页数
                page:1
            }
        },
        methods:{
            //    精品歌单接口 https://autumnfish.cn/top/playlist/highquality
            songInfo(){
              axios({
                  url:'https://autumnfish.cn/top/playlist/highquality',
                  methods:'get',
                  params:{
                      limit:1,
                      cat:this.selectName
                  }
              }).then(res => {
                  this.jpcs = res.data.playlists[0]
              })
            },
            songsList(){
                //    歌单列表接口 https://autumnfish.cn/top/playlist/
                axios({
                    url:'https://autumnfish.cn/top/playlist/',
                    methods: 'get',
                    params: {
                        limit:10,
                        //页码-1  *  每页个数
                        offset:(this.page - 1) * 10,
                        cat:this.selectName
                    }

                }).then(res => {
                    console.log(res)
                    this.total = res.data.total;
                    this.songList = res.data.playlists
                })
            },
            change(val) {
                console.log(`当前页数是:${val}`)
                this.page = val
                this.songsList()
            },
            toVocal(id) {
                this.$router.push("/vocal?id="+id)
            }
        },
        watch:{
            selectName(){
                this.songInfo()
                this.songsList()
                this.page=1
            }
        },

        created() {
            this.songInfo()
            this.songsList()
        },



    }
</script>

<style scoped>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    .playlists {
        width: 1100px;
        overflow: hidden;
        margin: 0 auto;
        position: relative;
    }
    .introduce {
        width: 1100px;
        height: 200px;
        position: relative;
        border-radius: 7px;
        background-color: #2980b9;
        margin-top: 10px;
    }
    .introduce .ico {
        width: 160px;
        height: 160px;
        position: absolute;
        left: 20px;
        top: 50%;
        transform: translateY(-50%);
    }
    .introduce .ico img {
        width: 160px;
        height: 160px;
    }
    .warp {
        width: 880px;
        height: 160px;
        overflow: hidden;
        position: absolute;
        top: 50%;
        left: 200px;
        transform: translateY(-50%);
    }
    .warp .tag {
        width: 100px;
        height: 30px;
        background: none;
        border:2px solid #e67e22;
        text-align: center;
        line-height: 30px;
        border-radius: 6px;
        color: #e67e22;
    }

    .warp .title {
        font-size: 18px;
        color: #fff;
        margin: 8px 0;
    }
    .warp .info {
        width: 880px;
        height: 85px;
        overflow: hidden;
        text-overflow: ellipsis;
        color: #f5f6fa;
    }

    /*中间分类区*/
    .itemize {
        width: 1100px;
        margin: 30px 0 0px 0;
    }
    .itemize .tab-bar {
        text-align: right;
        color: #7f8c8d;
    }
    .tab-bar span {
        font-size: 17px;
        margin-right: 15px;
        cursor: pointer;
    }
    /*分类选中class*/
    .action {
        color: #d35400;
    }

    /*中间主题内容*/
    .items {
        width: 1100px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        margin-top: 10px;
    }
    .item {
        width: 200px;
        height: 241px;
        overflow: hidden;
        margin-top: 25px;
    }
    .img2 {
        position: relative;
    }
    .img2 img {
        width: 200px;
        height: 200px;
        border-radius: 4px;
    }
    .img2 .tag {
        width: 200px;
        height: 30px;
        background: rgba(0,0,0,0.35);
        color: #fff;
        position: absolute;
        top: -50px;
        transition: .4s;
    }
    .item:hover .tag {top: 0;}
    .img2 i {
        position: absolute;
        right: 10px;
        bottom: 10px;
        color: #f40;
        font-size: 47px;
        cursor: pointer;
        opacity: 0;
        transition: .4s;
    }
    .item:hover i {
        opacity: 1;
    }
    .item .name {
        font-size: 14px;
        width: 200px;
        height: 37.3px;
    }

    /*分页*/
    .paging {
        width: 1100px;
        text-align: center;
        margin-top: 30px;
        margin-bottom: 20px;
    }

</style>
