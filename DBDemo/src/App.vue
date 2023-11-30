<script setup lang="ts">
import { onBeforeMount } from 'vue';

onBeforeMount(() => {
  const body = document.querySelector('body');
  if (body) {
    body.setAttribute('style', 'margin:0;');
  }
});
</script>

<template>
  <div class="container">

    <div class="navigator">
      <router-link to="./AdminOrder">修改订单|</router-link>
      <router-link to="./AdminUser">修改用户|</router-link>
      <router-link to="./AdminProduct">修改商品</router-link>
    </div>

    <div class="content">
      <router-view></router-view>
    </div>

  </div>


</template>

<style scoped>
.container{
  align-items: center;
  justify-content: center;
}
.navigator {
  background-color: #f1f1f1;
  border: 1px solid #ccc;
  padding: 10px;
  float: left;
  clear: both;
}

.content {
  background-color: #f1f1f1;
  border: 1px solid #ccc;
  padding: 10px;
  float: left;
  clear: both;
}

</style>
