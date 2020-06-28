package com.dxp.mybatisplus.controller;

import com.dxp.mybatisplus.entity.User;
import com.dxp.mybatisplus.service.UserService;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/save")
    public User save(User user) {
        this.userService.save(user);
        return user;
    }

    @GetMapping("/update/username/{id}")
    public User update(@PathVariable Long id, @RequestParam String username) {
        User user = this.userService.getById(id);
        if (user == null) {
            throw new RuntimeException("404 not found");
        }
        user.setUsername(username);
        this.userService.updateById(user);
        return user;
    }

    @GetMapping("/all")
    public List<User> all() {
        return this.userService.list();
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.userService.removeById(id);
    }

}
