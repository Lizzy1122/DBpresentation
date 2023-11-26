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
  <dic class="container">

    <div class="navigator">
      <router-link to="./HOME">首页|</router-link>
      <router-link to="./BLOG">博客|</router-link>
      <router-link to="./Crud1">Crud1</router-link>
    </div>

    <div class="content">
      <router-view></router-view>
    </div>

  </dic>
  <!--  测试一下 Element-plus-->
  <!--  <el-row class="mb-4">-->
  <!--    <el-button>Default</el-button>-->
  <!--    <el-button type="primary">Primary</el-button>-->
  <!--    <el-button type="success">Success</el-button>-->
  <!--    <el-button type="info">Info</el-button>-->
  <!--    <el-button type="warning">Warning</el-button>-->
  <!--    <el-button type="danger">Danger</el-button>-->
  <!--  </el-row>-->
  <!--测试 vue-router-->
  <!--    如果不使用router则要使用a标签跳转-->
  <!--    <a href="/HOME">首页</a>  |-->
  <!--    <a href="/BLOG">博客</a>-->

  <!--    使用router-link标签-->


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
