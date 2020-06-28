package com.dxp.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxp.mybatisplus.entity.User;
import com.dxp.mybatisplus.mapper.UserMapper;
import com.dxp.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author carzy
 * @date 2020/6/28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
