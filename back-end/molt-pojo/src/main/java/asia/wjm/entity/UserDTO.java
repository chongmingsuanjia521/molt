package asia.wjm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id; // 主键
    private String username; // 用户名
    private String password; // 密码
    private String email; // 邮箱
    private String name; // 姓名
    private String sex; // 性别
    private Integer age; // 年龄
    private String phone; // 手机号
    private String avatar; // 头像
}
