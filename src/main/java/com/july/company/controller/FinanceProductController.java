package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.*;
import com.july.company.entity.FinanceProduct;
import com.july.company.entity.FinanceStockDetail;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceProductService;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import com.july.company.service.impl.FinanceStockDetailServiceImpl;
import com.july.company.vo.finance.*;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private FinanceStockDetailServiceImpl financeStockDetailService;

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

    /**
     * 获取股权融资信息分页（后台）
     * @param pageParamVo
     * @author xia.junwei
     * @since 2020/6/8
     */
    @PostMapping("/getStockList")
    public ResultT<ResultT.Page<StockListVo>> getStockList(@RequestBody PageParamVo<ListConditionDto> pageParamVo) {
        PageVo<ListConditionDto> pager = pageParamVo.getPager();
        IPage<StockListVo> recordVos = financeProductService.getStockList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 获取债权融资信息分页（后台）
     * @param pageParamVo
     * @author xia.junwei
     * @since 2020/6/8
     */
    @PostMapping("/getBondList")
    public ResultT<ResultT.Page<BondListVo>> getBondList(@RequestBody PageParamVo<ListConditionDto> pageParamVo) {
        PageVo<ListConditionDto> pager = pageParamVo.getPager();
        IPage<BondListVo> recordVos = financeProductService.getBondList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 股权融资信息根据产品ID查询（后台）
     * @author xia.junwei
     * @since 2020/6/8
     */
    @PostMapping("/getStockByproductId")
    public ResultT<StockListVo> getStockByproductId(@RequestBody Long id) {
        FinanceProduct financeProduct = financeProductService.getFinanceProductById(id);
        FinanceStockDetail financeProductDetail = financeStockDetailService.getFinanceProductDetail(id);
        StockListVo stockListVo = new StockListVo();
        BeanUtils.copyProperties(financeProductDetail, stockListVo);
        BeanUtils.copyProperties(financeProduct, stockListVo);
        return ResultT.ok(stockListVo);
    }

}
