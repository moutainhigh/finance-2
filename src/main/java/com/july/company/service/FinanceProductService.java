package com.july.company.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.finance.*;
import com.july.company.entity.FinanceProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.entity.FinanceStockDetail;
import com.july.company.vo.finance.*;

/**
 * 融资产品信息 服务类
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface FinanceProductService extends IService<FinanceProduct> {

    /**
     * 获取产品列表信息
     * @param page
     * @param financeStockProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceStockProductVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    IPage<FinanceStockProductVo> getFinanceStockProduct(Page<FinanceStockProductVo> page, FinanceStockProductDto financeStockProductDto);

    /**
     * 获取债券产品列表信息
     * @param page
     * @param financeBondProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceBondProductVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    IPage<FinanceBondProductVo> getFinanceBondProduct(Page<FinanceBondProductVo> page, FinanceBondProductDto financeBondProductDto);

    /**
     * 删除产品信息
     * @param oneProductDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/4
     */
    void deleteProduct(OneProductDto oneProductDto);

    /**
     * 获取股权融资信息分页（后台）
     * @param page
     * @param content
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.StockListVo>
     * @author zengxueqi
     * @since 2020/6/10
     */
    IPage<StockListVo> getStockList(Page<StockListVo> page, ListStockConditionDto content);

    /**
     * 获取股权产品的信息
     * @param id
     * @author xiajunwei
     * @since 2020/6/8
     */
    FinanceProduct getFinanceProductById(Long id);

    /**
     * 获取债权融资信息分页（后台）
     * @author xia.junwei
     * @since 2020/6/8
     */
    IPage<BondListVo> getBondList(Page<ListConditionDto> page, ListConditionDto listConditionDto);

}
