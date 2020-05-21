package com.july.company.service;

import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.institution.InstitutionAndRegionVo;

import java.util.List;

/**
 * 金融机构信息 服务类
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface InstitutionService extends IService<Institution> {

    /**
     * 保存机构信息
     * @param saveInstitutionDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/21
     */
    void saveInstitution(SaveInstitutionDto saveInstitutionDto);

    /**
     * 获取机构与机构对应的地区信息
     * @param
     * @return java.util.List<com.july.company.vo.institution.InstitutionAndRegionVo>
     * @author zengxueqi
     * @since 2020/5/21
     */
    List<InstitutionAndRegionVo> getInstitutionAndRegion();

}
