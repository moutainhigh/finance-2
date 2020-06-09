package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.company.CompanyDto;
import com.july.company.dto.company.DeleteCompanyDto;
import com.july.company.dto.company.SelectCompanyDto;
import com.july.company.dto.company.UpdateCompanyDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * 通过用户查询公司信息
     * @author xiajunwei
     * @since 2020/6/9
     */
    public Company getCompanyByUser(UserInfoDto userInfoDto);

    /**
     * 一件匹配保存修改企业信息
     * @return void
     * @author xiajunwei
     * @since 2020/6/9
     */
    void updateCompanyForSaveOneMatch(Company company);
}
