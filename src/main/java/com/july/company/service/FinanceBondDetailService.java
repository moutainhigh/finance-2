package com.july.company.service;

import com.july.company.dto.finance.BondSaveDetailDto;
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
     * 根据Id更改融资产品明细信息
     * @author xiajunwei
     * @since 2020/6/9
     */
    void updateFinanceBondProductDetailById(BondSaveDetailDto bondSaveDetailDto);

    /**
     * 根据产品ID获取债券产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    FinanceBondDetail getFinanceProductDetail(Long productId);

}
