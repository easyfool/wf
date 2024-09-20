package com.wf.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wf.mp.domain.entity.User;
import com.wf.mp.mapper.UserMapper;
import com.wf.mp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangfeng
 * @since 2024-09-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * mybatis-plus 根据任意字段saveOrUpdateBatch
     * 一般根据唯一索引
     *
     * @param list
     * @return
     */
    public boolean batchSaveOrUpdate(List<User> list) {
        return SqlHelper.saveOrUpdateBatch(getEntityClass(), getMapperClass(), super.log, list, DEFAULT_BATCH_SIZE, (sqlSession, entity) -> {//这里主要是查询唯一约束对应的记录是否存在
            LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                    .eq(User::getUsername, entity.getUsername());
//                    .eq(User::getAgentId, entity.getAgentId()).eq(Entity::getPeriod, entity.getPeriod())
//                    .eq(User::getType, entity.getType());
            Map<String, Object> map = CollectionUtils.newHashMapWithExpectedSize(1);
            map.put(Constants.WRAPPER, queryWrapper);
            return CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_LIST), map));
        }, (sqlSession, entity) -> {
            LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(User::getUsername, entity.getUsername());
//                    .eq(User::getAgentId, entity.getAgentId()).eq(User::getPeriod, entity.getPeriod())
//                    .eq(User::getType, entity.getType());
            Map<String, Object> param = CollectionUtils.newHashMapWithExpectedSize(2);
            param.put(Constants.ENTITY, entity);
            param.put(Constants.WRAPPER, lambdaUpdateWrapper);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE), param);
        });
    }


}
