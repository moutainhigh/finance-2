package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.*;
import com.july.company.entity.FinanceProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.finance.*;

import java.util.List;

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
    IPage<BondListVo> getBondList(Page<BondListVo> page, ListConditionDto listConditionDto);

    /**
     * 导出债券产品信息
     * @param page
     * @param listConditionDto
     * @return java.util.List<com.july.company.vo.finance.BondListExcelVo>
     * @author zengxueqi
     * @since 2020/6/16
     */
    List<BondListExcelVo> exportBondProduct(Page<BondListVo> page, ListConditionDto listConditionDto);

    /**
     * 获取股权产品信息
     * @param selectProductDto
     * @return com.july.company.vo.finance.StockListVo
     * @author zengxueqi
     * @since 2020/6/11
     */
    StockEditDetailVo getStockByProductId(SelectProductDto selectProductDto);

    /**
     * 债权融资信息根据产品ID查询
     * @param selectProductDto
     * @return com.july.company.vo.finance.BondEditDetailVo
     * @author zengxueqi
     * @since 2020/6/11
     */
    BondEditDetailVo getBondByProductId(SelectProductDto selectProductDto);

    /**
     * 股权产品excel信息
     * @param page
     * @param listStockConditionDto
     * @return java.util.List<com.july.company.vo.finance.StockExcelListVo>
     * @author zengxueqi
     * @since 2020/6/11
     */
    List<StockExcelListVo> getStockExcelList(Page<StockListVo> page, ListStockConditionDto listStockConditionDto);

    /**
     * 保存股权融资产品信息
     * @param stockEditDetailDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/12
     */
    void saveStockProduct(StockEditDetailDto stockEditDetailDto);

    /**
     * 修改保存或者添加债权信息(后台)
     * @param bondSaveDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    void updateOrAddFinanceBond(BondSaveDetailDto bondSaveDetailDto);

    /**
     * 删除债权信息(后台)
     * @param bondDeleteDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    void deleteBondList(BondDeleteDetailDto bondDeleteDetailDto);

    /**
     * 删除股权信息(后台)
     * @param bondDeleteDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    void deleteStockList(BondDeleteDetailDto bondDeleteDetailDto);

    /**
     * 产品信息操作(后台)
     * @param productOperateDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/13
     */
    void updateProductOperate(ProductOperateDto productOperateDto);

    /**
     * 编辑产品页面发布(后台)
     * @param bondSaveDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    void updateFinanceProductStatus(UpdateStatusDto bondSaveDetailDto);
}
