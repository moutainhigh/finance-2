package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.*;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceProductService;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 删除产品信息
     * @param oneProductDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/4
     */
    @PostMapping("/deleteProduct")
    public ResultT<String> deleteProduct(@RequestBody OneProductDto oneProductDto) {
        financeProductService.deleteProduct(oneProductDto);
        return ResultT.ok("删除成功！");
    }

    /**
     * 修改债券融资信息
     * @param financeBondProductDetailDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/4
     */
    @PostMapping("/updateBondProduct")
    public ResultT<String> updateBondProduct(@RequestBody FinanceBondProductDetailDto financeBondProductDetailDto) {
        return ResultT.ok("保存成功！");
    }

    /**
     * 修改金股权融资信息
     * @param financeStockProductDetailDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/4
     */
    @PostMapping("/updateStockProduct")
    public ResultT<String> updateStockProduct(@RequestBody FinanceStockProductDetailDto financeStockProductDetailDto) {
        return ResultT.ok("保存成功！");
    }

}
