package com.ssm.framework.service.impl.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.role.RoleDao;
import com.ssm.framework.model.role.Role;
import com.ssm.framework.service.role.RoleService;

/**
 * 角色Service
 * 
 * RoleServiceImpl.
 *
 * @author zax
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDao roleDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        // TODO Auto-generated method stub
        return roleDao.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleDao.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleDao.updateByPrimaryKey(record);
    }
}
