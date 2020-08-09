package com.dxp.security.web.controller;

import com.dxp.security.web.controller.dto.ProductUpdate;
import com.dxp.security.web.entity.bas.Product;
import com.dxp.security.web.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 商品页
 *
 * @author dxp
 * 2020/7/26 2:18 下午
 */
@RestController
@RequestMapping("/api/v1/product")
@Api(tags = "商品操作", value = "商品的增/删/改/查操作")
public class ProductController {

    @ApiOperation(value = "查询商品集合", notes = "按照条件查询全部的商品信息。需要拥有 product:read 权限")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('product:read')")
    public R<List<Product>> list() {
        List<Product> list = IntStream.of(5).mapToObj(i -> {
            Product p = new Product();
            p.setId((long) i);
            p.setName("name_" + i);
            p.setNum(100 + i);
            p.setPrice((double) (66 + i));
            return p;
        }).collect(Collectors.toList());
        return R.suc(list);
    }

    @ApiOperation(value = "新建商品信息", notes = "传入对应的名称,价格和库存数即可。需要拥有 product:create 权限或者 product 角色")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('product:create') or hasRole('product')")
    public R<Product> save(@Valid @RequestBody Product product) {
        product.setId(11L);
        return R.suc(product);
    }

    @ApiOperation(value = "修改商品信息", notes = "传入对应的名称,价格和库存数即可. 需要拥有 admin 角色，并有product:update权限")
    @ApiImplicitParam(name = "id", required = true, paramType = "path")
    @PreAuthorize("hasAnyAuthority('product:update') and hasRole('admin')")
    @PatchMapping("/{id}")
    public R<Product> update(@PathVariable Long id, @RequestBody ProductUpdate update) {
        Product product = new Product();
        product.setId(id);
        if (update.getName() == null || "".equals(update.getName())) {
            product.setName("name_" + id);
        } else {
            product.setName(update.getName());
        }
        if (update.getPrice() == null) {
            product.setPrice(66.6);
        } else {
            product.setPrice(update.getPrice());
        }
        product.setNum(555);
        return R.suc(product);
    }

    @ApiOperation(value = "删除商品信息", notes = "传入对应的商品ID。 需要拥有product:delete权限")
    @ApiImplicitParam(name = "id", required = true, paramType = "path")
    @PreAuthorize("hasAnyAuthority('product:delete')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }

}
