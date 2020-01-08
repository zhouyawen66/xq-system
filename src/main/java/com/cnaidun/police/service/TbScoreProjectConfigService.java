package com.cnaidun.police.service;

import com.baomidou.mybatisplus.service.IService;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.ProjectConfigDTO;
import com.cnaidun.police.dto.RoleListResponseDTO;
import com.cnaidun.police.entity.TbScoreProjectConfig;

public interface TbScoreProjectConfigService extends IService<TbScoreProjectConfig> {
    PagedResult<ProjectConfigDTO> findProjectConfigList(Integer pageNo, Integer pageSize);

    Tip addOrUpdateConfig(ProjectConfigDTO projectConfigDTO);

    Tip setStatus(String status, Long id);
}
