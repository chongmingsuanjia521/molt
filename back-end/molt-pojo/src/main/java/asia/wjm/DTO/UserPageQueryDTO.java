package asia.wjm.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPageQueryDTO {
    private Integer page;
    private Integer size;
    private String name;
    private String sex;
}
