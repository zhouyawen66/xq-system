package com.cnaidun.police.util;

import com.cnaidun.police.dto.MenuResponseDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeUtil
 * @Description TODO
 * @Author zyw
 * @Date 2019/12/4 8:52
 * @Version 1.0
 */
public class TreeUtil {

    /**
     * description: 递归树用于构建菜单或部门树 <br>
     * version: 1.0 <br>
     * date: 2019/12/4 9:15 <br>
     * author: zhouyw <br>
     * <p>
     * [userPermissions]
     *
     * @return java.util.List<com.cnaidun.sxtrafficexamination.modular.system.dto.MenuResponseDTO>
     */
    public static List<MenuResponseDTO> getTreeList(List<MenuResponseDTO> userPermissions) {
        List<MenuResponseDTO> resultList = new ArrayList<>();
        //获取顶层元素集合
        userPermissions.forEach(entity -> {
            Long parentId = entity.getParentId();
            //顶层元素的parentCode==null或者为0
            if (parentId == null || 0 == parentId) {
                resultList.add(entity);
            }
        });
        //获取每个顶层元素的子数据集合
        resultList.forEach(entity -> {
            entity.setChildren(getSubList(entity.getParentId(), userPermissions));
        });
        return resultList;

    }

    private static List<MenuResponseDTO> getSubList(Long id, List<MenuResponseDTO> entityList) {
        List<MenuResponseDTO> childList = new ArrayList<>();
        //获取顶层元素集合
        entityList.forEach(entity -> {
            Long parentId = entity.getParentId();
            if (id == parentId) {
                childList.add(entity);
            }
        });

        if(CollectionUtils.isNotEmpty(childList)){
            childList.forEach(entity -> {
                entity.setChildren(getSubList(entity.getParentId(), entityList));
            });
        }else {
            return childList;
        }
        return childList;
    }
}
