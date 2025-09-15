package asia.wjm.controller.admin;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.entity.User;
import asia.wjm.entity.UserDTO;
import asia.wjm.mapper.UserMapper;
import asia.wjm.result.PageResult;
import asia.wjm.result.Result;
import asia.wjm.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("AdminUserController")
@Api(tags = "用户管理模块")
@RequestMapping("/admin/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/page")
    public Result<PageResult> pageQuery(UserPageQueryDTO pageQueryDTO){
        log.info("用户分页查询:{}",pageQueryDTO);
        return Result.success(userService.pageQuery(pageQueryDTO));
    }
    @PostMapping("/add")
    public Result add(@RequestBody UserDTO userDTO){
        log.info("新增用户:{}",userDTO);
        userService.add(userDTO);

        return Result.success("添加成功！");
    }
    @DeleteMapping("/delete")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids){
        log.info("删除用户: {}", ids);
        userService.delete(ids);
        return Result.success("删除成功！");
    }
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id){
        log.info("根据id获取用户: {}", id);
        User user= userService.searchById(id);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody UserDTO userDTO){
        log.info("修改用户: {}", userDTO);
        userService.update(userDTO);
        return Result.success("修改成功！");
    }
}
