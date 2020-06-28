package com.dxp.mybatis.controller;

import com.dxp.mybatis.entity.User;
import com.dxp.mybatis.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户操作接口层操作类.
 * <p>
 * 为了测试方便,没有按照标准的 restful 风格写测试的API接口.
 *
 * @author carzy
 * @date 2020/6/28
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/save")
    public User save(User user) {
        this.userMapper.save(user);
        return user;
    }

    @GetMapping("/update/username/{id}")
    public User update(@PathVariable Long id, @RequestParam String username) {
        User user = this.userMapper.getById(id);
        if (user == null) {
            throw new RuntimeException("404 not found");
        }
        user.setUsername(username);
        this.userMapper.updateById(user);
        return user;
    }

    @GetMapping("/all")
    public List<User> all() {
        return this.userMapper.all();
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.userMapper.deleteById(id);
    }

}
