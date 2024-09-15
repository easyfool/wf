package com.wf.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wf.domain.query.PageQuery;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 11:00
 */
public class MybatisUtils {

    public static <T> Page<T> toPage(PageQuery pageQuery, OrderItem... orderItems) {
        Page<T> page = Page.of(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (StrUtil.isNotBlank(pageQuery.getOrderBy()) && pageQuery.getAsc() != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(pageQuery.getOrderBy());
            orderItem.setAsc(pageQuery.getAsc());
            page.addOrder(orderItem);
        }
        if (ArrayUtil.isNotEmpty(orderItems)) {
            page.addOrder(orderItems);
        }
        return page;
    }

    public static <T> Page<T> toPage(PageQuery pageQuery, String defaultColumnName, boolean isAsc) {
        Page<T> page = Page.of(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (StrUtil.isNotBlank(pageQuery.getOrderBy()) && pageQuery.getAsc() != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(pageQuery.getOrderBy());
            orderItem.setAsc(pageQuery.getAsc());
            page.addOrder(orderItem);
        }

        if (StrUtil.isNotBlank(defaultColumnName)) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(defaultColumnName);
            orderItem.setAsc(isAsc);
            page.addOrder(orderItem);
        }
        return page;
    }


}
