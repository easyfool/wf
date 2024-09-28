package com.wf.mp.service.audit;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/28 15:25
 */
public interface AuditService<VO, DTO> {

    boolean existsInMgtCheck(VO vo, Class<DTO> dtoClass);

    boolean add(VO vo, Class<DTO> dtoClass);

    void addCheck(DTO dto);

    boolean update(VO vo, Class<DTO> dtoClass);

    boolean delete(VO vo, Class<DTO> dtoClass);

    boolean addAccept(Long id, Class<DTO> dtoClass);

    boolean addReject(Long id, Class<DTO> dtoClass);

    boolean deleteAccept(Long id, Class<DTO> dtoClass);

    boolean deleteReject(Long id, Class<DTO> dtoClass);

    boolean updateAccept(Long id, Class<DTO> dtoClass);

    boolean updateReject(Long id, Class<DTO> dtoClass);
}
