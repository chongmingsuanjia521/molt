package asia.wjm.service.iml;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.entity.User;
import asia.wjm.entity.UserDTO;
import asia.wjm.mapper.UserMapper;
import asia.wjm.result.PageResult;
import asia.wjm.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getSize());
        List<User> page= userMapper.pageQuery(userPageQueryDTO);
        PageInfo<User> pageInfo=new PageInfo<>(page);
        PageResult pageResult=new PageResult();
        pageResult.setRecords(pageInfo.getList());
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }

    @Override
    public void add(UserDTO userDTO) {
        if(userDTO==null){
            throw new RuntimeException("用户信息不能为空");
        }
        if(userDTO.getUsername()==null){
            throw new RuntimeException("用户名不能为空");
        }
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        userMapper.insert(user);
    }
    @Override
    public void delete(List<Long> ids){
        List<User> users = userMapper.getByIds(ids);
        if(users==null){
            throw new NullPointerException("用户id不存在");
        }
        userMapper.deleteBatch(ids);
    }
    @Override
    public User searchById(Long id) {
        User user=userMapper.getById(id);
        if(user==null){
            throw new NullPointerException("用户id不存在");
        }
        return user;
    }
    @Override
    public void update(UserDTO userDTO) {
        if(userDTO==null){
            throw new RuntimeException("用户信息不能为空");
        }
        if(userDTO.getId()==null){
            throw new RuntimeException("用户id不能为空");
        }
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
}
