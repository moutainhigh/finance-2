package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.apply.*;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceApplyService;
import com.july.company.vo.apply.ProductDetalVo;
import com.july.company.vo.apply.SelectProductVo;
import com.july.company.vo.apply.StockCompanyDetalVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 企业申请产品信息 前端控制器
 * @author zengxueqi
 * @since 2020-06-10
 */
@RestController
@RequestMapping("/financeApply")
public class FinanceApplyController {

    @Resource
    private FinanceApplyService financeApplyService;

    /**
     * 保存企业申请的产品信息
     * @param productApplyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @PostMapping("/saveProductApply")
    public ResultT<String> saveProductApply(@RequestBody ProductApplyDto productApplyDto) {
        financeApplyService.saveProductApply(productApplyDto);
        return ResultT.ok("申请成功！");
    }

    /**
     * 保存企业提交的产品信息
     * @param productCommitDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @PostMapping("/commitProductApply")
    public ResultT<String> commitProductApply(@RequestBody ProductCommitDto productCommitDto) {
        financeApplyService.commitProductApply(productCommitDto);
        return ResultT.ok("提交成功！");
    }

    /**
     * 获取产品申请信息（后台）
     * @param pageParamVo
     * @return com.july.company.response.ResultT<com.july.company.response.ResultT.Page < com.july.company.vo.apply.SelectProductVo>>
     * @author zengxueqi
     * @since 2020/6/12
     */
    @PostMapping("/getFinanceApply")
    public ResultT<ResultT.Page<SelectProductVo>> getFinanceApply(@RequestBody PageParamVo<SelectProductDto> pageParamVo) {
        PageVo<SelectProductDto> pager = pageParamVo.getPager();
        IPage<SelectProductVo> recordVos = financeApplyService.getFinanceApply(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 申请产品操作
     * @param productOperateDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/12
     */
    @PostMapping("/applyProductOperate")
    public ResultT<String> applyProductOperate(@RequestBody ProductOperateDto productOperateDto) {
        financeApplyService.applyProductOperate(productOperateDto);
        return ResultT.ok("操作成功！");
    }

    /**
     * 获取申请的企业信息(后台)
     * @param stockCompanyDto
     * @return com.july.company.response.ResultT<com.july.company.vo.apply.StockCompanyDetalVo>
     * @author zengxueqi
     * @since 2020/6/13
     */
    @PostMapping("/getStockCompanyInfo")
    public ResultT<StockCompanyDetalVo> getStockCompanyInfo(@RequestBody StockCompanyDto stockCompanyDto) {
        return ResultT.ok(financeApplyService.getStockCompanyInfo(stockCompanyDto));
    }

}
