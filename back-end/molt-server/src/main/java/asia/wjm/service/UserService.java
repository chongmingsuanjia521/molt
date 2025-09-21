package asia.wjm.service;

import asia.wjm.DTO.UserPageQueryDTO;
import asia.wjm.entity.User;
import asia.wjm.entity.UserDTO;
import asia.wjm.result.PageResult;

import java.util.List;

public interface UserService {

    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    void add(UserDTO userDTO);

    void delete(List<Long> id);

    User searchById(Long id);

    void update(UserDTO userDTO);
}
