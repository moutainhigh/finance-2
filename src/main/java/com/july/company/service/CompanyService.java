package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.company.CompanyVo;
import com.july.company.vo.finance.FinanceStockProductVo;

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
    IPage<CompanyVo> getCompanyList(Page<CompanyVo> objectPage, Company content);
}
