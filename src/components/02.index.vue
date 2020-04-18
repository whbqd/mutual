<template>
    <div id="index">
        <div class="nav">
            <ul>
                <li :class="{bg:name=='发现音乐'}">
                    <router-link to="/discovery">
                        <i class="el-icon-service"></i>
                        <span @click="name='发现音乐'">发现音乐</span>
                    </router-link>
                </li>
                <li :class="{bg:name=='推荐歌单'}" >
                    <router-link to="/playlists">
                        <i class="el-icon-notebook-2"></i>
                        <span @click="name='推荐歌单'">推荐歌单</span>
                    </router-link>
                </li>
                <li :class="{bg:name=='最新音乐'}" >
                    <router-link to="/songs">
                        <i class="el-icon-lollipop"></i>
                        <span @click="name='最新音乐'">最新音乐</span>
                    </router-link>
                </li>
                <li :class="{bg:name=='最新MV'}" >
                    <router-link to="/mvs">
                        <i class="el-icon-potato-strips"></i>
                        <span @click="name='最新MV'">最新MV</span>
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="main">
            <router-view></router-view>
        </div>
        <div class="player">
            <audio :src="playUrl" controls autoplay></audio>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                playUrl:'',
                name:'发现音乐',
            }
        },
    }
</script>

<style>
    .bg{background-color: #fff;transition: .1s}
    ul{list-style: none}
    #index {
        margin-top: 0;
        display: flex;
        height: 610px;
        width: 100%;
        box-sizing: border-box;
    }
    #index .nav {
        width: 200px;
        background-color: #ededed;
        box-sizing: border-box;
    }
    #index .nav li {
        text-align: center;
        line-height: 60px;
        width: 100%;
    }
    #index .nav li a {
        color: #000;
        font-size: 18px;
        text-decoration: none;
        position: relative;
    }
    #index .nav li a i{
        font-size: 20px;
        position: absolute;
        left: -35px;
        top: 50%;
        transform: translateY(-50%);
    }
    #index .main {
        background-color: #fff;
    /*    使用剩余尺寸*/
        flex: 1;
    }

    a.router-link-active {
        color: #f40 !important;
        transition: .1s;
    }
    .main {
        position: relative;
        overflow-y: auto;
    }
    .player {
        width: 100%;
        height: 54px;
        position: fixed;
        bottom: 0;
        background-color: #F1F3F4;
    }
    .player audio {
        width: 100%;
        height: 54px;
        outline: none;
    }
</style>
