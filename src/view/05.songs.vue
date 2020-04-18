<template>
    <div class="songs">
<!--        上方分类-->
        <div class="bar">
            <span :class="{action:name==0}" @click="name=0">全部</span>
            <span :class="{action:name==7}" @click="name=7">华语</span>
            <span :class="{action:name==96}" @click="name=96">欧美</span>
            <span :class="{action:name==8}" @click="name=8">日本</span>
            <span :class="{action:name==16}" @click="name=16">韩国</span>
        </div>
        <div class="by">
<!--            内容列介绍-->
            <div class="title">
                <span class="ml1">音乐标题</span>
                <span class="ml2">歌手</span>
                <span class="ml3">专辑</span>
                <span class="ml4">时长</span>
            </div>
<!--            内容-->
            <div class="hz" v-for="(item,index) in NewList" :key="index">
                <span class="num">{{ index + 1 }}</span>
                <div class="pp">
                    <img :src="item.album.picUrl" alt="">
                    <i @click="playMusic(item.id)" class="el-icon-video-play"></i>
                </div>
                <span class="wztitle">{{ item.name }}<i class="el-icon-video-play"></i></span>
                <span class="au">{{ item.album.artists[0].name }}</span>
                <span class="zj">{{ item.album.name }}</span>
                <span class="times">{{ item.duration }}</span>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                name : 0,
                NewList : [],
            }
        },
        watch:{
          name(){
              this.getNewMusic()
          }
        },
        created() {
            this.getNewMusic()
        },
        methods: {
            getNewMusic(){
                //最新音乐接口
                axios({
                    url : 'https://autumnfish.cn/top/song',
                    methods : 'get',
                    params : {
                        type: this.name
                    }
                }).then(res => {
                    this.NewList = res.data.data
                    // 处理时长  毫秒 转 分秒
                    for (let i = 0; i < this.NewList.length; i++) {
                        let duration = this.NewList[i].duration
                        let min = parseInt(duration / 1000 / 60) //分
                        let sec = parseInt(duration / 1000 % 60) //秒
                        if(min < 10) { min = '0' + min }
                        if(sec < 10) { sec = '0' + sec }
                        this.NewList[i].duration = `${min}:${sec}`
                    }
                })
            },
            //点击后搜索歌曲播放地址 并设置给父组件audio的playUrl
            playMusic(id) {
                //获取音频URl  参数id
                axios({
                    url: 'https://autumnfish.cn/song/url',
                    methods: 'get',
                    params: {
                        id
                    }
                }).then(res => {
                    // 设置父组件的url(音频播放)地址
                    this.$parent.playUrl = res.data.data[0].url
                })
            }
        }

    }
</script>

<style scoped>
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;

    }
    .songs {
        padding: 10px 10px;
    }
    .bar {
        width: 1100px;
        margin: 0px auto;
        text-align: right;
        color: #808080;
    }
    .bar span {
        margin-right: 22px;
        font-size: 15px;
        cursor: pointer;
    }
    .action{
        color: #f40;
    }

    .by {
        width: 1100px;
        margin: 0 auto;
        overflow: hidden;
    }

    .title {
        width: 1100px;
        margin: 0px auto;
        margin-top: 15px;
        position: relative;
    }
    .title span {
        font-size: 15px;
        color: #808080;
    }
/*    标题间距*/
    .ml1 {
        position: absolute;
        left: 190px;
    }
    .ml2 {
        position: absolute;
        left: 475px;
    }
    .ml3 {
        position: absolute;
        right: 378px;
    }
    .ml4 {
        position: absolute;
        right: 0;
    }
    .wztitle i {
        font-size: 20px;
        color: #f40;
        cursor: pointer;
    }
/*    内容*/
    .hz {
        width: 1100px;
        margin: 55px auto 0 auto;
        height: 94px;
        position: relative;
        font-size: 15px;
    }

    .hz .num {
        float: left;
        line-height: 94px;
        margin: 0 72px 0 10px;
    }
    .hz .pp {
        float: left;
        position: relative;
        width: 70px;
        height: 94px;
        left: -40px;
    }
    .hz:hover {
        background-color: #dfe4ea;
        transition: .5s;
    }
    .pp img {
        width: 70px;
        height: 70px;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        border-radius: 5px;
    }
    .pp i {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        color: #ff4400;
        font-size: 32px;
        cursor: pointer;
    }
    .wztitle {
        line-height: 94px;
        position: absolute;
        left: 195px;
        font-size: 13px;

    }
    .wztitle i {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
    }
    .au {
         line-height: 94px;
        position: absolute;
        left: 477px;
        font-size: 13px;
    }

    .zj  {
        line-height: 94px;
        position: absolute;
        left: 693px;
        font-size: 13px;
    }
    .times {
        line-height: 94px;
        position: absolute;
        right: 0;
        font-size: 13px;
    }
</style>
