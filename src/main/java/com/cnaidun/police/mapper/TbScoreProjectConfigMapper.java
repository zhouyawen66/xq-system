package com.cnaidun.police.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.dto.ProjectConfigDTO;
import com.cnaidun.police.entity.TbScoreProjectConfig;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author kitty_zhu
 * @date 2019-12-04 15:21
 */
@Mapper
public interface TbScoreProjectConfigMapper extends BaseMapper<TbScoreProjectConfig> {
    int addProjectConfig(ProjectConfigDTO addProjectConfigDTO);

    List<ProjectConfigDTO> findProjectConfigList(PageRequest pageRequest);

    int updateProjectConfig(ProjectConfigDTO projectConfigDTO);
}
