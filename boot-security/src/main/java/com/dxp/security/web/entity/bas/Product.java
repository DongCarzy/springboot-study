package com.dxp.security.web.entity.bas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品
 *
 * @author carzy
 * @date 2020/8/7
 */
@ApiModel(value = "Product", description = "商品")
public class Product {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "价格", required = true)
    @NotNull(message = "价格不能为空")
    private Double price;

    @ApiModelProperty(value = "库存", required = true)
    @NotNull(message = "库存不能为空")
    private Integer num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
