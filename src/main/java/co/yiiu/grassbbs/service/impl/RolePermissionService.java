package co.yiiu.grassbbs.service.impl;

import co.yiiu.grassbbs.mapper.RolePermissionMapper;
import co.yiiu.grassbbs.model.RolePermission;
import co.yiiu.grassbbs.service.IRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@Service
@Transactional
public class RolePermissionService implements IRolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    // 根据角色id查询所有的角色权限关联记录
    @Override
    public List<RolePermission> selectByRoleId(Integer roleId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(RolePermission::getRoleId, roleId);
        return rolePermissionMapper.selectList(wrapper);
    }

    // 根据角色id删除关联关系
    @Override
    public void deleteByRoleId(Integer roleId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(RolePermission::getRoleId, roleId);
        rolePermissionMapper.delete(wrapper);
    }

    // 根据权限id删除关联关系
    @Override
    public void deleteByPermissionId(Integer permissionId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(RolePermission::getPermissionId, permissionId);
        rolePermissionMapper.delete(wrapper);
    }

    @Override
    public void insert(RolePermission rolePermission) {
        rolePermissionMapper.insert(rolePermission);
    }

}
