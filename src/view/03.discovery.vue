<template>
    <div class="discovery">
        <!--  轮播图  -->
        <div class="Upper">
            <el-carousel :interval="4000" type="card" height="230px" style="width: 1100px;left: 50%;transform: translateX(-50%)">
                <el-carousel-item v-for="(item,index) in CarouselList" :key="index">
                    <img :src="item.imageUrl" alt="" width="550px" height="230px">
                </el-carousel-item>
            </el-carousel>
        </div>
<!--    推荐歌单-->
        <div class="songs">
            <h3 class="title">推荐歌单</h3>
            <div class="items">
<!--                盒子-->
                <div class="item" v-for="item in songsheetList">
                    <div class="imgitem">
                        <i class="el-icon-video-play" @click="tomusic(item.id)"></i>
                        <img :src="item.picUrl" alt="">
                        <div class="desc">
                            <span>{{ item.copywriter }}</span>
                        </div>
                    </div>
                    <p class="name">{{ item.name }}</p>
                </div>
            </div>

        </div>
<!--        最新音乐-->
        <div class="news">
            <h3 class="title">最新音乐</h3>
            <div class="items">
                <div class="item" v-for="item in NewestList">
                    <img :src="item.picUrl" alt="">
                    <i @click="playMusic(item.id)" class="el-icon-video-play"></i>
                    <div class="warp">
                        <p class="bt">{{ item.name }}</p>
                        <p>{{ item.song.artists[0].name }}</p>
                    </div>
                </div>
            </div>
        </div>

<!--        推荐mv-->
        <div class="mvs">
            <h3 class="title">推荐mv</h3>
            <div class="items">
                <div class="item" v-for="item in mvList">
                    <div class="img">
                        <img :src="item.picUrl" alt="">
                        <span><i class="el-icon-headset"></i>{{ item.playCount }}</span>
                        <i @click="toMv(item.id,item.artistName,item.name,item.playCount,item.cover)" class="el-icon-video-play"></i>
                    </div>
                    <p class="bw1">{{ item.name }}</p>
                    <p class="bw2">{{ item.artistName }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                //轮播图
                CarouselList : [],
                //推荐歌单
                songsheetList : [],
                //最新音乐
                NewestList : [],
                //推荐MV
                mvList : []
            }

        },
        created() {
            //发现音乐接口 https://autumnfish.cn/banner
            axios({
                url:'https://autumnfish.cn/banner',
                methods:'get',
                params:{}
            }).then(res => {
                this.CarouselList = res.data.banners
                // console.log(this.CarouselList)
            }),
            //推荐歌单接口 https://autumnfish.cn/personalized
            axios({
                url:'https://autumnfish.cn/personalized',
                methods:'get',
                params:{
                    limit:10
                }
            }).then(res => {
                this.songsheetList = res.data.result;
            }),
        //  最新音乐  https://autumnfish.cn/personalized/newsong
            axios({
                url : 'https://autumnfish.cn/personalized/newsong',
                methods : 'get',
                params:{}
            }).then(res => {
                this.NewestList = res.data.result;
            })
        //    推荐MV接口 https://autumnfish.cn/personalized/mv
            axios({
                url : 'https://autumnfish.cn/personalized/mv',
                methods : 'get',
                params : {}
            }).then(res => {
                this.mvList = res.data.result
            })
        },
        methods: {
            //  最新音乐 点击播放
            playMusic(id) {
                //获取音频URl  参数id
                axios({
                    url: 'https://autumnfish.cn/song/url',
                    methods: 'get',
                    params: {
                        id
                    }
                }).then(res => {
                    let url = res.data.data[0].url
                    // 设置父组件的url(音频播放)地址
                    this.$parent.playUrl = url
                })
            },
            toMv(id,songsName,name,playCount,cover) {
                this.$router.push(`/Mvdetails?id=${id}&songsName=${songsName}
                                    &name=${name}&playCount=${playCount}&cover=${cover}`)
            },
            tomusic(id) {
                this.$router.push(`/vocal?id=${id}`)
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
    .Upper {
        margin-top: 10px;
        text-align: center;
        position: relative;
    }
    .Upper img {
        border-radius: 20px;
    }
    /*推荐歌单*/
    .songs {
        width: 1100px;
        margin: 0 auto;
    }
    /*标题间距*/
    .songs .title {
        margin-bottom: 20px;
    }
    .songs img{
        width: 200px;
        border-radius: 6px;
    }
    .songs .items {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .songs .item {
        width: 200px;
        overflow: hidden;
        position: relative;
        margin-bottom: 18px;
    }
    .songs i {
        position: absolute;
        font-size: 40px;
        color: #ff4400;
        right: 10px;
        bottom: 55px;
        opacity: 0;
        cursor: pointer;
        transition: .4s;

    }
    .songs .item .imgitem {

    }
    .songs .desc{
        position: absolute;
        top: -50px;
        background-color: rgba(0,0,0,0.3);
        width: 200px;
        height: 49px;
        flex-wrap: wrap;
        transition: .4s;
    }
    .songs .item:hover .desc{
        top: 0px;

    }
    .songs .item:hover i {opacity: 1}
    .songs .desc span {
        font-size: 14px;
        color: #fff;
    }
    .songs .name {
        font-size: 14px;
    }
    /*最新音乐*/
    .news {
        width: 1100px;
        height: 500px;
        margin: 10px auto;
    }
    .news .title {
        margin-bottom: 15px;
    }
    .news .item {
        width: 550px;
        height: 100px;
        display: flex;
        flex-wrap: wrap;
        float: left;
        background-color: rgba(0,0,0,0);
        position: relative;
        margin-bottom: 10px;
        transition: .4s;

    }
    .news .item:hover{
        background-color: rgba(0,0,0,0.1);

    }
    .news img {

        width: 80px;
        height: 80px;
        position: absolute;
        left: 10px;
        top: 50%;
        transform: translateY(-50%);
        cursor: pointer;

    }
    .news i {
        font-size: 33px;
        color: #ff4400;
        position: absolute;
        left: 35px;
        top: 50%;
        transform: translateY(-50%);
        cursor: pointer;
        opacity: 0;
        transition: .4s;

    }
    .news .item:hover i{
        opacity: 1;
    }
    .news .item .warp {
        height: 100px;
        position: absolute;
        left: 100px;

    }
    .news .item .warp .bt {
        font-size: 17px;
        margin: 15px 0 25px 0;
    }

    /*推荐mv*/
    .mvs {
        width: 1100px;
        height: 234px;
        margin: 20px auto  0;
    }
    .mvs .title {
        margin: 20px 0px;
    }
    .mvs img{
        width: 250px;
        height: 140px;
        border-radius: 5px;
    }
    .mvs .items {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        margin-bottom: 20px;
    }
    .mvs .item {
        width: 250px;
        height: 189px;
    }
    .mvs .img {
        position: relative;
        cursor: pointer;
    }
    .img span {
        position: absolute;
        top: 0;
        right: 0;
        color: #fff;
        font-size: 18px;
    }
    .img > i {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        font-size: 53px;
        color: #ff4400;
        opacity: 0;
        cursor: pointer;
        transition: .4s;

    }
    .img:hover i {
        opacity: 1;
    }
    .item .bw1 {font-size: 15px}
    .item .bw2 {
        font-size: 14px;
        color: #d1d1d1;
        margin-top: 5px;
        margin-bottom: 15px;
    }
</style>
