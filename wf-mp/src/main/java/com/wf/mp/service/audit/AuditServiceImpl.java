//package com.wf.mp.service.audit;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.collection.CollectionUtil;
//import cn.hutool.json.JSONUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * @author wangfeng
// * @version 1.0
// * @mail wangfengbabe@163.com
// * @data 2024/9/28 15:28
// */
//@Service
//@Slf4j
//public class AuditServiceImpl<VO, DTO> implements AuditService<VO, DTO> {
//
//    @Resource
//    private MgtCheckService mgtCheckService;
//
//    @Override
//    public boolean existsInMgtCheck(VO vo, Class<DTO> dtoClass) {
//        DTO dto = BeanUtil.copyProperties(vo, dtoClass);
//        //查询待审核的记录
//        List<MgtCheck> list = mgtCheckService.queryByMenuAndStatus();
//        if(CollectionUtil.isEmpty(list)){
//            return false;
//        }
//        //待审核内容是否与业务相关
//        for(item : list){
//            String beofreContent = item.getBeforeCOntent(0);
//            String afterContent = item.getAfterContent(0);
//            String content = beofreContent?afterContent
//            existedDTO = JSONUtil.toBean(content,dtoClass);
//            if(isSameBizCheck(dto,existedDTO)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    @Override
//    public boolean add(VO vo, Class<DTO> dtoClass) {
//        DTO dto = BeanUtil.copyProperties(vo, dtoClass);
//        //1. 新增内容校验
//        addCheck(dto);
//        //2. 校验通过，则插入审核表待审核
//        mgtCheckService.add(PAGE_ENUM, ADD, null, dto);
//        return false;
//    }
//
//    @Override
//    public void addCheck(DTO dto) {
//        //1. 内容是否已存在
//        //2. 审核表中是否已存在
//    }
//
//    @Override
//    public boolean update(VO vo, Class<DTO> dtoClass) {
//        DTO dto = BeanUtil.copyProperties(vo, dtoClass);
//        //1. 校验
//        updateCheck(dto);
//        //2. 校验通过，则插入审核表待审核
//        DTO contentBeforeModified = getContentBeforeModified(dto);
//        mgtCheckService.add(PAGE_ENUM, UPDATE, contentBeforeModified, dto);
//        return false;
//    }
//
//    private DTO getContentBeforeModified(DTO dto) {
//        return null;
//    }
//
//    @Override
//    public boolean delete(VO vo, Class<DTO> dtoClass) {
//        DTO dto = BeanUtil.copyProperties(vo, dtoClass);
//        //1. 校验
//        deleteCheck(dto);
//        //2. 校验通过，则插入审核表待审核
//        DTO contentBeforeModified = getContentBeforeModified(dto);
//        mgtCheckService.add(PAGE_ENUM, DELETE, contentBeforeModified, null);
//        return false;
//    }
//
//    @Override
//    public boolean addAccept(Long id, Class<DTO> dtoClass) {
//        return false;
//    }
//
//    @Override
//    public boolean addReject(Long id, Class<DTO> dtoClass) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteAccept(Long id, Class<DTO> dtoClass) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteReject(Long id, Class<DTO> dtoClass) {
//        return false;
//    }
//
//    @Override
//    public boolean updateAccept(Long id, Class<DTO> dtoClass) {
//        return false;
//    }
//
//    @Override
//    public boolean updateReject(Long id, Class<DTO> dtoClass) {
//        return false;
//    }
//}
