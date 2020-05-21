package com.july.company.service;

import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
