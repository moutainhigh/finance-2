package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.company.CompanyIdsDto;
import com.july.company.dto.finance.OneProductDto;
import com.july.company.entity.Company;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.CompanyService;
import com.july.company.utils.LayerData;
import com.july.company.vo.company.CompanyVo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * 注册的公司信息 前端控制器
 * @author zengxueqi
 * @since 2020-05-20
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    /**
     * 获取公司列表信息分页
     * @param pageParamVo
     * @author xia.junwei
     * @since 2020/5/28
     */
    @PostMapping("/getCompanyList")
    public ResultT<ResultT.Page<CompanyVo>> getCompanyList(@RequestBody PageParamVo<Company> pageParamVo) {
        PageVo<Company> pager = pageParamVo.getPager();
        IPage<CompanyVo> recordVos = companyService.getCompanyList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }
    /**
     * 获取公司列表信息分页
     * @author xia.junwei
     * @since 2020/5/28
     */
    @PostMapping("/list")
    public LayerData<CompanyVo> list_bak(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Long limit,
                                         ServletRequest request) {
        PageParamVo<Company> pageParamVo = new PageParamVo();
        PageVo<Company> companyPageVo = new PageVo<>();
        companyPageVo.setCurrent(page);
        companyPageVo.setSize(limit);
        pageParamVo.setPager(companyPageVo);
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        //System.out.println("====>" + map.get("type"));
//        FinanceStockProductDto financeStockProductDto = new FinanceStockProductDto();
//        pageParamVo.setContent(financeStockProductDto);
        LayerData<CompanyVo> layerData = new LayerData<>();
        PageVo<Company> pager = pageParamVo.getPager();
        IPage<CompanyVo> recordVos = companyService.getCompanyList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        layerData.setCount(recordVos.getTotal());
        layerData.setData(recordVos.getRecords());
        return layerData;
    }
    /**
     * 更新企业信息
     * @author xia.junwei
     * @since 2020/6/5
     */
    @PutMapping ("/updateCompany")
    public LayerData<CompanyVo> updateCompany(@RequestBody Company company){
        LayerData<CompanyVo> data = new LayerData<CompanyVo>();
        boolean result = companyService.updateCompany(company);
        if (!result){
            data.setCode(1);
        }
        return data;
    }

    /**
     * 删除企业信息
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/4
     */
    @DeleteMapping ("/deleteCompany")
    public ResultT<String> deleteCompany(@RequestBody CompanyIdsDto companyIdsDto) {
        companyService.deleteCompany(companyIdsDto);
        return ResultT.ok("删除成功！");
    }
}
