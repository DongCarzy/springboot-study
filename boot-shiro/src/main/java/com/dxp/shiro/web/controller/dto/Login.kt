package com.dxp.shiro.web.controller.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author carzy
 * @date 2020/8/6
 */
@ApiModel(value = "Login", description = "Login")
class Login {

    @ApiModelProperty(value = "账号")
    var username: String? = null

    @ApiModelProperty(value = "密码")
    var password: String? = null
}