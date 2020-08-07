package com.dxp.security.web.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品可修改字段
 *
 * @author carzy
 * @date 2020/8/7
 */
@ApiModel(value = "商品可被修改的字段")
public class ProductUpdate {

    @ApiModelProperty(name = "名称", required = true)
    private String name;

    @ApiModelProperty(name = "价格", required = true)
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
