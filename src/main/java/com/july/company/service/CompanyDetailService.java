package com.july.company.service;

import com.july.company.entity.CompanyDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 融资公司明细信息 服务类
 * @author zengxueqi
 * @since 2020-05-17
 */
public interface CompanyDetailService extends IService<CompanyDetail> {

    /**
     * 获取融资公司的明细信息
     * @param companyId
     * @return java.util.List<com.july.company.entity.CompanyDetail>
     * @author zengxueqi
     * @since 2020/5/17
     */
    CompanyDetail getCompanyDetail(Long companyId);

}
