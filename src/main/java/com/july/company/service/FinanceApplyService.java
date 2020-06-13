package com.july.company.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.apply.*;
import com.july.company.entity.FinanceApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.apply.SelectProductVo;
import com.july.company.vo.apply.StockCompanyDetalVo;

/**
 * 企业申请产品信息 服务类
 * @author zengxueqi
 * @since 2020-06-10
 */
public interface FinanceApplyService extends IService<FinanceApply> {

    /**
     * 保存企业申请的产品信息
     * @param productApplyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    void saveProductApply(ProductApplyDto productApplyDto);

    /**
     * 保存企业提交的产品信息
     * @param productCommitDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    void commitProductApply(ProductCommitDto productCommitDto);


    /**
     * 获取产品申请信息（后台）
     * @param page
     * @param selectProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.apply.SelectProductVo>
     * @author zengxueqi
     * @since 2020/6/12
     */
    IPage<SelectProductVo> getFinanceApply(Page<SelectProductVo> page, SelectProductDto selectProductDto);

    /**
     * 申请产品操作
     * @param productOperateDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/12
     */
    void applyProductOperate(ProductOperateDto productOperateDto);

    /**
     * 获取申请的企业信息(后台)
     * @param stockCompanyDto
     * @return com.july.company.vo.apply.StockCompanyDetalVo
     * @author zengxueqi
     * @since 2020/6/13
     */
    StockCompanyDetalVo getStockCompanyInfo(StockCompanyDto stockCompanyDto);

}
