package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.company.CompanyVo;

/**
 * 注册的公司信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface CompanyMapper extends BaseMapper<Company> {

    IPage<CompanyVo> findCompanyList(Page<CompanyVo> page, Company company);
}
