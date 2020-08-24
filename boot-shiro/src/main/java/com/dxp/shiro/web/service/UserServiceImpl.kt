package com.dxp.shiro.web.service

import com.dxp.shiro.common.Constants
import com.dxp.shiro.web.entity.sys.User
import org.apache.shiro.crypto.hash.Md5Hash
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    override fun findByUsername(name: String?): User? {
        val md5 = Md5Hash("12345678", null, Constants.MD5_HASH_ITERATIONS)
        //todo.. 查数据库
        val user = User();
        user.id = 1L
        user.username = "test"
        user.password = md5.toString()
        return user
    }
}