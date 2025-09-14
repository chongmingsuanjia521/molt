package asia.wjm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminInviteCode {

    Long id;
    String code;
    Date createTime;
    Date expireTime;
    Integer status; // 0: 未使用, 1: 已使用, 2: 已过期
    Long usedBy; // 使用者ID
}
