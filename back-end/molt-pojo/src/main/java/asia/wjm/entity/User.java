package asia.wjm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id; // 主键
    private String username; // 用户名
    private String password; // 密码
    private String email; // 邮箱
    private String name; // 姓名
    private String sex; // 性别
    private Integer age; // 年龄
    private Short status; // 状态 0:禁用，1:启用
    private String phone; // 手机号
    private String avatar; // 头像
    private LocalDateTime createTime; // 创建时间
    private String school; // 学校
    private Integer level; // 用户等级
    private Integer points; // 积分
    private String bio; // 个人简介
    private LocalDateTime birthday; // 生日
    private LocalDateTime updateTime; // 更新时间
    private LocalDateTime lastLoginTime; // 最后登录时间
}
