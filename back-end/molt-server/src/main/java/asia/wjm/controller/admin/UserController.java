package asia.wjm.controller.admin;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.result.PageResult;
import asia.wjm.result.Result;
import asia.wjm.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminUserController")
@Api(tags = "用户管理模块")
@RequestMapping("/admin/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/page")
    public Result<PageResult> pageQuery(UserPageQueryDTO pageQueryDTO){
        log.info("用户分页查询:{}",pageQueryDTO);
        return Result.success(userService.pageQuery(pageQueryDTO));
    }
}
