package com.risesin.agent.service;

import com.risesin.agent.dao.ComLogErrorDao;
import com.risesin.agent.entity.ComLogError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * comLogError服务层
 *
 * @author Administrator
 */
@Service
public class ComLogErrorService {

    @Autowired
    private ComLogErrorDao comLogErrorDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComLogError> findAll() {
        return comLogErrorDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComLogError> findSearch(Map whereMap, int page, int size) {
        Specification<ComLogError> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comLogErrorDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComLogError> findSearch(Map whereMap) {
        Specification<ComLogError> specification = createSpecification(whereMap);
        return comLogErrorDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public ComLogError findById(Integer id) {
        return comLogErrorDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param comLogError
     */
    public void add(ComLogError comLogError) {
        // comLogError.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comLogErrorDao.save(comLogError);
    }

    /**
     * 修改
     *
     * @param comLogError
     */
    @Transactional
    public void update(ComLogError comLogError) {
        comLogErrorDao.update(comLogError, comLogError.getId());
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional
    public void deleteById(Integer id) {
        comLogErrorDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComLogError> createSpecification(Map searchMap) {

        return new Specification<ComLogError>() {

            @Override
            public Predicate toPredicate(Root<ComLogError> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 服务ID
                if (searchMap.get("serviceId") != null && !"".equals(searchMap.get("serviceId"))) {
                    predicateList.add(cb.like(root.get("serviceId").as(String.class), "%" + (String) searchMap.get("serviceId") + "%"));
                }
                // 服务器名
                if (searchMap.get("serverHost") != null && !"".equals(searchMap.get("serverHost"))) {
                    predicateList.add(cb.like(root.get("serverHost").as(String.class), "%" + (String) searchMap.get("serverHost") + "%"));
                }
                // 服务器IP地址
                if (searchMap.get("serverIp") != null && !"".equals(searchMap.get("serverIp"))) {
                    predicateList.add(cb.like(root.get("serverIp").as(String.class), "%" + (String) searchMap.get("serverIp") + "%"));
                }
                // 系统环境
                if (searchMap.get("env") != null && !"".equals(searchMap.get("env"))) {
                    predicateList.add(cb.like(root.get("env").as(String.class), "%" + (String) searchMap.get("env") + "%"));
                }
                // 操作方式
                if (searchMap.get("method") != null && !"".equals(searchMap.get("method"))) {
                    predicateList.add(cb.like(root.get("method").as(String.class), "%" + (String) searchMap.get("method") + "%"));
                }
                // 请求URI
                if (searchMap.get("requestUri") != null && !"".equals(searchMap.get("requestUri"))) {
                    predicateList.add(cb.like(root.get("requestUri").as(String.class), "%" + (String) searchMap.get("requestUri") + "%"));
                }
                // 用户代理
                if (searchMap.get("userAgent") != null && !"".equals(searchMap.get("userAgent"))) {
                    predicateList.add(cb.like(root.get("userAgent").as(String.class), "%" + (String) searchMap.get("userAgent") + "%"));
                }
                // 堆栈
                if (searchMap.get("stackTrace") != null && !"".equals(searchMap.get("stackTrace"))) {
                    predicateList.add(cb.like(root.get("stackTrace").as(String.class), "%" + (String) searchMap.get("stackTrace") + "%"));
                }
                // 异常名
                if (searchMap.get("exceptionName") != null && !"".equals(searchMap.get("exceptionName"))) {
                    predicateList.add(cb.like(root.get("exceptionName").as(String.class), "%" + (String) searchMap.get("exceptionName") + "%"));
                }
                // 异常信息
                if (searchMap.get("message") != null && !"".equals(searchMap.get("message"))) {
                    predicateList.add(cb.like(root.get("message").as(String.class), "%" + (String) searchMap.get("message") + "%"));
                }
                // 操作IP地址
                if (searchMap.get("remoteIp") != null && !"".equals(searchMap.get("remoteIp"))) {
                    predicateList.add(cb.like(root.get("remoteIp").as(String.class), "%" + (String) searchMap.get("remoteIp") + "%"));
                }
                // 方法类
                if (searchMap.get("methodClass") != null && !"".equals(searchMap.get("methodClass"))) {
                    predicateList.add(cb.like(root.get("methodClass").as(String.class), "%" + (String) searchMap.get("methodClass") + "%"));
                }
                // 文件名
                if (searchMap.get("fileName") != null && !"".equals(searchMap.get("fileName"))) {
                    predicateList.add(cb.like(root.get("fileName").as(String.class), "%" + (String) searchMap.get("fileName") + "%"));
                }
                // 方法名
                if (searchMap.get("methodName") != null && !"".equals(searchMap.get("methodName"))) {
                    predicateList.add(cb.like(root.get("methodName").as(String.class), "%" + (String) searchMap.get("methodName") + "%"));
                }
                // 操作提交的数据
                if (searchMap.get("params") != null && !"".equals(searchMap.get("params"))) {
                    predicateList.add(cb.like(root.get("params").as(String.class), "%" + (String) searchMap.get("params") + "%"));
                }
                // 执行时间
                if (searchMap.get("time") != null && !"".equals(searchMap.get("time"))) {
                    predicateList.add(cb.like(root.get("time").as(String.class), "%" + (String) searchMap.get("time") + "%"));
                }
                // 创建者
                if (searchMap.get("createBy") != null && !"".equals(searchMap.get("createBy"))) {
                    predicateList.add(cb.like(root.get("createBy").as(String.class), "%" + (String) searchMap.get("createBy") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
