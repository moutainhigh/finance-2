package com.july.company.service;

import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.entity.FinanceBondDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.finance.FinanceBondProductDetailVo;

/**
 * 融资公司明细信息 服务类
 * @author zengxueqi
 * @since 2020-05-26
 */
public interface FinanceBondDetailService extends IService<FinanceBondDetail> {

    /**
     * 获取债券融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    FinanceBondProductDetailVo getFinanceBondProductDetail(FinanceProductDetailDto financeProductDetailDto);

    /**
     * 一键匹配债券产品信息
     * @param bondProductMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    String getBondOneKeyMatching(BondProductMatchDto bondProductMatchDto);

}
