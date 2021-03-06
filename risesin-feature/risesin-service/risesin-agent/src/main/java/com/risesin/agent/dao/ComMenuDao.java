package com.risesin.agent.dao;

import com.risesin.agent.entity.ComMenu;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comMenu数据访问接口
 *
 * @author Administrator
 */
public interface ComMenuDao extends JpaRepository<ComMenu, Integer>, JpaSpecificationExecutor<ComMenu>, BaseDao<ComMenu, Integer> {

}
