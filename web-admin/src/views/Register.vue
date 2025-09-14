<template>
  <div class="register-container">
    <div class="register-box">
      <div class="register-header">
        <div class="logo">
          <h1>Molt</h1>
          <span class="logo-subtitle">蜕</span>
        </div>
        <h2>管理员注册</h2>
        <p class="description">加入自律社交平台管理团队</p>
      </div>
      <el-form 
        ref="registerFormRef" 
        :model="registerForm" 
        :rules="registerRules" 
        class="register-form"
        @keyup.enter="handleRegister"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="registerForm.username"
            placeholder="请输入用户名"
            size="large"
            clearable
          >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input
            v-model="registerForm.name"
            placeholder="请输入姓名"
            size="large"
            clearable
          >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="registerForm.sex" size="large">
            <el-radio label="1" size="large">男</el-radio>
            <el-radio label="0" size="large">女</el-radio>
          </el-radio-group>
        </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="registerForm.phone"
          placeholder="请输入手机号"
          size="large"
            clearable
          >
            <template #prefix>
              <el-icon><Phone /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="registerForm.password"
            type="password"
            placeholder="请输入密码"
            size="large"
            clearable
            show-password
          >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input
            v-model="registerForm.confirmPassword"
            type="password"
            placeholder="请确认密码"
            size="large"
            clearable
            show-password
          >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        
        <el-form-item label="邀请码" prop="inviteCode">
          <el-input
            v-model="registerForm.inviteCode"
            placeholder="请输入邀请码"
            size="large"
            clearable
          >
            <template #prefix>
              <el-icon><Message /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        
        <el-form-item>
          <el-button 
            type="primary" 
            size="large" 
            class="register-button"
            :loading="loading"
            @click="handleRegister"
          >
            注册
          </el-button>
        </el-form-item>
        
        <el-form-item>
          <div class="register-links">
            <router-link to="/login" class="login-link">
              已有账号？立即登录
            </router-link>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { User, Lock, Message, Phone } from '@element-plus/icons-vue'
import { register } from '../api/auth'
import type { RegisterData } from '../api/auth'

const router = useRouter()
const registerFormRef = ref<FormInstance>()
const loading = ref(false)

const registerForm = reactive<RegisterData>({
  username: 'demo1',
  name:'zhangsan',
  sex: '1',  // "1"代表男，"0"代表女
  phone: '12345678901',
  password: '123456',
  confirmPassword: '123456',
  inviteCode: '521521'
})

const validateConfirmPassword = (_rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== registerForm.password) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

const registerRules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度为 3 到 20 个字符', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 30, message: '姓名长度为 2 到 30 个字符', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^[0-9]{11}$/, message: '请输入正确的手机号', trigger: 'blur' }
  ],
  sex: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度为 6 到 20 个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, validator: validateConfirmPassword, trigger: 'blur' }
  ]
}

const handleRegister = async () => {
  if (!registerFormRef.value) return
  
  const valid = await registerFormRef.value.validate().catch(() => false)
  if (!valid) return
  
  loading.value = true
  
  try {
    const response = await register(registerForm)
    
    if (response.code === 1) {
      ElMessage.success('注册成功，请登录')
      router.push('/login')
    } else {
      // 处理业务错误（code !== 1）
      ElMessage.error(response.msg || response.message || '注册失败')
    }
  } catch (error: any) {
    console.error('Register error:', error)
    
    // 处理HTTP错误或网络错误
    if (error.response?.data) {
      // 后端返回了错误信息
      const errorData = error.response.data
      if (errorData.msg) {
        ElMessage.error(errorData.msg)
      } else if (errorData.message) {
        ElMessage.error(errorData.message)
      } else if (errorData.data?.msg) {
        ElMessage.error(errorData.data.msg)
      } else {
        ElMessage.error('注册失败，请稍后重试')
      }
    } else {
      // 网络错误或其他错误
      ElMessage.error('网络连接失败，请检查网络连接')
    }
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-container {
  width: 100vw;
  height: 100vh;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  position: fixed;
  top: 0;
  left: 0;
  overflow: hidden;
}

.register-container::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: 
    radial-gradient(circle at 20% 80%, rgba(120, 119, 198, 0.3) 0%, transparent 50%),
    radial-gradient(circle at 80% 20%, rgba(255, 255, 255, 0.1) 0%, transparent 50%);
}

.register-box {
  width: 420px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border-radius: 16px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.15);
  position: relative;
  z-index: 1;
}

.register-header {
  text-align: center;
  margin-bottom: 35px;
}

.logo {
  margin-bottom: 20px;
}

.logo h1 {
  font-size: 36px;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin: 0;
  letter-spacing: 2px;
}

.logo-subtitle {
  display: block;
  font-size: 14px;
  color: #999;
  margin-top: 5px;
  letter-spacing: 1px;
}

.register-header h2 {
  color: #333;
  font-weight: 600;
  margin: 10px 0 5px 0;
  font-size: 20px;
}

.description {
  color: #666;
  font-size: 14px;
  margin: 0;
}

.register-form {
  width: 100%;
}

.register-form .el-form-item__label {
  color: #333 !important;
  font-weight: 600 !important;
  font-size: 14px !important;
  line-height: 1.5 !important;
}

.register-button {
  width: 100%;
  height: 45px;
  font-size: 16px;
  font-weight: 600;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
}

.register-button:hover {
  background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
}

.register-links {
  text-align: center;
  width: 100%;
}

.login-link {
  color: #667eea;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.3s;
}

.login-link:hover {
  color: #764ba2;
  text-decoration: underline;
}

.register-form .el-radio-group {
  width: 100%;
  display: flex;
  gap: 20px;
}

.register-form .el-radio {
  flex: 1;
  margin-right: 0;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  padding: 12px 20px;
  transition: all 0.3s;
  background-color: #fff;
}

.register-form .el-radio:hover {
  border-color: #667eea;
  background-color: #f8f9ff;
}

.register-form .el-radio.is-checked {
  border-color: #667eea;
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0%, rgba(118, 75, 162, 0.1) 100%);
}

.register-form .el-radio__label {
  font-weight: 500;
  color: #333;
}
</style>
