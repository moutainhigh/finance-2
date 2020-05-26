package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceBondProductDto;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.dto.finance.ProductMatchDto;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceProductService;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 产品信息 前端控制器
 * @author zengxueqi
 * @since 2020-05-20
 */
@RestController
@RequestMapping("/financeProduct")
public class FinanceProductController {

    @Resource
    private FinanceProductService financeProductService;

    /**
     * 获取股权产品列表信息
     * @param pageParamVo
     * @return com.july.company.response.ResultT<com.july.company.response.ResultT.Page < com.july.company.vo.finance.FinanceStockProductVo>>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getFinanceStockProduct")
    public ResultT<ResultT.Page<FinanceStockProductVo>> getFinanceStockProduct(@RequestBody PageParamVo<FinanceStockProductDto> pageParamVo) {
        PageVo<FinanceStockProductDto> pager = pageParamVo.getPager();
        IPage<FinanceStockProductVo> recordVos = financeProductService.getFinanceStockProduct(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 获取债券产品列表信息
     * @param pageParamVo
     * @return com.july.company.response.ResultT<com.july.company.response.ResultT.Page < com.july.company.vo.finance.FinanceBondProductVo>>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getFinanceBondProduct")
    public ResultT<ResultT.Page<FinanceBondProductVo>> getFinanceBondProduct(@RequestBody PageParamVo<FinanceBondProductDto> pageParamVo) {
        PageVo<FinanceBondProductDto> pager = pageParamVo.getPager();
        IPage<FinanceBondProductVo> recordVos = financeProductService.getFinanceBondProduct(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

}
