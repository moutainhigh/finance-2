package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.stock.StockCompanyDetailDto;
import com.july.company.dto.stock.StockCompanyDto;
import com.july.company.entity.FinanceCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.stock.StockCompanyDetailVo;
import com.july.company.vo.stock.StockCompanyVo;

/**
 * 融资公司信息 服务类
 * @author zengxueqi
 * @since 2020-05-17
 */
public interface FinanceCompanyService extends IService<FinanceCompany> {

    /**
     * 获取股权融资公司列表信息
     * @param page
     * @param stockCompanyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.stock.StockCompanyVo>
     * @author zengxueqi
     * @since 2020/5/17
     */
    IPage<StockCompanyVo> getFinanceCompany(Page<StockCompanyVo> page, StockCompanyDto stockCompanyDto);

    /**
     * 获取股权融资公司明细信息
     * @param stockCompanyDetailDto
     * @return com.july.company.vo.stock.StockCompanyDetailVo
     * @author zengxueqi
     * @since 2020/5/17
     */
    StockCompanyDetailVo getFinanceCompanyDetail(StockCompanyDetailDto stockCompanyDetailDto);

}
