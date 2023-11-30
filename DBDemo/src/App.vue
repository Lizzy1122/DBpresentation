<script setup lang="ts">
import { onBeforeMount } from "vue";

onBeforeMount(() => {
  const body = document.querySelector("body");
  if (body) {
    body.setAttribute("style", "margin:0;");
  }
});
</script>

<template>
  <dic class="container">
    <div class="content">
      <router-view></router-view>
    </div>
  </dic>
</template>

<style scoped></style>
