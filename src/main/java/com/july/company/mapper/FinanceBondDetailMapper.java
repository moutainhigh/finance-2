package com.july.company.mapper;

import com.july.company.entity.FinanceBondDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 融资公司明细信息 Mapper 接口
 * </p>
 *
 * @author zengxueqi
 * @since 2020-05-26
 */
public interface FinanceBondDetailMapper extends BaseMapper<FinanceBondDetail> {

    /**
     * 根据产品ID获取债券产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    FinanceBondDetail getFinanceProductDetail(Long productId);
}
