package com.dxp.shiro.web.controller.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 商品可修改字段
 *
 * @author carzy
 * @date 2020/8/7
 */
@ApiModel(value = "ProductUpdate", description = "商品可被修改的字段")
class ProductUpdate {

    @ApiModelProperty(name = "名称", required = true)
    var name: String? = null

    @ApiModelProperty(name = "价格", required = true)
    var price: Double? = null
}