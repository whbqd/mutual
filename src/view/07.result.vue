<template>
    <div class="result">
        <div class="title">
            <span class="singer">{{ this.$route.query.key }}</span>
            <span class="number">找到{{ count }}个结果</span>
        </div>
<!--        中间-->
        <div class="type">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="歌曲" name="first">
                   <table class="el-table">
                       <thead>
                        <td></td>
                        <td>音乐标题</td>
                        <td>歌手</td>
                        <td>专辑</td>
                        <td>时长</td>
                       </thead>
                       <tbody>
                        <tr v-for="(items,index) in MusicList">
                            <td>{{ index + 1 }}</td>
                            <td>
                                <span @click="play(items.id)">{{ items.name }} </span>
                                <i @click="toMv(items.mvid)" class="el-icon-video-camera" v-show="items.mvid!==0"></i>
                                <h5 class="bz" v-if="items.alias.length !== 0">{{ items.alias[0] }}</h5>
                            </td>
                            <td width="200px">{{ items.artists[0].name }}</td>
                            <td>{{ items.album.name }}</td>
                            <td>{{ items.duration }}</td>
                        </tr>
                       </tbody>
                   </table>
                </el-tab-pane>
                <el-tab-pane label="歌单" name="second">
                    <div class="items">
                        <div class="item" v-for="items in PlayList">
                            <div class="itemImg">
                                <img :src="items.coverImgUrl" alt="">
                                <i @click="toSongs(items.id)" class="el-icon-video-play"></i>
                                <span>播放量:{{ items.playCount }}</span>
                            </div>
                            <p class="name">{{ items.name }}</p>
                        </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="MV" name="third">
                    <div class="mvitems">
                        <div v-for="items in MvList" class="mvitem">
                            <div class="mvitemImg">
                                <img :src="items.cover" alt="">
                                <div class="ico">
                                    <i class="el-icon-caret-right"></i>
                                    <span>{{ items.playCount }}</span>
                                </div>
                                <span>{{ items.duration }}</span>
                                <i @click="toMv(items.id,items.artistName,items.name,items.playCount,items.cover)" class="el-icon-video-play"></i>
                            </div>
                            <p>{{ items.name }}</p>
                            <p class="Mvname">{{ items.artistName }}</p>
                        </div>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>

<!--        分页-->
        <div class="paging">
            <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="total"
                    :current-page="page"
                    @current-change="change"
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
                activeName: 'first',
                //歌曲数组
                MusicList:[],
                //歌单数组
                PlayList:[],
                //Mv 数组
                MvList:[],
                limit:20,
                count:0,
                total:0,
                page:1,
                type:1,
            };
        },
        watch : {
            activeName() {
                if(this.activeName == 'first') {
                    this.type = 1
                } else if (this.activeName == 'second') {
                    this.type = 1000
                } else  {
                    this.type = 1004
                }
                this.getmusic(this.type)
                this.page = 1
            }
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },
            play(id) {
                axios({
                    url : 'https://autumnfish.cn/song/url',
                    methods : 'get',
                    params : {
                        id
                    }
                }).then(res => {
                    if(res.data.data[0].url == null) {
                        this.$message.error("歌曲获取失败")
                    } else {
                        this.$parent.playUrl = res.data.data[0].url
                    }
                })
            },
            getmusic(type){
                axios({
                    // url : 'https://autumnfish.cn/song/url'
                    url : 'https://autumnfish.cn/search',
                    methods : 'get',
                    params : {
                        keywords : this.$route.query.key,
                        type,   //1歌曲   1000歌单  1004Mv
                        limit : this.limit,
                        offset: (this.page - 1) * this.limit
                    }
                }).then(res => {
                    console.log(res)
                    if(this.type == 1) {
                        this.count = res.data.result.songCount
                        this.MusicList = res.data.result.songs
                        for (let i = 0; i < this.MusicList.length; i++) {
                            let min = parseInt(this.MusicList[i].duration / 1000 / 60)
                            let sec = parseInt(this.MusicList[i].duration / 1000 % 60)
                            if (min < 10) {
                                min = '0' + min
                            }
                            if (sec < 10) {
                                sec = '0' + sec
                            }
                            this.MusicList[i].duration = `${min}:${sec}`
                        }
                    } else if (this.type == 1000) {
                        console.log("歌单接口")
                        console.log(res)
                        this.count = res.data.result.playlistCount
                        this.PlayList = res.data.result.playlists
                        for (let i = 0; i < this.PlayList.length; i++) {
                            if(this.PlayList[i].playCount > 10000) {
                                this.PlayList[i].playCount = parseInt(this.PlayList[i].playCount / 10000) + '万'
                            }
                        }
                    } else {
                        this.count = res.data.result.mvCount
                        this.MvList = res.data.result.mvs
                        for (let i = 0; i < this.MvList.length; i++) {
                            let min = parseInt(this.MvList[i].duration / 1000 / 60)
                            let sec = parseInt(this.MvList[i].duration / 1000 % 60)
                            if (min < 10) {
                                min = '0' + min
                            }
                            if (sec < 10) {
                                sec = '0' + sec
                            }
                            this.MvList[i].duration = `${min}:${sec}`
                        }
                        for (let i = 0; i < this.MvList.length; i++) {
                            if(this.MvList[i].playCount > 10000) {
                                this.MvList[i].playCount = parseInt(this.MvList[i].playCount / 10000) + '万'
                            }
                        }
                    }
                    this.total = (this.count / 20) * 10
                })
            },
            change(val) {
                this.page = val
                this.getmusic(this.type)
            },
            toMv(id,songsName,name,playCount,cover) {
                this.$router.push(`/Mvdetails?id=${id}&songsName=${songsName}
                                    &name=${name}&playCount=${playCount}&cover=${cover}`)
            },
            toSongs(id) {
                this.$router.push(`/vocal?id=${id}`)
            }
        },

        created() {
            this.getmusic()
        }


        //生命周期钩子 created 回调函数 添加后自动执行 内部可以通过this访问到vue实例
        // created(){
        //     console.log(this.$route.query.key)
        // }
    }
</script>

<style scoped>
    .bz {
        font-size: 14px;
        color: #b2bec3;
        margin-top: 10px;
        font-weight: normal;
    }
    tr {
       cursor: pointer;
    }
    .result {
        width: 1100px;
        margin: 10px auto;
    }
    .title {

    }
    .singer {
        font-size: 25px;
        font-weight: bold;
        margin-right: 15px;
    }
    .number {
        font-size: 15px;
        color: #b2bec3;
    }

    .type {
        margin-top: 35px;
    }
    /*歌单*/
    .items {
        width: 1100px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .item {
        width: 200px;
        height: 244px;
        overflow: hidden;
        cursor: pointer;
        margin-bottom: 10px;
    }
    .itemImg {
        position: relative;
        width: 200px;
        height: 200px;


    }
    .itemImg i {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        font-size: 50px;
        color: #54a0ff;
        opacity: 0;
        transition: .3s;
    }
    .itemImg:hover i {
        opacity: 1;
    }
    .itemImg img {
        width: 200px;
        height: 200px;
        border-radius: 7px;
    }
    .itemImg span {
        position: absolute;
        right: 0;
        top: 0px;
        color: #fff;
        font-size: 15px;
    }
    .item .name {
        font-size: 16px;
    }
    td i:hover{
        color: #f40;
    }
    /*MV*/
    .mvitems {
        width: 1100px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .mvitem {
        width: 250px;
        height: 203.69px;
        overflow: hidden;
        cursor: pointer;
    }
    .mvitemImg {
        width: 250px;
        height: 141px;
        position: relative;
    }
    .mvitemImg img {
        width: 250px;
        height: 141px;
        border-radius: 7px;
    }
    .mvitemImg .ico {
        position: absolute;
        top: 0;
        right: 0;
        z-index: 6;
        color: #fff;
    }
    .ico i {
        font-size: 20px;

    }
    .ico span {
        font-size: 17px;
    }
    .mvitemImg > span {
        position: absolute;
        bottom: 0;
        right: 5px;
        font-size: 15px;
        color: #fff;
    }
    .mvitemImg > i {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        font-size: 55px;
        color: #54a0ff;
        opacity: 0;
        transition: .2s;
    }
    .mvitemImg:hover i {
        opacity: 1;
    }
    .Mvname {
        font-size: 14px;
        color: #b2bec3;
    }
    /*分页*/
    .paging {
        text-align: center;
        margin-top: 40px;
    }


</style>
