package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.dto.institution.ListInstitutionDto;
import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.finance.FinanceStatisticsVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import com.july.company.vo.institution.InstitutionAndRegionVo;
import com.july.company.vo.institution.ListInstitutionVo;

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

    /**
     * 获取融资统计信息
     * @param
     * @return com.july.company.vo.finance.FinanceStatisticsVo
     * @author zengxueqi
     * @since 2020/5/21
     */
    FinanceStatisticsVo getFinanceStatistics();

    /**
     * 获取机构列表信息
     * @param page
     * @param listInstitutionDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.institution.ListInstitutionVo>
     * @author zengxueqi
     * @since 2020/6/10
     */
    IPage<ListInstitutionVo> getInstitution(Page<ListInstitutionVo> page, ListInstitutionDto listInstitutionDto);

}
