<template>
  <div class="simple-test">
    <h2>简单下拉菜单测试</h2>
    
    <!-- 最简单的下拉菜单 -->
    <el-dropdown @command="handleCommand">
      <el-button type="primary">
        下拉菜单测试
        <el-icon class="el-icon--right"><arrow-down /></el-icon>
      </el-button>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="a">Action 1</el-dropdown-item>
          <el-dropdown-item command="b">Action 2</el-dropdown-item>
          <el-dropdown-item command="c">Action 3</el-dropdown-item>
          <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>

    <br><br>

    <!-- 用户头像样式的下拉菜单 -->
    <el-dropdown @command="handleCommand">
      <div class="user-area">
        <el-avatar>U</el-avatar>
        <span>用户名</span>
        <el-icon><arrow-down /></el-icon>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="profile">个人资料</el-dropdown-item>
          <el-dropdown-item command="settings">设置</el-dropdown-item>
          <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>

    <p>点击上面的下拉菜单进行测试</p>
  </div>
</template>

<script setup lang="ts">
import { ArrowDown } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const handleCommand = (command: string) => {
  console.log('Command:', command)
  ElMessage.success(`点击了: ${command}`)
}
</script>

<style scoped>
.simple-test {
  padding: 50px;
  text-align: center;
}

.user-area {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 8px;
}
</style>
