package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.finance.ProductGroupDto;
import com.july.company.dto.institution.ListInstitutionDto;
import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.FinanceProduct;
import com.july.company.entity.Institution;
import com.july.company.entity.SysCode;
import com.july.company.entity.enums.FinanceTypeEnum;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.mapper.InstitutionMapper;
import com.july.company.service.CompanyService;
import com.july.company.service.InstitutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.SysCodeService;
import com.july.company.vo.finance.FinanceStatisticsVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import com.july.company.vo.institution.InstitutionAndRegionVo;
import com.july.company.vo.institution.InstitutionProductVo;
import com.july.company.vo.institution.ListInstitutionVo;
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
    @Resource
    private InstitutionMapper institutionMapper;
    @Resource
    private FinanceProductMapper financeProductMapper;
    @Resource
    private SysCodeService sysCodeService;

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
        List<InstitutionAndRegionVo> institutionAndRegionVoList = institutionMapper.getInstitutionAndRegion();
        //获取机构与产品的对应关系
        List<FinanceProduct> financeProducts = financeProductMapper.getInstitutionProduct();
        List<InstitutionAndRegionVo> institutionAndRegionVos = new ArrayList<>();
        institutionAndRegionVoList.stream().forEach(institutionAndRegionVo -> {
            List<InstitutionProductVo> institutionProductVos = new ArrayList<>();
            financeProducts.stream().forEach(financeProduct -> {
                if (institutionAndRegionVo.getId().equals(financeProduct.getInstitutionId())) {
                    institutionProductVos.add(InstitutionProductVo.builder()
                            .productName(financeProduct.getTitle())
                            .build());
                }
            });
            institutionAndRegionVo.setInstitutionProductVos(institutionProductVos);
            institutionAndRegionVos.add(institutionAndRegionVo);
        });
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
        //股权产品数量
        Integer stockCount = 0;
        //债券产品数量
        Integer bondCount = 1000;

        List<ProductGroupDto> productGroupDtos = financeProductMapper.getProductGroupCount();
        if (!CollectionUtils.isEmpty(productGroupDtos)) {
            for (ProductGroupDto productGroupDto : productGroupDtos) {
                if (FinanceTypeEnum.STOCKRIGHT.getValue().equals(productGroupDto.getFinanceType())) {
                    stockCount = productGroupDto.getProductCount();
                } else if (FinanceTypeEnum.BOND.getValue().equals(productGroupDto.getFinanceType())) {
                    bondCount = productGroupDto.getProductCount();
                }
            }
        }
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

    /**
     * 获取机构列表信息
     * @param page
     * @param listInstitutionDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.institution.ListInstitutionVo>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public IPage<ListInstitutionVo> getInstitution(Page<ListInstitutionVo> page, ListInstitutionDto listInstitutionDto) {
        //获取机构分页列表信息
        IPage<ListInstitutionVo> listInstitutionVoIPage = institutionMapper.getInstitution(page, listInstitutionDto);
        List<ListInstitutionVo> institutionVoList = listInstitutionVoIPage.getRecords();

        //获取所属地区信息
        List<SysCode> sysCodes = sysCodeService.getSysCodeByType(SystemConstant.REGION);

        List<ListInstitutionVo> listInstitutionVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(institutionVoList)) {
            listInstitutionVos = institutionVoList.stream().map(listInstitutionVo -> {
                sysCodes.stream().forEach(sysCode -> {
                    if (sysCode.getCode().equals(listInstitutionVo.getRegionName())) {
                        listInstitutionVo.setRegionName(sysCode.getValue());
                    }
                });
                return listInstitutionVo;
            }).collect(Collectors.toList());
        }
        listInstitutionVoIPage.setRecords(listInstitutionVos);
        return listInstitutionVoIPage;
    }

}
