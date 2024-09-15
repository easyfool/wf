package com.wf.mp.service.impl;

import com.wf.mp.domain.entity.User;
import com.wf.mp.mapper.UserMapper;
import com.wf.mp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangfeng
 * @since 2024-09-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
