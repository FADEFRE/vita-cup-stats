<script setup>
import Settings from './components/Settings.vue'
</script>

<template>
  <div class="page_body">
    <div class="align">
      <Settings class="full-width" :streamSlotId="'A'" :streamSlotName="'A-Stream'"/>
      <Settings class="full-width" :streamSlotId="'B'" :streamSlotName="'B-Stream'"/>
    </div>
    <Settings class="full-width" :streamSlotId="'N'" :streamSlotName="'Nix-Stream'"/>
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

.align {
  display: flex;
  align-items: stretch;
  gap: 32px;
  align-self: stretch;
}

.full-width {
  width: 100%;
}
</style>
