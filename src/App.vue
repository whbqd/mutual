<template>
  <div id="app">
    <router-view />
    <div class="record">
      <span
        ><a href="http://www.beian.miit.gov.cn/">浙ICP备20015412号-1</a></span
      >
    </div>
    <!--<register />-->
  </div>
</template>
<script>
export default {};
</script>
<style lang="scss">
  #app {
    height: 100%;
  }
.record {
  width: 400px;
  position: absolute;
  bottom: -5%;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;

  span {
    a {
      color: #000;

      &:hover {
        color: #2c3e50;
      }
    }
  }
}
</style>
