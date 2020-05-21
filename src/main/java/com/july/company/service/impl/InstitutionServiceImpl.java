package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.july.company.mapper.InstitutionMapper;
import com.july.company.service.CompanyService;
import com.july.company.service.InstitutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.finance.FinanceStatisticsVo;
import com.july.company.vo.institution.InstitutionAndRegionVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
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

    @Resource
    private CompanyService companyService;

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

    /**
     * 获取融资统计信息
     * @param
     * @return com.july.company.vo.finance.FinanceStatisticsVo
     * @author zengxueqi
     * @since 2020/5/21
     */
    @Override
    public FinanceStatisticsVo getFinanceStatistics() {
        //公司入驻数量
        Integer companyCount = companyService.getCompanyCount();
        //机构入驻数量
        Integer institutionCount = getInstitutionCount();
        //TODO 股权产品数量
        Integer stockCount = 13140;
        //TODO 债券产品数量
        Integer bondCount = 1000;
        //TODO 匹配数量
        Integer matchCount = 10;
        //TODO 匹配金额
        Double matchMoney = 13.16;

        FinanceStatisticsVo financeStatisticsVo = FinanceStatisticsVo.builder()
                .bondCount(bondCount)
                .stockCount(stockCount)
                .companyCount(companyCount)
                .institutionCount(institutionCount)
                .matchCount(matchCount)
                .matchMoney(matchMoney)
                .build();
        return financeStatisticsVo;
    }

    /**
     * 获取机构入驻数量
     * @param
     * @return java.lang.Integer
     * @author zengxueqi
     * @since 2020/5/21
     */
    public Integer getInstitutionCount() {
        QueryWrapper<Institution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", SystemConstant.SYS_FALSE);
        List<Institution> institutions = this.list(queryWrapper);
        return CollectionUtils.isEmpty(institutions) ? 0 : institutions.size();
    }

}
