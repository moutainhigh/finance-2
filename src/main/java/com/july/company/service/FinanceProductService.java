package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.FinanceProductDto;
import com.july.company.entity.FinanceProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.finance.FinanceProductDetailVo;
import com.july.company.vo.finance.FinanceProductVo;

/**
 * 融资产品信息 服务类
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface FinanceProductService extends IService<FinanceProduct> {

    /**
     * 获取产品列表信息
     * @param page
     * @param stockCompanyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceProductVo>
     * @author zengxueqi
     * @since 2020/5/20
     */
    IPage<FinanceProductVo> getFinanceProduct(Page<FinanceProductVo> page, FinanceProductDto stockCompanyDto);

    /**
     * 获取产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    FinanceProductDetailVo getFinanceProductDetail(FinanceProductDetailDto financeProductDetailDto);

}
