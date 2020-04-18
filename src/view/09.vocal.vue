<template>
    <div class="vocal">
        <div class="title">
            <div class="TitleTop">
                <img :src="songlist.coverImgUrl" width="190px" height="190px">
                <div class="dw">
                    <span class="r1">{{ songlist.name }}</span>
                    <el-button type="primary" class="mt20">播放全部</el-button>
                    <span class="bq">标签：{{ songlist.tags }}</span>
                </div>
            </div>
            <p class="name">简介：{{ songlist.description }}</p>
        </div>
        <div class="bt">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="歌曲列表" name="first">
                    <table class="el-table">
                        <thead>
                            <td width="35px"></td>
                            <td width="180px"></td>
                            <td width="200px">音乐标题</td>
                            <td width="150px">歌手</td>
                            <td width="350px">专辑</td>
                            <td>时长</td>
                        </thead>
                        <tbody>
                            <tr v-for="(items,index) in songlist.tracks">
                                <td>{{ index + 1 }}</td>
                                <td class="playtd"><img :src="items.al.picUrl" alt="" width="75px"><i class="el-icon-video-play" @click="playmc(items.id)"></i></td>
                                <td>{{ items.name }}</td>
                                <td>{{ items.ar[0].name }}</td>
                                <td>{{ items.al.name }}</td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                </el-tab-pane>
                <el-tab-pane label="评论" name="second">评论</el-tab-pane>
            </el-tabs>
        </div>

    </div>
</template>

<script>
    import axios from 'axios'
    export default {

        data() {
            return {
                activeName: 'first',
                songlist:{}
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },
            playmc(id) {
                axios({
                    url : 'https://autumnfish.cn/song/url',
                    methods : 'get',
                    params : {
                        id
                    }
                }).then(res => {
                    console.log('歌曲地址：')
                    console.log(res)
                    let url = res.data.data[0].url
                    if(url !== null) {
                        this.$parent.playUrl = res.data.data[0].url
                    }else {
                        this.$message.error("歌曲获取失败")
                    }
                })
            }
        },
        created() {
            axios({
                //歌单
                url : 'https://autumnfish.cn/playlist/detail',
                methods : 'get',
                params : {
                    id : this.$route.query.id
                }
            }).then(res => {
                console.log(res)
                this.songlist = res.data.playlist

            })
        }
    }
</script>

<style scoped>
    .vocal {
        width: 1100px;
        margin: 10px auto;
    }
    .title {
        margin-top: 20px;
    }
    TitleTop {
        position: relative;
        margin-top: 20px;
    }
    .mt20{
        margin-top: 40px;
    }
    .TitleTop span{
        display: block;
        margin-top: 25px ;
    }
    .dw {
        position: absolute;
        left: 25%;
        top: 0;
    }
    .name {
        margin-top: 10px;
    }

    .bt {
        width: 1100px;
        margin: 25px auto;
    }
    .r1 {
        font-size: 22px;
    }
    .playtd {
        position: relative;
    }
    .playtd img {
        cursor: pointer;
    }
    .playtd i {
        position: absolute;
        left: 16px;
        top: 50%;
        transform: translateY(-50%);
        font-size: 40px;
        color: #74b9ff;
        opacity: 0;
        transition: .2s;
        cursor: pointer;
    }
    .playtd i:hover{
        opacity: 1;
    }
</style>
