package com.dxp.shiro.web.entity.bas

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 商品
 *
 * @author carzy
 * @date 2020/8/7
 */
@ApiModel(value = "Product", description = "商品")
class Product {
    @ApiModelProperty(value = "主键")
    var id: Long? = null

    @ApiModelProperty(value = "名称", required = true)
    var name: @NotBlank(message = "名称不能为空") String? = null

    @ApiModelProperty(value = "价格", required = true)
    var price: @NotNull(message = "价格不能为空") Double? = null

    @ApiModelProperty(value = "库存", required = true)
    var num: @NotNull(message = "库存不能为空") Int? = null
}