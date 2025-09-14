<template>
  <div class="test-dashboard">
    <h1>测试下拉菜单</h1>
    
    <div class="test-dropdown">
      <el-dropdown @command="handleCommand" placement="bottom-end">
        <el-button type="primary">
          点击测试下拉菜单
          <el-icon class="el-icon--right"><ArrowDown /></el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="profile">
              <el-icon><User /></el-icon>
              个人资料
            </el-dropdown-item>
            <el-dropdown-item command="settings">
              <el-icon><Setting /></el-icon>
              系统设置
            </el-dropdown-item>
            <el-dropdown-item divided command="logout">
              <el-icon><SwitchButton /></el-icon>
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

    <div class="user-info-test">
      <h3>用户信息测试</h3>
      <el-dropdown @command="handleCommand" placement="bottom-end">
        <div class="user-info">
          <el-avatar :size="32">
            <el-icon><User /></el-icon>
          </el-avatar>
          <span class="username">测试用户</span>
          <el-icon class="arrow-down"><ArrowDown /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="profile">
              <el-icon><User /></el-icon>
              个人资料
            </el-dropdown-item>
            <el-dropdown-item command="settings">
              <el-icon><Setting /></el-icon>
              系统设置
            </el-dropdown-item>
            <el-dropdown-item divided command="logout">
              <el-icon><SwitchButton /></el-icon>
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

    <el-button @click="goBack">返回仪表板</el-button>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { User, ArrowDown, Setting, SwitchButton } from '@element-plus/icons-vue'

const router = useRouter()

const handleCommand = async (command: string) => {
  console.log('Test command triggered:', command)
  switch (command) {
    case 'profile':
      ElMessage.info('个人资料功能开发中...')
      break
    case 'settings':
      ElMessage.info('系统设置功能开发中...')
      break
    case 'logout':
      try {
        await ElMessageBox.confirm('确定要退出登录吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        
        ElMessage.success('退出登录成功')
        router.push('/login')
      } catch {
        console.log('用户取消退出登录')
      }
      break
  }
}

const goBack = () => {
  router.push('/dashboard')
}
</script>

<style scoped>
.test-dashboard {
  padding: 40px;
  max-width: 800px;
  margin: 0 auto;
}

.test-dropdown {
  margin: 30px 0;
}

.user-info-test {
  margin: 30px 0;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 8px 12px;
  border-radius: 6px;
  border: 1px solid #ddd;
  background: white;
  transition: background-color 0.3s;
}

.user-info:hover {
  background-color: #f5f5f5;
}

.username {
  font-size: 14px;
  font-weight: 500;
}

.arrow-down {
  font-size: 12px;
}
</style>
