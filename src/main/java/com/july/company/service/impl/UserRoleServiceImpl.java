package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.role.UserRoleDto;
import com.july.company.entity.UserRole;
import com.july.company.mapper.UserRoleMapper;
import com.july.company.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户与角色关系 服务实现类
 * @author zengxueqi
 * @since 2020-06-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    /**
     * 获取用户的角色信息
     * @param userId
     * @return java.util.List<com.july.company.dto.role.UserRoleDto>
     * @author zengxueqi
     * @since 2020/6/5
     */
    @Override
    public List<UserRoleDto> getUserRole(Long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        List<UserRole> userRoleList = this.list(queryWrapper);

        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            userRoleDtos = userRoleList.stream().map(userRole -> UserRoleDto.builder().roleId(userRole.getRoleId()).userId(userRole.getUserId()).build())
                    .collect(Collectors.toList());
        }
        return userRoleDtos;
    }

}
