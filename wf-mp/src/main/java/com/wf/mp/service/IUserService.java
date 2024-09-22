package com.wf.mp.service;

import com.wf.mp.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.mp.domain.form.UserForm;
import com.wf.mp.domain.query.UserQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangfeng
 * @since 2024-09-21
 */
public interface IUserService extends IService<User> {

    void addUser(UserForm userForm);

    List<User> queryUsers(UserQuery userQuery);
}
