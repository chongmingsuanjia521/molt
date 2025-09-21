package asia.wjm.mapper;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> pageQuery(UserPageQueryDTO userPageQueryDTO);

    void insert(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(Long id);

    List<User> getByIds(List<Long> ids);

    void deleteBatch(List<Long> ids);

    void update(User user);
}

