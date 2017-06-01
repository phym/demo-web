package com.ssm.framework.service.role;

import com.ssm.framework.model.role.Role;

/**
 * 
 * 角色Service
 * RoleService.
 *
 * @author zax
 */
public interface RoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
