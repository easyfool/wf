package com.wf.redis.service.impl;

import com.wf.redis.domain.entity.User;
import com.wf.redis.mapper.UserMapper;
import com.wf.redis.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangfeng
 * @since 2024-10-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
