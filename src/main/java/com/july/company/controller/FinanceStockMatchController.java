package com.july.company.controller;


import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceStockMatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 股权融资一键匹配 前端控制器
 * @author zengxueqi
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/financeStockMatch")
public class FinanceStockMatchController {

    @Resource
    private FinanceStockMatchService financeStockMatchService;

    /**
     * 一键匹配股权产品信息
     * @param stockProductMatchDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/26
     * @deprecated
     */
    @PostMapping("/getStockOneKeyMatching")
    public ResultT<String> getStockOneKeyMatching(@RequestBody StockProductMatchDto stockProductMatchDto) {
        return ResultT.ok(financeStockMatchService.getStockOneKeyMatching(stockProductMatchDto));
    }

}
