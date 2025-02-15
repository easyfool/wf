package com.wf.redis.service.impl;

import com.wf.redis.domain.entity.BlogComments;
import com.wf.redis.mapper.BlogCommentsMapper;
import com.wf.redis.service.IBlogCommentsService;
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
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements IBlogCommentsService {

}
