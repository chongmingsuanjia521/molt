package asia.wjm.service;

import asia.wjm.DTO.AdminLoginDTO;
import asia.wjm.DTO.AdminRegisterDTO;
import asia.wjm.VO.AdminLoginVO;
import asia.wjm.entity.Admin;


public interface AuthService {
    Admin Login(AdminLoginDTO adminLoginDTO);

    void register(AdminRegisterDTO adminRegisterDTO);
}
