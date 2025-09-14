package asia.wjm.mapper;

import asia.wjm.entity.Admin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM admin_user WHERE username = #{username}")
    Admin getAdminByUsername(String username);

    @Insert("INSERT INTO admin_user(username, password, sex, name, phone, status, create_time, update_time, create_user, update_user) " +
            "VALUES (#{username}, #{password}, #{sex}, #{name}, #{phone}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Admin admin);
}
