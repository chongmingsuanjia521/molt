package asia.wjm.service;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.result.PageResult;

public interface UserService {

    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);
}
