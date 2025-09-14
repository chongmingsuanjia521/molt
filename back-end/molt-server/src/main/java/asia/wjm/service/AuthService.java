package asia.wjm.admin.service;

import asia.wjm.VO.AdminLoginVO;
import asia.wjm.admin.mapper.AuthMapper;
import asia.wjm.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface AuthService {
    AdminLoginVO Login(AdminLoginVO adminLoginVO);
}
