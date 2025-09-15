# API 接口文档

## 认证接口

### 1. 用户登录

**接口地址：** `POST /admin/auth/login`

**前端代理地址：** `POST /api/auth/login`

**请求参数：**
```json
{
  "username": "string",    // 用户名，必填
  "password": "string"     // 密码，必填
}
```

**响应示例：**

成功响应：
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": 1,
      "username": "admin",
      "email": "admin@example.com",
      "role": "admin"
    }
  }
}
```

失败响应：
```json
{
  "code": 400,
  "message": "用户名或密码错误",
  "data": null
}
```

---

### 2. 用户注册

**接口地址：** `POST /admin/auth/register`

**前端代理地址：** `POST /api/auth/register`

**请求参数：**
```json
{
  "username": "string",      // 用户名，必填，长度3-20字符
  "password": "string",      // 密码，必填，长度6-20字符
  "email": "string",         // 邮箱，必填，格式正确的邮箱地址
  "confirmPassword": "string" // 确认密码，必填，需与密码一致
}
```

**响应示例：**

成功响应：
```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "user": {
      "id": 2,
      "username": "newuser",
      "email": "newuser@example.com",
      "role": "user"
    }
  }
}
```

失败响应：
```json
{
  "code": 400,
  "message": "用户名已存在",
  "data": null
}
```


## 前端代理配置

在 `vite.config.ts` 中已配置代理：

```typescript
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

前端调用时使用 `/api/auth/login` 和 `/api/auth/register`，会自动代理到后端的 `/admin/auth/login` 和 `/admin/auth/register`。

---

## 目标管理接口

### 3. 获取目标列表

**接口地址：** `GET /admin/goals`

**前端代理地址：** `GET /api/goals`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "keyword": "string", // 搜索关键字，可选
  "status": "string",  // 目标状态：active/completed/paused，可选
  "userId": "number"   // 用户ID，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 50,
    "list": [
      {
        "id": 1,
        "title": "每天运动1小时",
        "description": "坚持每天运动1小时，保持身体健康",
        "userId": 123,
        "username": "张三",
        "status": "active",
        "targetDays": 30,
        "completedDays": 15,
        "createTime": "2025-09-01 10:00:00",
        "updateTime": "2025-09-14 08:30:00"
      }
    ]
  }
}
```

---

### 4. 创建目标

**接口地址：** `POST /admin/goals`

**前端代理地址：** `POST /api/goals`

**请求参数：**
```json
{
  "title": "string",       // 目标标题，必填
  "description": "string", // 目标描述，可选
  "userId": "number",      // 用户ID，必填
  "targetDays": "number",  // 目标天数，必填
  "category": "string"     // 目标分类，可选
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "创建成功",
  "data": {
    "id": 2,
    "title": "每天运动1小时",
    "status": "active",
    "createTime": "2025-09-14 10:30:00"
  }
}
```

---

### 5. 编辑目标

**接口地址：** `PUT /admin/goals/{id}`

**前端代理地址：** `PUT /api/goals/{id}`

**请求参数：**
```json
{
  "title": "string",       // 目标标题，可选
  "description": "string", // 目标描述，可选
  "status": "string",      // 目标状态：active/completed/paused，可选
  "targetDays": "number"   // 目标天数，可选
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "更新成功",
  "data": null
}
```

---

### 6. 删除目标

**接口地址：** `DELETE /admin/goals/{id}`

**前端代理地址：** `DELETE /api/goals/{id}`

**响应示例：**
```json
{
  "code": 1,
  "msg": "删除成功",
  "data": null
}
```

---

## 打卡记录管理接口

### 7. 获取打卡记录

**接口地址：** `GET /admin/check-ins`

**前端代理地址：** `GET /api/check-ins`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "goalId": "number",  // 目标ID，可选
  "userId": "number",  // 用户ID，可选
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 100,
    "list": [
      {
        "id": 1,
        "goalId": 1,
        "goalTitle": "每天运动1小时",
        "userId": 123,
        "username": "张三",
        "checkInDate": "2025-09-14",
        "content": "今天跑步30分钟，力量训练30分钟",
        "images": ["image1.jpg", "image2.jpg"],
        "status": "completed",
        "createTime": "2025-09-14 20:30:00"
      }
    ]
  }
}
```

---

### 8. 获取打卡统计

**接口地址：** `GET /admin/check-ins/statistics`

**前端代理地址：** `GET /api/check-ins/statistics`

**请求参数：**
```json
{
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string",   // 结束日期，格式：YYYY-MM-DD，可选
  "userId": "number"     // 用户ID，可选
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "totalCheckIns": 450,
    "todayCheckIns": 25,
    "weeklyCheckIns": 180,
    "monthlyCheckIns": 650,
    "dailyStats": [
      {
        "date": "2025-09-14",
        "count": 25
      }
    ],
    "topGoals": [
      {
        "goalId": 1,
        "goalTitle": "每天运动1小时",
        "checkInCount": 120
      }
    ]
  }
}
```

---

## 成就系统管理接口

### 9. 获取成就列表

**接口地址：** `GET /admin/achievements`

**前端代理地址：** `GET /api/achievements`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "keyword": "string", // 搜索关键字，可选
  "status": "string",  // 成就状态：active/inactive，可选
  "category": "string" // 成就分类，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 30,
    "list": [
      {
        "id": 1,
        "title": "运动达人",
        "description": "连续打卡运动30天",
        "icon": "achievement_sport.png",
        "category": "运动",
        "condition": "consecutive_checkin_30",
        "status": "active",
        "achievedCount": 15,
        "createTime": "2025-09-01 10:00:00",
        "updateTime": "2025-09-14 08:30:00"
      }
    ]
  }
}
```

---

### 10. 创建成就

**接口地址：** `POST /admin/achievements`

**前端代理地址：** `POST /api/achievements`

**请求参数：**
```json
{
  "title": "string",       // 成就标题，必填
  "description": "string", // 成就描述，必填
  "icon": "string",        // 成就图标，可选
  "category": "string",    // 成就分类，必填
  "condition": "string",   // 获得条件，必填
  "points": "number",      // 奖励积分，可选
  "status": "string"       // 成就状态：active/inactive，可选，默认active
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "创建成功",
  "data": {
    "id": 2,
    "title": "运动达人",
    "status": "active",
    "createTime": "2025-09-14 10:30:00"
  }
}
```

---

### 11. 编辑成就

**接口地址：** `PUT /admin/achievements/{id}`

**前端代理地址：** `PUT /api/achievements/{id}`

**请求参数：**
```json
{
  "title": "string",       // 成就标题，可选
  "description": "string", // 成就描述，可选
  "icon": "string",        // 成就图标，可选
  "category": "string",    // 成就分类，可选
  "condition": "string",   // 获得条件，可选
  "points": "number",      // 奖励积分，可选
  "status": "string"       // 成就状态：active/inactive，可选
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "更新成功",
  "data": null
}
```

---

### 12. 删除成就

**接口地址：** `DELETE /admin/achievements/{id}`

**前端代理地址：** `DELETE /api/achievements/{id}`

**响应示例：**
```json
{
  "code": 1,
  "msg": "删除成功",
  "data": null
}
```

---

## 用户管理接口

### 13. 获取用户列表

**接口地址：** `GET /admin/users`

**前端代理地址：** `GET /api/users`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "keyword": "string", // 搜索关键字（用户名/手机号），可选
  "status": "number",  // 用户状态：1-启用，0-禁用，可选
  "sex": "string",     // 性别：M-男，F-女，可选
  "startDate": "string", // 注册开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 注册结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 1234,
    "list": [
      {
        "id": 1,
        "username": "user001",
        "name": "张三",
        "phone": "138****1234",
        "sex": "M",
        "status": 1,
        "createTime": "2025-09-01 10:00:00",
        "updateTime": "2025-09-14 08:30:00",
        "lastLoginTime": "2025-09-14 09:15:00"
      }
    ]
  }
}
```

---

### 14. 获取用户详情

**接口地址：** `GET /admin/users/{id}`

**前端代理地址：** `GET /api/users/{id}`

**响应示例：**
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "id": 1,
    "username": "user001",
    "name": "张三",
    "phone": "13800138000",
    "sex": "M",
    "status": 1,
    "createTime": "2025-09-01 10:00:00",
    "updateTime": "2025-09-14 08:30:00",
    "lastLoginTime": "2025-09-14 09:15:00",
    "totalGoals": 5,
    "activeGoals": 3,
    "totalCheckIns": 45,
    "thisMonthCheckIns": 15
  }
}
```

---

### 15. 更新用户状态

**接口地址：** `PUT /admin/users/{id}/status`

**前端代理地址：** `PUT /api/users/{id}/status`

**请求参数：**
```json
{
  "status": 1  // 1-启用，0-禁用
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "状态更新成功",
  "data": null
}
```

---

### 16. 删除用户

**接口地址：** `DELETE /admin/users/{id}`

**前端代理地址：** `DELETE /api/users/{id}`

**响应示例：**
```json
{
  "code": 1,
  "msg": "删除成功",
  "data": null
}
```

---

## 内容管理接口

### 17. 获取动态列表

**接口地址：** `GET /admin/posts`

**前端代理地址：** `GET /api/posts`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "keyword": "string", // 搜索关键字，可选
  "status": "string",  // 动态状态：published/pending/rejected，可选
  "userId": "number",  // 用户ID，可选
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 500,
    "list": [
      {
        "id": 1,
        "content": "今天完成了30分钟跑步，感觉很棒！",
        "images": ["image1.jpg", "image2.jpg"],
        "userId": 123,
        "username": "张三",
        "status": "published",
        "likeCount": 25,
        "commentCount": 8,
        "createTime": "2025-09-14 08:30:00",
        "updateTime": "2025-09-14 09:00:00"
      }
    ]
  }
}
```

---

### 18. 审核动态

**接口地址：** `PUT /admin/posts/{id}/review`

**前端代理地址：** `PUT /api/posts/{id}/review`

**请求参数：**
```json
{
  "status": "string",  // published-通过，rejected-拒绝
  "reason": "string"   // 拒绝原因，当status为rejected时必填
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "审核成功",
  "data": null
}
```

---

### 19. 删除动态

**接口地址：** `DELETE /admin/posts/{id}`

**前端代理地址：** `DELETE /api/posts/{id}`

**响应示例：**
```json
{
  "code": 1,
  "msg": "删除成功",
  "data": null
}
```

---

### 20. 获取评论列表

**接口地址：** `GET /admin/comments`

**前端代理地址：** `GET /api/comments`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "keyword": "string", // 搜索关键字，可选
  "status": "string",  // 评论状态：published/pending/rejected，可选
  "postId": "number",  // 动态ID，可选
  "userId": "number",  // 用户ID，可选
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 200,
    "list": [
      {
        "id": 1,
        "content": "坚持就是胜利！",
        "postId": 1,
        "postContent": "今天完成了30分钟跑步...",
        "userId": 456,
        "username": "李四",
        "status": "published",
        "likeCount": 5,
        "createTime": "2025-09-14 09:30:00",
        "updateTime": "2025-09-14 09:30:00"
      }
    ]
  }
}
```

---

### 21. 审核评论

**接口地址：** `PUT /admin/comments/{id}/review`

**前端代理地址：** `PUT /api/comments/{id}/review`

**请求参数：**
```json
{
  "status": "string",  // published-通过，rejected-拒绝
  "reason": "string"   // 拒绝原因，当status为rejected时必填
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "审核成功",
  "data": null
}
```

---

### 22. 删除评论

**接口地址：** `DELETE /admin/comments/{id}`

**前端代理地址：** `DELETE /api/comments/{id}`

**响应示例：**
```json
{
  "code": 1,
  "msg": "删除成功",
  "data": null
}
```

---

## 统计数据接口

### 23. 获取仪表盘统计数据

**接口地址：** `GET /admin/statistics/dashboard`

**前端代理地址：** `GET /api/statistics/dashboard`

**响应示例：**
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "userStats": {
      "total": 1234,
      "todayNew": 15,
      "weeklyGrowth": 8.5,
      "monthlyActive": 890
    },
    "contentStats": {
      "totalPosts": 5678,
      "todayPosts": 25,
      "weeklyGrowth": 12.3,
      "pendingReview": 8
    },
    "goalStats": {
      "totalGoals": 3456,
      "activeGoals": 2890,
      "completedToday": 156,
      "averageCompletion": 78.5
    },
    "checkInStats": {
      "totalCheckIns": 12345,
      "todayCheckIns": 234,
      "weeklyGrowth": 15.2,
      "topCategory": "运动健身"
    }
  }
}
```

---

## 系统管理接口

### 24. 获取系统配置

**接口地址：** `GET /admin/system/config`

**前端代理地址：** `GET /api/system/config`

**响应示例：**
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "app": {
      "name": "蜕 - 自律社交平台",
      "version": "1.0.0",
      "description": "专注自律习惯养成的社交平台"
    },
    "upload": {
      "maxSize": "5MB",
      "allowedTypes": ["jpg", "jpeg", "png", "gif"],
      "uploadPath": "/uploads/"
    },
    "content": {
      "autoReview": false,
      "sensitiveWords": ["违禁词1", "违禁词2"],
      "maxPostLength": 500,
      "maxCommentLength": 200
    },
    "user": {
      "allowRegister": true,
      "requireInviteCode": true,
      "defaultStatus": 1
    }
  }
}
```

---

### 25. 更新系统配置

**接口地址：** `PUT /admin/system/config`

**前端代理地址：** `PUT /api/system/config`

**请求参数：**
```json
{
  "app": {
    "name": "string",
    "description": "string"
  },
  "upload": {
    "maxSize": "string",
    "allowedTypes": ["string"]
  },
  "content": {
    "autoReview": "boolean",
    "sensitiveWords": ["string"],
    "maxPostLength": "number",
    "maxCommentLength": "number"
  },
  "user": {
    "allowRegister": "boolean",
    "requireInviteCode": "boolean",
    "defaultStatus": "number"
  }
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "配置更新成功",
  "data": null
}
```

---

### 26. 获取操作日志

**接口地址：** `GET /admin/system/logs`

**前端代理地址：** `GET /api/system/logs`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 20,          // 每页数量，可选，默认20
  "level": "string",   // 日志级别：info/warn/error，可选
  "module": "string",  // 模块：user/content/system，可选
  "adminId": "number", // 管理员ID，可选
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 1000,
    "list": [
      {
        "id": 1,
        "level": "info",
        "module": "user",
        "action": "用户状态更新",
        "description": "将用户ID:123的状态修改为禁用",
        "adminId": 1,
        "adminName": "管理员1",
        "ip": "192.168.1.100",
        "userAgent": "Mozilla/5.0...",
        "createTime": "2025-09-14 10:30:00"
      }
    ]
  }
}
```

---

### 27. 清理系统日志

**接口地址：** `DELETE /admin/system/logs`

**前端代理地址：** `DELETE /api/system/logs`

**请求参数：**
```json
{
  "beforeDate": "string"  // 清理此日期之前的日志，格式：YYYY-MM-DD
}
```

**响应示例：**
```json
{
  "code": 1,
  "msg": "日志清理成功",
  "data": {
    "deletedCount": 500
  }
}
```

---

## 目标管理接口

### 28. 获取目标列表

**接口地址：** `GET /admin/goals`

**前端代理地址：** `GET /api/goals`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "keyword": "string", // 搜索关键字，可选
  "category": "string", // 目标分类：运动健身/学习成长/生活习惯/其他，可选
  "status": "string",  // 目标状态：active/completed/paused，可选
  "userId": "number",  // 用户ID，可选
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 800,
    "list": [
      {
        "id": 1,
        "title": "每日跑步30分钟",
        "description": "坚持每天跑步30分钟，提高身体素质",
        "category": "运动健身",
        "targetDays": 30,
        "currentDays": 15,
        "status": "active",
        "userId": 123,
        "username": "张三",
        "createTime": "2025-09-01 10:00:00",
        "updateTime": "2025-09-14 08:30:00",
        "completionRate": 50.0
      }
    ]
  }
}
```

---

### 29. 获取打卡记录

**接口地址：** `GET /admin/checkins`

**前端代理地址：** `GET /api/checkins`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 20,          // 每页数量，可选，默认20
  "goalId": "number",  // 目标ID，可选
  "userId": "number",  // 用户ID，可选
  "startDate": "string", // 开始日期，格式：YYYY-MM-DD，可选
  "endDate": "string"    // 结束日期，格式：YYYY-MM-DD，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 2000,
    "list": [
      {
        "id": 1,
        "goalId": 1,
        "goalTitle": "每日跑步30分钟",
        "userId": 123,
        "username": "张三",
        "content": "今天跑了5公里，用时35分钟",
        "images": ["checkin1.jpg"],
        "checkInTime": "2025-09-14 07:30:00",
        "createTime": "2025-09-14 08:00:00"
      }
    ]
  }
}
```

---

### 30. 获取成就统计

**接口地址：** `GET /admin/achievements`

**前端代理地址：** `GET /api/achievements`

**请求参数：**
```json
{
  "page": 1,           // 页码，可选，默认1
  "size": 10,          // 每页数量，可选，默认10
  "type": "string",    // 成就类型：consistency/milestone/streak，可选
  "userId": "number"   // 用户ID，可选
}
```

**响应示例：**

成功响应：
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 300,
    "list": [
      {
        "id": 1,
        "title": "坚持达人",
        "description": "连续打卡7天",
        "type": "consistency",
        "icon": "achievement_consistency.png",
        "userId": 123,
        "username": "张三",
        "goalId": 1,
        "goalTitle": "每日跑步30分钟",
        "achievedTime": "2025-09-14 08:00:00"
      }
    ]
  }
}
```

---

## 错误码说明

### 通用错误码

| 错误码 | 说明 | 备注 |
|--------|------|------|
| 0 | 请求失败 | 通用失败状态 |
| 1 | 请求成功 | 通用成功状态 |
| 400 | 参数错误 | 请求参数格式或内容错误 |
| 401 | 未授权 | 未登录或token失效 |
| 403 | 禁止访问 | 权限不足 |
| 404 | 资源不存在 | 请求的资源不存在 |
| 409 | 资源冲突 | 如用户名已存在 |
| 422 | 数据验证失败 | 数据格式正确但验证不通过 |
| 500 | 服务器错误 | 内部服务器错误 |

### 业务错误码

| 错误码 | 说明 | 适用接口 |
|--------|------|----------|
| 1001 | 用户名或密码错误 | 登录接口 |
| 1002 | 邀请码无效 | 注册接口 |
| 1003 | 用户已存在 | 注册接口 |
| 1004 | 用户不存在 | 用户相关接口 |
| 1005 | 用户已被禁用 | 用户相关接口 |
| 2001 | 动态不存在 | 动态相关接口 |
| 2002 | 动态已被删除 | 动态相关接口 |
| 2003 | 内容包含敏感词 | 内容发布接口 |
| 3001 | 目标不存在 | 目标相关接口 |
| 3002 | 目标已完成 | 目标操作接口 |
| 4001 | 文件格式不支持 | 文件上传接口 |
| 4002 | 文件大小超限 | 文件上传接口 |

### 错误响应格式

所有错误响应都遵循统一格式：

```json
{
  "code": 0,
  "msg": "错误描述信息",
  "message": "错误描述信息",  // 兼容字段
  "data": null
}
```

---

## 接口规范说明

### 请求规范

1. **Content-Type**: 
   - POST/PUT 请求：`application/json`
   - 文件上传：`multipart/form-data`

2. **认证方式**: 
   - 请求头携带：`Authorization: Bearer {token}`

3. **时间格式**:
   - 日期时间：`YYYY-MM-DD HH:mm:ss`
   - 日期：`YYYY-MM-DD`

4. **分页参数**:
   - `page`: 页码，从1开始
   - `size`: 每页数量，默认10，最大100

### 响应规范

1. **成功响应**: `code` 为 1，包含 `msg` 和 `data`
2. **失败响应**: `code` 为 0 或具体错误码，包含 `msg`/`message` 说明
3. **列表数据**: 包含 `total` 总数和 `list` 数组
4. **空数据**: `data` 为 `null` 或空对象 `{}`

### 开发环境配置

**前端代理配置** (vite.config.ts):
```typescript
export default defineConfig({
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080/admin',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
```

**后端接口前缀**: `/admin`

**前端访问地址**: `http://localhost:3000/api`

---

## 接口测试建议

### 1. 认证流程测试
```bash
# 1. 管理员登录
POST /api/login
{
  "username": "admin",
  "password": "123456"
}

# 2. 获取用户列表（需要token）
GET /api/users
Authorization: Bearer {token}
```

### 2. 用户管理测试
```bash
# 获取用户列表
GET /api/users?page=1&size=10&keyword=张三

# 更新用户状态
PUT /api/users/1/status
{
  "status": 0
}
```

### 3. 内容管理测试
```bash
# 获取动态列表
GET /api/posts?status=pending

# 审核动态
PUT /api/posts/1/review
{
  "status": "published"
}
```

---

## 版本历史

### v1.0.0 (当前版本)
- 基础认证功能
- 用户管理模块
- 内容管理模块
- 统计数据接口
- 系统管理功能
- 目标管理模块

---

*本文档由蜕(Molt)自律社交平台管理后台团队维护*  
*最后更新时间：2025-09-14*