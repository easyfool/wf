package com.wf.redis.service.impl;

import com.wf.redis.domain.entity.SeckillVoucher;
import com.wf.redis.mapper.SeckillVoucherMapper;
import com.wf.redis.service.ISeckillVoucherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀优惠券表，与优惠券是一对一关系 服务实现类
 * </p>
 *
 * @author wangfeng
 * @since 2024-10-07
 */
@Service
public class SeckillVoucherServiceImpl extends ServiceImpl<SeckillVoucherMapper, SeckillVoucher> implements ISeckillVoucherService {

}
