package com.july.company.service;

import com.july.company.dto.role.UserRoleDto;
import com.july.company.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户与角色关系 服务类
 * @author zengxueqi
 * @since 2020-06-05
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 获取用户的角色信息
     * @param userId
     * @return java.util.List<com.july.company.dto.role.UserRoleDto>
     * @author zengxueqi
     * @since 2020/6/5
     */
    List<UserRoleDto> getUserRole(Long userId);

}
