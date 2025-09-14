package asia.wjm.DTO;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRegisterDTO {
    String username;
    String name;
    String phone;
    String sex;
    String password;
    String inviteCode;
}
