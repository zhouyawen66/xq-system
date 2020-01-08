package com.cnaidun.police.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cnaidun.police.config.mybatis.BeanUtil;
import com.cnaidun.police.config.mybatis.PageRequest;
import com.cnaidun.police.config.mybatis.PagedResult;
import com.cnaidun.police.config.tip.SuccessTip;
import com.cnaidun.police.config.tip.Tip;
import com.cnaidun.police.dto.ProjectConfigDTO;
import com.cnaidun.police.entity.TbScoreProjectConfig;
import com.cnaidun.police.mapper.TbScoreProjectConfigMapper;
import com.cnaidun.police.service.TbScoreProjectConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TbScoreProjectConfigServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 15:19
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
@Slf4j
public class TbScoreProjectConfigServiceImpl extends ServiceImpl<TbScoreProjectConfigMapper, TbScoreProjectConfig> implements TbScoreProjectConfigService {
    @Autowired
    TbScoreProjectConfigMapper scoreProjectConfigMapper;


    @Override
    public PagedResult<ProjectConfigDTO> findProjectConfigList(Integer pageNo, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, true);
        List<ProjectConfigDTO> roleListResponseDTOS = scoreProjectConfigMapper.findProjectConfigList(pageRequest);
        return BeanUtil.toPagedResult(roleListResponseDTOS, pageRequest);
    }

    @Override
    public Tip addOrUpdateConfig(ProjectConfigDTO projectConfigDTO) {
        if (null == projectConfigDTO.getId()) {
            scoreProjectConfigMapper.addProjectConfig(projectConfigDTO);
        } else {
            scoreProjectConfigMapper.updateProjectConfig(projectConfigDTO);
        }
        return new SuccessTip("成功");
    }

    @Override
    public Tip setStatus(String status, Long id) {
        ProjectConfigDTO dto = ProjectConfigDTO.builder().id(id).status(status).build();
        scoreProjectConfigMapper.updateProjectConfig(dto);
        return new SuccessTip("成功");
    }
}
