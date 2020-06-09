package com.july.company.controller;


import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceBondMatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 债券融资一键匹配 前端控制器
 * @author zengxueqi
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/financeBondMatch")
public class FinanceBondMatchController {

    @Resource
    private FinanceBondMatchService financeBondMatchService;

    /**
     * 一键匹配债券产品信息
     * @param bondProductMatchDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @PostMapping("/getBondOneKeyMatching")
    public ResultT<String> getBondOneKeyMatching(@RequestBody BondProductMatchDto bondProductMatchDto) {
        return ResultT.ok(financeBondMatchService.getBondOneKeyMatching(bondProductMatchDto));
    }

}
