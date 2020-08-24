package com.dxp.shiro.web.service

import com.dxp.shiro.web.entity.sys.Permit
import org.springframework.stereotype.Service
import java.util.*

@Service
class PermitServiceImpl : PermitService {

    override fun findAll(): List<Permit> {
        //todo.. 通过角色查询所有权限.  模拟有商品的查询，修改和保存权限，无删除权限
        val authorizations: MutableList<Permit> = ArrayList<Permit>()
        var authorization = Permit()
        authorization.id = 1L
        authorization.name = "product:read"
        authorizations.add(authorization)
        authorization = Permit()
        authorization.id = 2L
        authorization.name = "product:update"
        authorizations.add(authorization)

        return authorizations
    }

    override fun findAllByUserId(id: Long?): List<Permit> {
        //todo.. 通过角色查询所有权限.  模拟有商品的查询，修改和保存权限，无删除权限
        val authorizations: MutableList<Permit> = ArrayList<Permit>()
        var authorization = Permit()
        authorization.id = 1L
        authorization.name = "product:read"
        authorizations.add(authorization)
        authorization = Permit()
        authorization.id = 2L
        authorization.name = "product:update"
        authorizations.add(authorization)

        return authorizations
    }
}