<script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
</script>

<template>
<!--  <header>-->
<!--    <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" />-->

<!--    <div class="wrapper">-->
<!--      <HelloWorld msg="You did it!" />-->
<!--    </div>-->
<!--  </header>-->
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
  <!--  如果不使用router则要使用a标签跳转-->
  <!--  <a href="/HOME">首页</a>  |-->
  <!--  <a href="/BLOG">博客</a>-->
  
  <!--  使用router-link标签-->
  <router-link to="./HOME">首页|</router-link>
  <router-link to="./BLOG">博客|</router-link>
  <router-link to="./Crud1">Crud1</router-link>
  <router-view></router-view>
<!--  <main>-->
<!--    <TheWelcome />-->
<!--  </main>-->
</template>

<!--<style scoped>-->
<!--header {-->
<!--  line-height: 1.5;-->
<!--}-->

<!--.logo {-->
<!--  display: block;-->
<!--  margin: 0 auto 2rem;-->
<!--}-->

<!--@media (min-width: 1024px) {-->
<!--  header {-->
<!--    display: flex;-->
<!--    place-items: center;-->
<!--    padding-right: calc(var(&#45;&#45;section-gap) / 2);-->
<!--  }-->

<!--  .logo {-->
<!--    margin: 0 2rem 0 0;-->
<!--  }-->

<!--  header .wrapper {-->
<!--    display: flex;-->
<!--    place-items: flex-start;-->
<!--    flex-wrap: wrap;-->
<!--  }-->
<!--}-->
<!--</style>-->
