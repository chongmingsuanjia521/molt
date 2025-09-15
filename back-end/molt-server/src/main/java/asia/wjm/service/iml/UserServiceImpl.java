package asia.wjm.service.iml;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.entity.User;
import asia.wjm.mapper.UserMapper;
import asia.wjm.result.PageResult;
import asia.wjm.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
