### **Molt（蜕）Web 管理后台**

**项目名称**：Molt（蜕）Web Admin  
**中文名**：蜕 · 管理后台  
**主题**：自律社交平台管理系统  
**核心目标**：为 Molt 自律社交平台提供全面的管理和监控功能

---

## **项目简介**
**Molt Web Admin** 是 Molt（蜕）自律社交平台的管理后台系统。管理员可以通过此系统管理用户、监控平台活动、审核内容、分析数据等，确保平台健康有序运行。

---

## **已实现功能**

### **✅ 认证系统**
- [x] 管理员登录界面（响应式设计，品牌化 UI）
- [x] 管理员注册界面  
- [x] JWT Token 认证
- [x] 登录状态保持
- [x] 自动路由守卫

### **✅ 布局架构**
- [x] Top-Side-Content 经典后台布局
- [x] 响应式侧边栏导航
- [x] 顶部用户信息栏
- [x] 面包屑导航
- [x] Element Plus UI 组件库集成

### **✅ 数据概览（仪表板）**
- [x] 核心数据统计卡片
  - 总用户数 + 增长趋势
  - 总目标数 + 增长趋势  
  - 今日打卡数 + 增长趋势
  - 今日动态数 + 增长趋势
- [x] 图表区域（预留 ECharts 集成位置）
- [x] 最新动态列表
- [x] 实时数据展示

### **✅ 导航菜单结构**
- [x] **数据概览** - 平台整体数据监控
- [x] **用户管理**
  - 用户列表 - 查看所有注册用户
  - 用户分析 - 用户行为数据分析
- [x] **目标管理**  
  - 目标列表 - 用户创建的所有目标
  - 目标分类 - 目标类型管理
  - 成就系统 - 徽章和奖励管理
- [x] **社交管理**
  - 动态管理 - 用户发布的社区动态
  - 挑战赛 - 公开挑战活动管理
  - 排行榜 - 活跃用户排行
- [x] **内容管理**
  - 内容审核 - 社区内容合规审查
  - 举报处理 - 用户举报内容处理
- [x] **系统管理**
  - 系统配置 - 平台参数设置
  - 系统日志 - 操作和错误日志

### **✅ 网络配置**
- [x] Vite 开发服务器代理配置
- [x] 跨域问题解决方案
- [x] Axios 请求拦截器（Token 自动添加）
- [x] 响应拦截器（错误统一处理）

---

## **技术栈**

### **前端框架**
- **Vue 3** - 渐进式 JavaScript 框架
- **TypeScript** - 类型安全的 JavaScript 超集
- **Vite** - 快速的前端构建工具

### **UI 组件库**
- **Element Plus** - 基于 Vue 3 的桌面端组件库
- **@element-plus/icons-vue** - Element Plus 官方图标库

### **路由与状态**
- **Vue Router 4** - Vue.js 官方路由器
- **Pinia**（待集成）- Vue 状态管理

### **网络请求**
- **Axios** - 基于 Promise 的 HTTP 库
- **代理配置** - Vite 反向代理解决跨域

### **开发工具**
- **ESLint** - 代码质量检查
- **TypeScript** - 静态类型检查

---

## **API 接口**

### **后端接口地址**
- 登录：`POST http://localhost:8080/admin/auth/login`
- 注册：`POST http://localhost:8080/admin/auth/register`

### **前端代理地址**
- 登录：`POST /api/auth/login`  
- 注册：`POST /api/auth/register`

### **代理配置**
```typescript
// vite.config.ts
server: {
  port: 3000,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true,
      rewrite: (path) => path.replace(/^\/api/, '/admin')
    }
  }
}
```

---

## **项目结构**
```
src/
├── api/           # API 接口封装
│   ├── index.ts   # Axios 实例配置
│   └── auth.ts    # 认证相关接口
├── router/        # 路由配置
│   └── index.ts   # 路由定义和守卫
├── views/         # 页面组件
│   ├── Login.vue      # 登录页面
│   ├── Register.vue   # 注册页面
│   └── Dashboard.vue  # 管理后台主页
├── components/    # 可复用组件
├── assets/        # 静态资源
├── App.vue        # 根组件
└── main.ts        # 应用入口
```

---

## **快速开始**

### **安装依赖**
```bash
npm install
```

### **启动开发服务器**
```bash
npm run dev
```

### **构建生产版本**
```bash
npm run build
```

---

## **下一步开发计划**

### **🚀 第二阶段**
1. **用户管理模块**
   - [ ] 用户列表页面（分页、搜索、筛选）
   - [ ] 用户详情页面
   - [ ] 用户状态管理（启用/禁用）
   - [ ] 用户数据分析图表

2. **目标管理模块**
   - [ ] 目标列表页面
   - [ ] 目标分类管理
   - [ ] 成就系统配置

3. **数据可视化**
   - [ ] 集成 ECharts 图表库
   - [ ] 用户增长趋势图
   - [ ] 目标完成率分析
   - [ ] 活跃度热力图

### **🚀 第三阶段**
1. **社交内容管理**
   - [ ] 动态内容审核
   - [ ] 挑战赛活动管理
   - [ ] 举报内容处理

2. **系统优化**
   - [ ] 性能优化
   - [ ] 移动端适配
   - [ ] 暗色主题支持
### 🎯 核心功能完善计划
1. 用户管理模块 (最优先)

    当前已有登录注册，还需要：

#### 页面需求：

    - 用户列表页面（表格、搜索、分页）
    - 用户详情/编辑对话框
    - 用户状态管理
2. 自律管理核心功能

根据README描述的核心功能：

3. 社交功能管理
4. 系统管理功能

### 接口
// 用户管理API接口
- GET /api/users - 用户列表（分页、搜索、筛选）
- GET /api/users/:id - 用户详情
- PUT /api/users/:id - 编辑用户信息
- DELETE /api/users/:id - 删除用户
- PUT /api/users/:id/status - 启用/禁用用户
- GET /api/users/statistics - 用户统计数据


// 目标管理
- GET /api/goals - 目标列表
- POST /api/goals - 创建目标
- PUT /api/goals/:id - 编辑目标
- DELETE /api/goals/:id - 删除目标

// 打卡记录管理
- GET /api/check-ins - 打卡记录
- GET /api/check-ins/statistics - 打卡统计

// 成就系统管理
- GET /api/achievements - 成就列表
- POST /api/achievements - 创建成就
- PUT /api/achievements/:id - 编辑成就

// 动态管理
- GET /api/posts - 动态列表
- PUT /api/posts/:id/status - 审核动态
- DELETE /api/posts/:id - 删除动态

// 评论管理
- GET /api/comments - 评论列表
- PUT /api/comments/:id/status - 审核评论
- DELETE /api/comments/:id - 删除评论

// 社区管理
- GET /api/communities - 社区列表
- POST /api/communities - 创建社区

// 系统配置
- GET /api/system/config - 系统配置
- PUT /api/system/config - 更新配置

// 操作日志
- GET /api/system/logs - 操作日志

// 数据统计
- GET /api/statistics/dashboard - 仪表盘数据
- GET /api/statistics/users - 用户统计
- GET /api/statistics/content - 内容统计
---

## **设计理念**
- **简洁高效** - 清晰的信息架构，高效的操作流程
- **数据驱动** - 通过数据洞察平台运营状况
- **用户友好** - 直观的界面设计，良好的交互体验
- **可扩展性** - 模块化设计，便于功能扩展

---

**项目愿景**：构建一个功能完善、易用高效的管理后台，为 Molt 自律社交平台的健康发展提供坚实支撑。
