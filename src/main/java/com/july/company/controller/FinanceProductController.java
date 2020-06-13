package com.july.company.controller;

import cn.gjing.tools.excel.ExcelFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.*;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.FinanceProductService;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import com.july.company.vo.finance.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 获取股权融资信息分页（后台）
     * @param pageParamVo
     * @author xia.junwei
     * @since 2020/6/8
     */
    @PostMapping("/getStockList")
    public ResultT<ResultT.Page<StockListVo>> getStockList(@RequestBody PageParamVo<ListStockConditionDto> pageParamVo) {
        PageVo<ListStockConditionDto> pager = pageParamVo.getPager();
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
     * 债权融资信息根据产品ID查询（后台）
     * @param selectProductDto
     * @return com.july.company.response.ResultT<com.july.company.vo.finance.BondEditDetailVo>
     * @author zengxueqi
     * @since 2020/6/11
     */
    @PostMapping("/getBondByProductId")
    public ResultT<BondEditDetailVo> getBondByProductId(@RequestBody SelectProductDto selectProductDto) {
        return ResultT.ok(financeProductService.getBondByProductId(selectProductDto));
    }

    /**
     * 股权融资信息根据产品ID查询(后台)
     * @param selectProductDto
     * @return com.july.company.response.ResultT<com.july.company.vo.finance.StockEditDetailVo>
     * @author zengxueqi
     * @since 2020/6/11
     */
    @PostMapping("/getStockByProductId")
    public ResultT<StockEditDetailVo> getStockByProductId(@RequestBody SelectProductDto selectProductDto) {
        return ResultT.ok(financeProductService.getStockByProductId(selectProductDto));
    }
    /**
     * 修改保存或者添加债权信息(后台)
     * @param bondSaveDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    @PostMapping("/updateFinanceBond")
    public ResultT<String> updateFinanceBond(@RequestBody BondSaveDetailDto bondSaveDetailDto) {
        financeProductService.updateOrAddFinanceBond(bondSaveDetailDto);
        return ResultT.ok("操作成功");
    }

    /**
     * 删除债权信息(后台)
     * @param bondDeleteDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    @PostMapping("/deleteBondList")
    public ResultT<String> deleteBondList(@RequestBody BondDeleteDetailDto bondDeleteDetailDto) {
        financeProductService.deleteBondList(bondDeleteDetailDto);
        return ResultT.ok("删除成功");
    }

    /**
     * 删除股权信息(后台)
     * @param bondDeleteDetailDto
     * @author xiajunwei
     * @since 2020/6/11
     */
    @PostMapping("/deleteStockList")
    public ResultT<String> deleteStockList(@RequestBody BondDeleteDetailDto bondDeleteDetailDto) {
        financeProductService.deleteStockList(bondDeleteDetailDto);
        return ResultT.ok("删除成功");
    }

    /**
     * 保存股权融资产品信息
     * @param stockEditDetailDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/12
     */
    @PostMapping("/saveStockProduct")
    public ResultT<String> saveStockProduct(@RequestBody StockEditDetailDto stockEditDetailDto) {
        financeProductService.saveStockProduct(stockEditDetailDto);
        return ResultT.ok("操作成功！");
    }



    /**
     * 股权产品信息导出
     * @param pageParamVo
     * @return void
     * @author zengxueqi
     * @since 2020/6/11
     */
    @PostMapping("/exportStockProduct")
    public void exportStockProduct(@RequestBody PageParamVo<ListStockConditionDto> pageParamVo) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        PageVo<ListStockConditionDto> pager = pageParamVo.getPager();
        pager.setPageSize(-1L);
        List<StockExcelListVo> eduTeacherLeaveRecordExcels = financeProductService.getStockExcelList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        ExcelFactory.createWriter("股权产品信息", StockExcelListVo.class, response).write(eduTeacherLeaveRecordExcels).flush();
    }

}
