<template>
    <div class="Mvdetails">
        <div class="mv">
            <video :src="mvsrc" controls autoplay></video>
        </div>
        <h2>{{ name }}</h2>
        <div class="js">
            <span class="name">{{ songsName }}</span>
            <div class="xx">
                <span class="playcount">播放:{{ playCount }}次</span>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data(){
            return {
                mvsrc : '',
                imgsrc : '',
                playCount : 0,
                songsName : '',
                name : '',
            }
        },
        created() {
            console.log(this.$route.query.id)
            let id = this.$route.query.id
            axios({
                url : 'https://autumnfish.cn/mv/url',
                methods : 'get',
                params : {
                    id
                }
            }).then(res => {
                console.log(res)
                this.mvsrc = res.data.data.url;
            })
            this.playCount = this.$route.query.playCount
            this.songsName = this.$route.query.songsName
            this.name = this.$route.query.name
            console.log(this.name)
            this.imgsrc = this.$route.query.cover
        }
    }
</script>

<style scoped>
    * {
        margin: 0;
        padding: 0;
    }
    .Mvdetails {
        width: 1000px;
        margin: 10px auto;
    }
    .mv {
        margin-top: 10px;
        width: 1000px;
        overflow: hidden;
        border-radius: 4px;
    }
    video {
        width: 1000px;
        height: 563px;
        outline: none;
        border: none;

    }
    .js {
        position: relative;
        margin-top: 10px;
        padding: 20px 0;
    }
    .name {
        position: absolute;
        font-size: 18px;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
    }
    .xx {
        position: absolute;
        left: 200px;
        top: 50%;
        transform: translateY(-50%);
        color: #95a5a6;
    }
</style>
