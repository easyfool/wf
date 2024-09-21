package com.wf.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wf.domain.query.PageQuery;

import java.util.List;
import java.util.Map;

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


    /**
     * mybatis-plus 根据任意字段saveOrUpdateBatch
     * 一般根据唯一索引
     *
     * 放在这个地方，后面项目可以使用
     *
     * @param list
     * @return
     */
//    public boolean batchSaveOrUpdate(List<User> list) {
//        return SqlHelper.saveOrUpdateBatch(getEntityClass(), getMapperClass(), super.log, list, DEFAULT_BATCH_SIZE, (sqlSession, entity) -> {//这里主要是查询唯一约束对应的记录是否存在
//            LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
//                    .eq(User::getUsername, entity.getUsername());
////                    .eq(User::getAgentId, entity.getAgentId()).eq(Entity::getPeriod, entity.getPeriod())
////                    .eq(User::getType, entity.getType());
//            Map<String, Object> map = CollectionUtils.newHashMapWithExpectedSize(1);
//            map.put(Constants.WRAPPER, queryWrapper);
//            return CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_LIST), map));
//        }, (sqlSession, entity) -> {
//            LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
//            lambdaUpdateWrapper.eq(User::getUsername, entity.getUsername());
////                    .eq(User::getAgentId, entity.getAgentId()).eq(User::getPeriod, entity.getPeriod())
////                    .eq(User::getType, entity.getType());
//            Map<String, Object> param = CollectionUtils.newHashMapWithExpectedSize(2);
//            param.put(Constants.ENTITY, entity);
//            param.put(Constants.WRAPPER, lambdaUpdateWrapper);
//            sqlSession.update(getSqlStatement(SqlMethod.UPDATE), param);
//        });
//    }


}
