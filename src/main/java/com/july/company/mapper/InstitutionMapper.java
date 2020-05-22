package com.july.company.mapper;

import com.july.company.entity.Institution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.institution.InstitutionAndRegionVo;

import java.util.List;

/**
 * 金融机构信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface InstitutionMapper extends BaseMapper<Institution> {

    /**
     * 保存机构信息
     * @param
     * @return java.util.List<com.july.company.vo.institution.InstitutionAndRegionVo>
     * @author zengxueqi
     * @since 2020/5/22
     */
    List<InstitutionAndRegionVo> getInstitutionAndRegion();

}
