package com.wf.redis.service.impl;

import com.wf.redis.domain.entity.Blog;
import com.wf.redis.mapper.BlogMapper;
import com.wf.redis.service.IBlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
