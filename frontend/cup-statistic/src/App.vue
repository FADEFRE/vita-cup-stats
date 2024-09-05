<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Settings from './components/Settings.vue'
</script>

<template>
  <div class="page_body">
    <Settings />
  </div>
</template>

<style scoped>
.page_body {
  background-color: #0D0D0D;
  display: flex;
  padding: 32px;
  flex-direction: column;
  align-items: flex-start;
  gap: 128px;
  width: 100dvw;
}
</style>
