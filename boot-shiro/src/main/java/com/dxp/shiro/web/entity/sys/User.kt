package com.dxp.shiro.web.entity.sys

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*

/**
 * 账号
 *
 * @author carzy
 * @date 2020/8/4
 */
class User : Serializable {

    var id: Long? = null
    var username: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String? = null

    var enabled = false

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val authorities: Set<Permit> = HashSet()

    val roles: List<Role>? = null

}