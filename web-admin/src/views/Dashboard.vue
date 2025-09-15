<template>
  <div class="dashboard">
    <h1>仪表板</h1>
    <el-button @click="goToTestDropdown">测试下拉菜单</el-button>
    <el-button @click="goToSimpleTest">简单下拉菜单测试</el-button>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter()

const goToTestDropdown = () => {
  router.push('/test-dropdown')
}

const goToSimpleTest = () => {
  router.push('/simple-test')
}
</script>

<style scoped>
.dashboard {
  width: 100vw;
  height: 100vh;
  margin: 0;
  padding: 40px;
  text-align: center;
  background: #f5f5f5;
  overflow: auto;
  box-sizing: border-box;
}

.dashboard h1 {
  margin-bottom: 20px;
}

.dashboard button {
  margin: 0 10px;
}
</style>
