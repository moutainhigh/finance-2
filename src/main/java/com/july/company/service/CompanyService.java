package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.company.*;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.company.CompanyMatchVo;
import com.july.company.vo.company.CompanyVo;

/**
 * 注册的公司信息 服务类
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface CompanyService extends IService<Company> {

    /**
     * 获取公司入驻数量
     * @param
     * @return java.lang.Integer
     * @author zengxueqi
     * @since 2020/5/21
     */
    Integer getCompanyCount();

    /**
     * 获取入驻公司列表信息
     * @param
     * @author xia.junwei
     * @since 2020/5/28
     */
    IPage<CompanyVo> getCompanyList(Page<CompanyVo> objectPage, CompanyDto companyDto);

    /**
     * 修改企业信息
     * @param updateCompanyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/7
     */
    void updateCompany(UpdateCompanyDto updateCompanyDto);

    /**
     * 获取某个企业的信息
     * @param selectCompanyDto
     * @return com.july.company.vo.company.CompanyVo
     * @author zengxueqi
     * @since 2020/6/7
     */
    CompanyVo getCompanyInfo(SelectCompanyDto selectCompanyDto);

    /**
     * 删除企业信息
     * @param deleteCompanyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/7
     */
    void deleteCompany(DeleteCompanyDto deleteCompanyDto);

    /**
     * 判断企业是否一键匹配过信息
     * @param companyMatchDto
     * @return com.july.company.vo.company.CompanyMatchVo
     * @author zengxueqi
     * @since 2020/6/10
     */
    CompanyMatchVo getCompanyBoolMatch(CompanyMatchDto companyMatchDto);

}
