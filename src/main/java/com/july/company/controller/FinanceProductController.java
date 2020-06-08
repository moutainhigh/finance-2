package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.*;
import com.july.company.entity.Company;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceProductService;
import com.july.company.utils.LayerData;
import com.july.company.vo.company.CompanyVo;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductDetailVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Map;

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

    @PostMapping("/list")
    public LayerData<FinanceStockProductVo> list_bak(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "limit", defaultValue = "10") Long limit,
                                                     ServletRequest request) {
        PageParamVo<FinanceStockProductDto> pageParamVo = new PageParamVo();
        PageVo<FinanceStockProductDto> productDtoPageVo = new PageVo<>();
        productDtoPageVo.setCurrent(page);
        productDtoPageVo.setSize(limit);
        pageParamVo.setPager(productDtoPageVo);
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        //System.out.println("====>" + map.get("type"));
        FinanceStockProductDto financeStockProductDto = new FinanceStockProductDto();
        pageParamVo.setContent(financeStockProductDto);
        LayerData<FinanceStockProductVo> layerData = new LayerData<>();
        PageVo<FinanceStockProductDto> pager = pageParamVo.getPager();
        IPage<FinanceStockProductVo> recordVos = financeProductService.getFinanceStockProduct(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        layerData.setCount(recordVos.getTotal());
        layerData.setData(recordVos.getRecords());
        return layerData;
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
        System.out.println("保存信息===>" + financeBondProductDetailDto.getTel());
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
        System.out.println("保存信息===>" + financeStockProductDetailDto.getTitle());
        return ResultT.ok("保存成功！");
    }
    /**
     * 获取股权融资信息分页（后台）
     * @param pageParamVo
     * @author xia.junwei
     * @since 2020/6/8
     */
    @PostMapping("/getStockList")
    public ResultT<ResultT.Page<FinanceStockProductDetailVo>> getStockList(@RequestBody PageParamVo<FinanceStockProductDetailDto> pageParamVo) {
        PageVo<FinanceStockProductDetailDto> pager = pageParamVo.getPager();
        IPage<FinanceStockProductDetailVo> recordVos = financeProductService.getStockList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

}
