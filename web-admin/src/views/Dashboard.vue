<template>
  <div class="dashboard-container">
    <!-- 顶部导航栏 -->
    <div class="top-header">
      <div class="header-left">
        <div class="logo">
          <h1>Molt</h1>
          <span class="logo-subtitle">蜕</span>
        </div>
        <span class="header-title">管理后台</span>
      </div>
      <div class="header-right">
        <el-button text>
          <el-icon><Bell /></el-icon>
        </el-button>
        <el-dropdown @command="handleCommand">
          <span class="user-dropdown">
            <el-avatar :size="32" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
            <span class="username">管理员</span>
            <el-icon><ArrowDown /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="profile">个人信息</el-dropdown-item>
              <el-dropdown-item command="settings">系统设置</el-dropdown-item>
              <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>

    <div class="main-container">
      <!-- 左侧导航栏 -->
      <div class="sidebar">
        <el-menu
          default-active="dashboard"
          class="sidebar-menu"
          background-color="#001529"
          text-color="#ffffff"
          active-text-color="#1890ff"
        >
          <el-menu-item index="dashboard">
            <el-icon><Odometer /></el-icon>
            <span>仪表盘</span>
          </el-menu-item>
          <el-sub-menu index="user">
            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="user-list">用户列表</el-menu-item>
            <el-menu-item index="user-roles">角色权限</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="content">
            <template #title>
              <el-icon><Document /></el-icon>
              <span>内容管理</span>
            </template>
            <el-menu-item index="posts">动态管理</el-menu-item>
            <el-menu-item index="comments">评论管理</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="system">
            <template #title>
              <el-icon><Setting /></el-icon>
              <span>系统管理</span>
            </template>
            <el-menu-item index="config">系统配置</el-menu-item>
            <el-menu-item index="logs">操作日志</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>

      <!-- 右侧内容区域 -->
      <div class="content-area">
        <div class="content-header">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>管理后台</el-breadcrumb-item>
            <el-breadcrumb-item>仪表盘</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        
        <div class="content-body">
          <!-- 数据统计卡片 -->
          <div class="stats-grid">
            <el-card class="stats-card">
              <div class="stats-content">
                <div class="stats-icon user-icon">
                  <el-icon><User /></el-icon>
                </div>
                <div class="stats-info">
                  <h3>用户总数</h3>
                  <p class="stats-number">1,234</p>
                  <span class="stats-trend positive">+12%</span>
                </div>
              </div>
            </el-card>
            
            <el-card class="stats-card">
              <div class="stats-content">
                <div class="stats-icon post-icon">
                  <el-icon><Document /></el-icon>
                </div>
                <div class="stats-info">
                  <h3>动态总数</h3>
                  <p class="stats-number">5,678</p>
                  <span class="stats-trend positive">+8%</span>
                </div>
              </div>
            </el-card>
            
            <el-card class="stats-card">
              <div class="stats-content">
                <div class="stats-icon activity-icon">
                  <el-icon><TrendCharts /></el-icon>
                </div>
                <div class="stats-info">
                  <h3>今日活跃</h3>
                  <p class="stats-number">890</p>
                  <span class="stats-trend negative">-3%</span>
                </div>
              </div>
            </el-card>
            
            <el-card class="stats-card">
              <div class="stats-content">
                <div class="stats-icon revenue-icon">
                  <el-icon><Money /></el-icon>
                </div>
                <div class="stats-info">
                  <h3>本月收入</h3>
                  <p class="stats-number">¥12,345</p>
                  <span class="stats-trend positive">+15%</span>
                </div>
              </div>
            </el-card>
          </div>

          <!-- 图表和表格区域 -->
          <div class="charts-section">
            <div class="chart-row">
              <el-card class="chart-card" header="用户增长趋势">
                <div class="chart-placeholder">
                  <p>📈 用户增长图表区域</p>
                  <span>这里将显示用户增长趋势图</span>
                </div>
              </el-card>
              
              <el-card class="chart-card" header="活跃度统计">
                <div class="chart-placeholder">
                  <p>📊 活跃度统计图表</p>
                  <span>这里将显示用户活跃度分析</span>
                </div>
              </el-card>
            </div>
          </div>

          <!-- 最新数据表格 -->
          <div class="table-section">
            <el-card header="最新用户注册">
              <el-table :data="recentUsers" style="width: 100%">
                <el-table-column prop="username" label="用户名" width="120" />
                <el-table-column prop="name" label="姓名" width="100" />
                <el-table-column prop="phone" label="手机号" width="130" />
                <el-table-column prop="registerTime" label="注册时间" width="180" />
                <el-table-column label="状态" width="80">
                  <template #default="scope">
                    <el-tag :type="scope.row.status === 'active' ? 'success' : 'warning'">
                      {{ scope.row.status === 'active' ? '正常' : '待审核' }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button link type="primary" size="small">查看</el-button>
                    <el-button link type="primary" size="small">编辑</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { 
  Bell, 
  ArrowDown, 
  User, 
  Odometer, 
  Document, 
  Setting, 
  TrendCharts, 
  Money 
} from '@element-plus/icons-vue'

const router = useRouter()

// 模拟用户数据
const recentUsers = ref([
  {
    username: 'user001',
    name: '张三',
    phone: '138****1234',
    registerTime: '2025-09-14 10:30:00',
    status: 'active'
  },
  {
    username: 'user002',
    name: '李四',
    phone: '139****5678',
    registerTime: '2025-09-14 09:15:00',
    status: 'pending'
  },
  {
    username: 'user003',
    name: '王五',
    phone: '137****9012',
    registerTime: '2025-09-14 08:45:00',
    status: 'active'
  },
  {
    username: 'user004',
    name: '赵六',
    phone: '136****3456',
    registerTime: '2025-09-14 07:20:00',
    status: 'active'
  }
])

// 处理下拉菜单命令
const handleCommand = (command: string) => {
  switch (command) {
    case 'profile':
      ElMessage.info('个人信息功能开发中...')
      break
    case 'settings':
      ElMessage.info('系统设置功能开发中...')
      break
    case 'logout':
      handleLogout()
      break
  }
}

// 退出登录
const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  ElMessage.success('退出登录成功')
  router.push('/login')
}
</script>

<style scoped>
.dashboard-container {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  background: #f0f2f5;
}

/* 顶部导航栏 */
.top-header {
  height: 64px;
  background: #fff;
  border-bottom: 1px solid #e8e8e8;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  z-index: 100;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.logo {
  display: flex;
  align-items: center;
  gap: 4px;
}

.logo h1 {
  font-size: 24px;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0;
}

.logo-subtitle {
  font-size: 12px;
  color: #999;
}

.header-title {
  font-size: 16px;
  color: #333;
  font-weight: 500;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-dropdown {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.user-dropdown:hover {
  background-color: #f5f5f5;
}

.username {
  font-size: 14px;
  color: #333;
}

/* 主要容器 */
.main-container {
  flex: 1;
  display: flex;
  overflow: hidden;
}

/* 左侧导航栏 */
.sidebar {
  width: 240px;
  background: #001529;
  overflow-y: auto;
}

.sidebar-menu {
  border: none;
  height: 100%;
}

.sidebar-menu .el-menu-item,
.sidebar-menu .el-sub-menu__title {
  height: 50px;
  line-height: 50px;
}

/* 内容区域 */
.content-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.content-header {
  height: 48px;
  background: #fff;
  border-bottom: 1px solid #e8e8e8;
  display: flex;
  align-items: center;
  padding: 0 24px;
}

.content-body {
  flex: 1;
  padding: 24px;
  overflow-y: auto;
}

/* 统计卡片 */
.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
  margin-bottom: 24px;
}

.stats-card {
  border-radius: 8px;
}

.stats-content {
  display: flex;
  align-items: center;
  gap: 16px;
}

.stats-icon {
  width: 60px;
  height: 60px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  color: white;
}

.user-icon {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.post-icon {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
}

.activity-icon {
  background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
}

.revenue-icon {
  background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
}

.stats-info h3 {
  margin: 0;
  font-size: 14px;
  color: #666;
  font-weight: 500;
}

.stats-number {
  margin: 4px 0;
  font-size: 24px;
  font-weight: 600;
  color: #333;
}

.stats-trend {
  font-size: 12px;
  font-weight: 500;
}

.stats-trend.positive {
  color: #52c41a;
}

.stats-trend.negative {
  color: #ff4d4f;
}

/* 图表区域 */
.charts-section {
  margin-bottom: 24px;
}

.chart-row {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 24px;
}

.chart-card {
  border-radius: 8px;
}

.chart-placeholder {
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: #fafafa;
  border-radius: 4px;
  color: #999;
}

.chart-placeholder p {
  font-size: 24px;
  margin: 0 0 8px 0;
}

.chart-placeholder span {
  font-size: 14px;
}

/* 表格区域 */
.table-section {
  margin-bottom: 24px;
}

.table-section .el-card {
  border-radius: 8px;
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
  
  .chart-row {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .sidebar {
    width: 200px;
  }
  
  .stats-grid {
    grid-template-columns: 1fr;
  }
  
  .content-body {
    padding: 16px;
  }
}
</style>
