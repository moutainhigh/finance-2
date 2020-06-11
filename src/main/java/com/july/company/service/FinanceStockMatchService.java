package com.july.company.service;

import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.entity.FinanceStockMatch;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 股权融资一键匹配 服务类
 * @author zengxueqi
 * @since 2020-06-08
 */
public interface FinanceStockMatchService extends IService<FinanceStockMatch> {

    /**
     * 一键匹配股权产品信息
     * @param stockProductMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    String getStockOneKeyMatching(StockProductMatchDto stockProductMatchDto);

    /**
     * 保存股权融资匹配信息
     * @param stockProductMatchDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/9
     */
    void saveStockOneKeyMatching(StockProductMatchDto stockProductMatchDto);

    /**
     * 获取公司的一键匹配信息
     * @param companyId
     * @return java.util.List<com.july.company.entity.FinanceStockMatch>
     * @author zengxueqi
     * @since 2020/6/10
     */
    List<FinanceStockMatch> getFinanceStockMatch(Long companyId);

}
