package com.july.company.service;

import com.july.company.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
