package com.july.company.service.impl;

import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.july.company.mapper.InstitutionMapper;
import com.july.company.service.InstitutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.institution.InstitutionAndRegionVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取机构与机构对应的地区信息
     * @param
     * @return java.util.List<com.july.company.vo.institution.InstitutionAndRegionVo>
     * @author zengxueqi
     * @since 2020/5/21
     */
    @Override
    public List<InstitutionAndRegionVo> getInstitutionAndRegion() {
        List<Institution> institutions = this.list();

        List<InstitutionAndRegionVo> institutionAndRegionVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(institutions)) {
            institutionAndRegionVos = institutions.stream().map(institution ->
                    InstitutionAndRegionVo.builder()
                            .institutionName(institution.getInstitutionName())
                            .institutionType(institution.getInstitutionType())
                            .regionId(institution.getRegionId())
                            .build()
            ).collect(Collectors.toList());
        }
        return institutionAndRegionVos;
    }

}
