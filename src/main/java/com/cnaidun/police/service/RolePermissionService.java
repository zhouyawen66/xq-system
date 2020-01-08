package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.entity.RolePermission;
import com.sun.rowset.internal.InsertRow;

public interface RolePermissionService extends IService<RolePermission> {

   void InsertRow(RolePermission rolePermission);
}
