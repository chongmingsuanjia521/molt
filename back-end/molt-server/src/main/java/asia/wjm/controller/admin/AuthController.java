package asia.wjm.controller.admin;

import asia.wjm.DTO.AdminLoginDTO;
import asia.wjm.DTO.AdminRegisterDTO;
import asia.wjm.VO.AdminLoginVO;
import asia.wjm.interceptor.JwtTokenUserInterceptor;
import asia.wjm.properties.JwtProperties;
import asia.wjm.service.AuthService;
import asia.wjm.entity.Admin;
import asia.wjm.result.Result;
import asia.wjm.utils.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("AdminController")
@RequestMapping("/admin/auth")
@Slf4j
@Api()
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO){
        log.info("管理员登录：{}",adminLoginDTO);
        Admin userInfo= authService.Login(adminLoginDTO);
        //返回登录信息
        AdminLoginVO adminLoginVO=new AdminLoginVO();
        adminLoginVO.setUserInfo(userInfo);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", userInfo.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        adminLoginVO.setToken(token);
        return Result.success(adminLoginVO);
    }
    @PostMapping("/register")
    public Result<String> register(@RequestBody AdminRegisterDTO adminRegisterDTO){
        log.info("管理员注册：{}",adminRegisterDTO);
        authService.register(adminRegisterDTO);
        return Result.success("注册成功");
    }
}
