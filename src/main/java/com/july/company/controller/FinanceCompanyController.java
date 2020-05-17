package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.stock.StockCompanyDetailDto;
import com.july.company.dto.stock.StockCompanyDto;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceCompanyService;
import com.july.company.vo.stock.StockCompanyDetailVo;
import com.july.company.vo.stock.StockCompanyVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 融资公司信息 前端控制器
 * @author zengxueqi
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/financeCompany")
public class FinanceCompanyController {

    @Resource
    private FinanceCompanyService financeCompanyService;

    /**
     * 获取股权融资公司列表信息
     * @param pageParamVo
     * @return com.july.company.response.ResultT<com.july.company.response.ResultT.Page < com.july.company.vo.stock.StockCompanyVo>>
     * @author zengxueqi
     * @since 2020/5/17
     */
    @PostMapping("/getFinanceCompany")
    public ResultT<ResultT.Page<StockCompanyVo>> getFinanceCompany(@RequestBody PageParamVo<StockCompanyDto> pageParamVo) {
        PageVo<StockCompanyDto> pager = pageParamVo.getPager();
        IPage<StockCompanyVo> recordVos = financeCompanyService.getFinanceCompany(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 获取股权融资公司明细信息
     * @param stockCompanyDetailDto
     * @return com.july.company.response.ResultT<com.july.company.dto.stock.StockCompanyDetailDto>
     * @author zengxueqi
     * @since 2020/5/17
     */
    @PostMapping("/getFinanceCompanyDetail")
    public ResultT<StockCompanyDetailVo> getFinanceCompanyDetail(@RequestBody StockCompanyDetailDto stockCompanyDetailDto) {
        return ResultT.ok(financeCompanyService.getFinanceCompanyDetail(stockCompanyDetailDto));
    }

}
