package asia.wjm.mapper;

import asia.wjm.entity.AdminInviteCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InviteCodeMapper {
    @Select("SELECT * FROM admin_invite_code")
    List<AdminInviteCode> getAll();
}
