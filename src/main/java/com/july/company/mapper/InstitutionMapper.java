package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.dto.institution.ListInstitutionDto;
import com.july.company.entity.Institution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.finance.FinanceStockProductVo;
import com.july.company.vo.institution.InstitutionAndRegionVo;
import com.july.company.vo.institution.ListInstitutionVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 获取机构列表信息
     * @param page
     * @param listInstitutionDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.institution.ListInstitutionVo>
     * @author zengxueqi
     * @since 2020/6/10
     */
    IPage<ListInstitutionVo> getInstitution(Page<ListInstitutionVo> page, @Param("param") ListInstitutionDto listInstitutionDto);

}
