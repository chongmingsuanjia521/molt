package asia.wjm.service.iml;

import asia.wjm.DTO.AdminLoginDTO;
import asia.wjm.DTO.AdminRegisterDTO;
import asia.wjm.entity.Admin;
import asia.wjm.entity.AdminInviteCode;
import asia.wjm.mapper.AdminMapper;
import asia.wjm.mapper.InviteCodeMapper;
import asia.wjm.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    @Override
    public Admin login(AdminLoginDTO adminLoginDTO) {
        String username = adminLoginDTO.getUsername();

        asia.wjm.entity.Admin admin = adminMapper.getAdminByUsername(username);
        if (admin != null) {
            String encodedPassword= DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());
            if (!admin.getPassword().equals(encodedPassword)) {
                // 登录成功
                throw new RuntimeException("密码错误");
            }
            if(admin.getStatus()==0){
                throw new RuntimeException("用户被禁用");
            }
        }else{
            throw new RuntimeException("用户不存在");
        }
        return admin;
    }

    @Override
    public void register(AdminRegisterDTO adminRegisterDTO) {

        String username = adminRegisterDTO.getUsername();
        Admin existingAdmin = adminMapper.getAdminByUsername(username);
        if (existingAdmin != null) {
            throw new RuntimeException("用户名已存在");
        }
        String inviteCode = adminRegisterDTO.getInviteCode();
        List<AdminInviteCode> codes = inviteCodeMapper.getAll();
        if(codes.stream().noneMatch(code -> code.getCode().equals(inviteCode))){
            throw new RuntimeException("邀请码无效");
        }
        //用md5加密
        String encodedPassword= DigestUtils.md5DigestAsHex(adminRegisterDTO.getPassword().getBytes());

        Admin admin=new Admin();

        BeanUtils.copyProperties(adminRegisterDTO,admin);
        admin.setPassword(encodedPassword);
        adminMapper.insert(admin);

    }
}
