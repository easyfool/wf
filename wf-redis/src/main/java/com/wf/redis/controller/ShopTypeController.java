package com.wf.redis.controller;


import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.wf.redis.domain.dto.CommonResponse;
import com.wf.redis.domain.entity.ShopType;
import com.wf.redis.service.IShopTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangfeng
 * @since 2024-10-07
 */
@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {
    @Resource
    private IShopTypeService shopTypeService;

    @GetMapping("list")
    public CommonResponse<List<ShopType>> queryShopTypeList() {
        List<ShopType> shopList = shopTypeService.query().orderByAsc("sort").list();
        return CommonResponse.success(shopList);
    }

}
