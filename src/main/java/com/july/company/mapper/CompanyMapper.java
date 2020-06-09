package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.company.CompanyDto;
import com.july.company.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.company.CompanyVo;
import org.apache.ibatis.annotations.Param;

/**
 * 注册的公司信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 获取企业信息
     * @param page
     * @param companyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.company.CompanyVo>
     * @author zengxueqi
     * @since 2020/6/6
     */
    IPage<CompanyVo> findCompanyList(Page<CompanyVo> page, @Param("param") CompanyDto companyDto);

}
