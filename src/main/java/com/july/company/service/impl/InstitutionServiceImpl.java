package com.july.company.service.impl;

import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.july.company.mapper.InstitutionMapper;
import com.july.company.service.InstitutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 金融机构信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-20
 */
@Service
public class InstitutionServiceImpl extends ServiceImpl<InstitutionMapper, Institution> implements InstitutionService {

    /**
     * 保存机构信息
     * @param saveInstitutionDto
     * @return void
     * @author zengxueqi
     * @since 2020/5/21
     */
    @Override
    public void saveInstitution(SaveInstitutionDto saveInstitutionDto) {
        Institution institution = Institution.builder()
                .institutionCode(saveInstitutionDto.getInstitutionCode())
                .institutionType(saveInstitutionDto.getInstitutionType())
                .institutionName(saveInstitutionDto.getInstitutionName())
                .build();
        this.save(institution);
    }

}
