package com.july.company.controller;


import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceBondDetailService;
import com.july.company.vo.finance.FinanceBondProductDetailVo;
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
@RequestMapping("/financeBondDetail")
public class FinanceBondDetailController {

    @Resource
    private FinanceBondDetailService financeBondDetailService;

    /**
     * 获取债券融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.response.ResultT<com.july.company.vo.finance.BondFinanceProductDetailVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getFinanceBondProductDetail")
    public ResultT<FinanceBondProductDetailVo> getFinanceBondProductDetail(@RequestBody FinanceProductDetailDto financeProductDetailDto) {
        return ResultT.ok(financeBondDetailService.getFinanceBondProductDetail(financeProductDetailDto));
    }

    /**
     * 一键匹配债券产品信息
     * @param bondProductMatchDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getBondOneKeyMatching")
    public ResultT<String> getBondOneKeyMatching(@RequestBody BondProductMatchDto bondProductMatchDto) {
        return ResultT.ok(financeBondDetailService.getBondOneKeyMatching(bondProductMatchDto));
    }

}
