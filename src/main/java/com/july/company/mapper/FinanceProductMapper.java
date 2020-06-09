package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.*;
import com.july.company.entity.FinanceProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.finance.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface FinanceProductMapper extends BaseMapper<FinanceProduct> {

    /**
     * 获取股权产品列表信息
     * @param page
     * @param financeStockProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceStockProductVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    IPage<FinanceStockProductVo> getFinanceStockProduct(Page<FinanceStockProductVo> page, @Param("param") FinanceStockProductDto financeStockProductDto);

    /**
     * 获取债券产品列表信息
     * @param page
     * @param financeBondProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceBondProductVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    IPage<FinanceBondProductVo> getFinanceBondProduct(Page<FinanceBondProductVo> page, @Param("param") FinanceBondProductDto financeBondProductDto);

    /**
     * 获取所有的债券产品信息
     * @param
     * @return java.util.List<com.july.company.dto.finance.ProductInfoDto>
     * @author zengxueqi
     * @since 2020/5/26
     */
    List<BondProductInfoDto> getAllBondProduct();

    /**
     * 获取所有的股权产品信息
     * @param
     * @return java.util.List<com.july.company.dto.finance.ProductInfoDto>
     * @author zengxueqi
     * @since 2020/5/26
     */
    List<StockProductInfoDto> getAllStockProduct();

    /**
     * 获取机构与产品的对应关系
     * @param
     * @return java.util.List<com.july.company.entity.FinanceProduct>
     * @author zengxueqi
     * @since 2020/5/26
     */
    List<FinanceProduct> getInstitutionProduct();

    /**
     * 融资机构类型对应的产品数量
     * @param
     * @return java.util.List<com.july.company.dto.finance.ProductGroupDto>
     * @author zengxueqi
     * @since 2020/5/26
     */
    List<ProductGroupDto> getProductGroupCount();
    /**
     * 获取股权融资信息分页（后台）
     * @author xia.junwei
     * @since 2020/6/8
     */
    IPage<StockListVo> getStockList(Page<ListConditionDto> page, @Param("param")ListConditionDto listConditionDto);
    /**
     * 获取债权融资信息分页（后台）
     * @author xia.junwei
     * @since 2020/6/8
     */
    IPage<BondListVo> getBondList(Page<ListConditionDto> page, @Param("param")ListConditionDto listConditionDto);
}
