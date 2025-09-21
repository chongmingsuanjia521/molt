package asia.wjm.service;

import asia.wjm.DTO.AdminLoginDTO;
import asia.wjm.DTO.AdminRegisterDTO;
import asia.wjm.entity.Admin;


public interface AuthService {
    Admin login(AdminLoginDTO adminLoginDTO);

    void register(AdminRegisterDTO adminRegisterDTO);
}
