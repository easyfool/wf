package com.wf.redis.service.impl;

import com.wf.redis.domain.entity.UserInfo;
import com.wf.redis.mapper.UserInfoMapper;
import com.wf.redis.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
