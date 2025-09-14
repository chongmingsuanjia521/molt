package asia.wjm.admin.controller;

import asia.wjm.DTO.AdminLoginDTO;
import asia.wjm.VO.AdminLoginVO;
import asia.wjm.admin.service.AuthService;
import asia.wjm.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminController")
@RequestMapping("/admin/auth")
@Slf4j
@Api()
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO){
        log.info("管理员登录：",adminLoginDTO);
        AdminLoginVO adminLoginVO = authService.Login(adminLoginDTO);
    }
}
