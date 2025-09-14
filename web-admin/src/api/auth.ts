import api from './request'

export interface LoginData {
  username: string
  password: string
}

export interface RegisterData {
  username: string
  name: string
  sex: string  // "1"=男，"0"=女 或 "M"=男，"F"=女
  phone: string
  password: string
  confirmPassword: string
  inviteCode: string
}

export interface ApiResponse<T = any> {
  code: number
  message?: string  // 兼容 message 字段
  msg?: string      // 兼容 msg 字段（后端实际使用的）
  data: T
}

export interface User {
  id: number
  username: string
  email: string
  role: string
}

export interface LoginResponse {
  token: string
  user: User
}

// 登录接口
export const login = (data: LoginData): Promise<ApiResponse<LoginResponse>> => {
  return api.post('/auth/login', data)
}

// 注册接口
export const register = (data: RegisterData): Promise<ApiResponse<{ user: User }>> => {
  return api.post('/auth/register', data)
}
