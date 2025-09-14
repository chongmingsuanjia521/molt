package asia.wjm.mapper;

import asia.wjm.entity.Admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Select("SELECT * FROM admin_user WHERE username = #{username}")
    Admin getAdminByUsername(String username);
}
