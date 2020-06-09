package com.july.company.controller;

import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceStockDetailService;
import com.july.company.vo.finance.FinanceStockProductDetailVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 融资公司明细信息 前端控制器
 * @author zengxueqi
 * @since 2020-05-26
 */
@RestController
@RequestMapping("/financeStockDetail")
public class FinanceStockDetailController {

    @Resource
    private FinanceStockDetailService financeStockDetailService;

    /**
     * 获取股权融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.response.ResultT<com.july.company.vo.finance.StockFinanceProductDetailVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getFinanceStockProductDetail")
    public ResultT<FinanceStockProductDetailVo> getFinanceStockProductDetail(@RequestBody FinanceProductDetailDto financeProductDetailDto) {
        return ResultT.ok(financeStockDetailService.getFinanceStockProductDetail(financeProductDetailDto));
    }

    /**
     * 一键匹配股权产品信息
     * @param stockProductMatchDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getStockOneKeyMatching")
    public ResultT<String> getStockOneKeyMatching(@RequestBody StockProductMatchDto stockProductMatchDto) {
        return ResultT.ok();//ResultT.ok(financeStockDetailService.getStockOneKeyMatching(stockProductMatchDto));
    }

}
