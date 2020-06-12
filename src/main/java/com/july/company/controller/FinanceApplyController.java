package com.july.company.controller;

import com.july.company.dto.apply.ProductApplyDto;
import com.july.company.dto.apply.ProductCommitDto;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceApplyService;
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

}
