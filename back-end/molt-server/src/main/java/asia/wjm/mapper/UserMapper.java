package asia.wjm.mapper;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> pageQuery(UserPageQueryDTO userPageQueryDTO);
}

