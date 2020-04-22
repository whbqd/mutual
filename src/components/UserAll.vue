<template>
  <div class="UserAll"></div>
</template>

<script>
    import axios from "axios";

    export default {
  name: "UserAll",
  created() {
    axios({
      url: "http://localhost:8080/Backend/UserAll",
      methods: "get"
    }).then(res => {
      console.log('#',res);

    });
  }
};
</script>

<style scoped></style>
