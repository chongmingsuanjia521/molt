package asia.wjm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    private Long id; // 主键
    private String name; // 姓名
    private String username; // 用户名
    private String password; // 密码
    private String phone; // 手机号
    private String avatar; // 头像
    private String sex; // 性别
    private Integer status = 1; // 状态 0:禁用，1:启用
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
    private Long createUser; // 创建人
    private Long updateUser; // 修改人
    private String role; // 角色

}
