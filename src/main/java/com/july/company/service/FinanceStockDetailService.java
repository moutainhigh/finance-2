package com.july.company.service;

import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.entity.FinanceStockDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.finance.FinanceStockProductDetailVo;

/**
 * 融资公司明细信息 服务类
 * @author zengxueqi
 * @since 2020-05-26
 */
public interface FinanceStockDetailService extends IService<FinanceStockDetail> {

    /**
     * 获取股权融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    FinanceStockProductDetailVo getFinanceStockProductDetail(FinanceProductDetailDto financeProductDetailDto);

    /**
     * 一键匹配股权产品信息
     * @param stockProductMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    String getStockOneKeyMatching(StockProductMatchDto stockProductMatchDto);

}