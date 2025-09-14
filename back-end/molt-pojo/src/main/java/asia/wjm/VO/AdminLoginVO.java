package asia.wjm.VO;

import asia.wjm.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginVO {

    String token;
    Admin userInfo;
    // 登录响应类型
   /* export interface LoginResponse {
        token: string
        refreshToken?: string
        expiresIn: number // token过期时间（秒）
        userInfo: {
            id: number
            name: string
            username: string
            phone?: string
            avatar?: string
            sex: string
            status: number
        }
    }*/
}
