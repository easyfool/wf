package com.wf.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wf.mp.domain.entity.User;
import com.wf.mp.domain.form.UserForm;
import com.wf.mp.domain.query.UserQuery;
import com.wf.mp.mapper.UserMapper;
import com.wf.mp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangfeng
 * @since 2024-09-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void addUser(UserForm userForm) {
        this.save(BeanUtil.copyProperties(userForm, User.class));
    }

    @Override
    public List<User> queryUsers(UserQuery userQuery) {
        return lambdaQuery()
                .like(userQuery.getUsername() != null, User::getUsername, userQuery.getUsername())
                .eq(userQuery.getStatus() != null, User::getStatus, userQuery.getStatus())
                .lt(userQuery.getMaxBalance() != null, User::getBalance, userQuery.getMaxBalance())
                .gt(userQuery.getMinBalance() != null, User::getBalance, userQuery.getMinBalance())
                .list();

    }
}
