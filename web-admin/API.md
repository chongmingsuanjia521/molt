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

---

## 错误码说明

| 错误码 | 说明 |
|--------|------|
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

---

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
