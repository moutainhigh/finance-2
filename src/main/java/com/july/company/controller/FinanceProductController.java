package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.FinanceProductDto;
import com.july.company.dto.finance.ProductMatchDto;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceProductService;
import com.july.company.vo.finance.FinanceProductDetailVo;
import com.july.company.vo.finance.FinanceProductVo;
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
     * 获取产品列表信息
     * @param pageParamVo
     * @return com.july.company.response.ResultT<com.july.company.response.ResultT.Page < com.july.company.vo.finance.FinanceProductVo>>
     * @author zengxueqi
     * @since 2020/5/20
     */
    @PostMapping("/getFinanceProduct")
    public ResultT<ResultT.Page<FinanceProductVo>> getFinanceProduct(@RequestBody PageParamVo<FinanceProductDto> pageParamVo) {
        PageVo<FinanceProductDto> pager = pageParamVo.getPager();
        IPage<FinanceProductVo> recordVos = financeProductService.getFinanceProduct(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 获取产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.response.ResultT<com.july.company.vo.finance.FinanceProductDetailVo>
     * @author zengxueqi
     * @since 2020/5/20
     */
    @PostMapping("/getFinanceProductDetail")
    public ResultT<FinanceProductDetailVo> getFinanceProductDetail(@RequestBody FinanceProductDetailDto financeProductDetailDto) {
        return ResultT.ok(financeProductService.getFinanceProductDetail(financeProductDetailDto));
    }

    /**
     * 一键匹配产品信息
     * @param productMatchDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/25
     */
    @PostMapping("/getOneKeyMatching")
    public ResultT<String> getOneKeyMatching(@RequestBody ProductMatchDto productMatchDto) {
        return ResultT.ok(financeProductService.getOneKeyMatching(productMatchDto));
    }

}
