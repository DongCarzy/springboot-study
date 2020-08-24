package com.dxp.shiro.web.controller

import com.dxp.shiro.web.controller.dto.ProductUpdate
import com.dxp.shiro.web.entity.bas.Product
import com.dxp.shiro.web.vo.R
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.apache.shiro.authz.annotation.RequiresPermissions
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.web.bind.annotation.*
import java.util.function.IntFunction
import java.util.stream.Collectors
import java.util.stream.IntStream
import javax.validation.Valid

/**
 * 商品页
 *
 * @author dxp
 * 2020/7/26 2:18 下午
 */
@RestController
@RequestMapping("/api/v1/product")
@Api(tags = ["商品操作"], value = "商品的增/删/改/查操作")
class ProductController {
    @ApiOperation(value = "查询商品集合", notes = "按照条件查询全部的商品信息。需要拥有 product:read 权限")
    @GetMapping
    @RequiresPermissions("product:read")
    fun list(): R<List<Product>> {

        val data: List<Product> = IntStream.of(5).mapToObj { i ->
            val p = Product();
            p.id = i.toLong()
            p.name = "name_$i"
            p.num = 100 + i
            p.price = (66 + i).toDouble()
            p
        }.collect(Collectors.toList())

        return R.suc(data)
    }

    @ApiOperation(value = "新建商品信息", notes = "传入对应的名称,价格和库存数即可。需要拥有 product:create 权限或者 product 角色")
    @PostMapping
    @RequiresPermissions("product:create")
    fun save(@RequestBody product: @Valid Product): R<Product> {
        product.id = 11L
        return R.suc(product)
    }

    @ApiOperation(value = "修改商品信息", notes = "传入对应的名称,价格和库存数即可. 需要拥有 admin 角色，并有product:update权限")
    @ApiImplicitParam(name = "id", required = true, paramType = "path", dataTypeClass = Long::class)
    @RequiresPermissions("product:update")
    @RequiresRoles("admin")
    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody update: ProductUpdate): R<Product> {
        val product = Product()
        product.id = id
        if (update.name == null || "" == update.name) {
            product.name = "name_$id"
        } else {
            product.name = update.name
        }
        if (update.price == null) {
            product.price = 66.6
        } else {
            product.price = update.price
        }
        product.num = 555
        return R.suc(product)
    }

    @ApiOperation(value = "删除商品信息", notes = "传入对应的商品ID。 需要拥有product:delete权限")
    @ApiImplicitParam(name = "id", required = true, paramType = "path", dataTypeClass = Long::class)
    @RequiresPermissions("product:delete")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long?) {
    }
}