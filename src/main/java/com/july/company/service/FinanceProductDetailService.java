package com.july.company.service;

import com.july.company.entity.FinanceProductDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 产品明细信息 服务类
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface FinanceProductDetailService extends IService<FinanceProductDetail> {

    /**
     * 获取产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    FinanceProductDetail getFinanceProductDetail(Long productId);


}
