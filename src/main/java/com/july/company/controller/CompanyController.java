package com.july.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.company.*;

import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.CompanyService;
import com.july.company.vo.company.CompanyMatchVo;
import com.july.company.vo.company.CompanyVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
     * 获取公司列表信息(admin)
     * 获取公司列表信息分页
     * @param pageParamVo
     * @author xia.junwei
     * @since 2020/5/28
     */
    @PostMapping("/getCompanyList")
    public ResultT<ResultT.Page<CompanyVo>> getCompanyList(@RequestBody PageParamVo<CompanyDto> pageParamVo) {
        PageVo<CompanyDto> pager = pageParamVo.getPager();
        IPage<CompanyVo> recordVos = companyService.getCompanyList(new Page<>(pager.getCurrent(), pager.getSize()), pageParamVo.getContent());
        return ResultT.ok(recordVos.getRecords(), new PageVo<>(recordVos.getCurrent(), recordVos.getSize(), recordVos.getTotal()));
    }

    /**
     * 获取某个企业的信息(admin)
     * @param selectCompanyDto
     * @return com.july.company.response.ResultT<com.july.company.vo.company.CompanyVo>
     * @author zengxueqi
     * @since 2020/6/7
     */
    @PostMapping("/getCompanyInfo")
    public ResultT<CompanyVo> getCompanyInfo(@RequestBody SelectCompanyDto selectCompanyDto) {
        return ResultT.ok(companyService.getCompanyInfo(selectCompanyDto));
    }

    /**
     * 通过用户ID获取企业信息(admin)
     * @param companyByUserDto
     * @return com.july.company.response.ResultT<com.july.company.vo.company.CompanyVo>
     * @author xiajunwei
     * @since 2020/6/15
     */
    @PostMapping("/getCompanyInfoById")
    public ResultT<CompanyVo> getCompanyInfoById(@RequestBody CompanyByUserDto companyByUserDto, HttpServletResponse response) {
        return ResultT.ok(companyService.getCompanyInfoById(companyByUserDto, response));
    }

    /**
     * 修改企业信息(admin)
     * @param updateCompanyDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/7
     */
    @PostMapping("/updateCompany")
    public ResultT<String> updateCompany(@RequestBody UpdateCompanyDto updateCompanyDto) {
        companyService.updateCompany(updateCompanyDto);
        return ResultT.ok("修改成功！");
    }

    /**
     * 删除企业信息(admin)
     * @param deleteCompanyDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/7
     */
    @PostMapping("/deleteCompany")
    public ResultT<String> deleteCompany(@RequestBody DeleteCompanyDto deleteCompanyDto) {
        companyService.deleteCompany(deleteCompanyDto);
        return ResultT.ok("修改成功！");
    }

    /**
     * 判断企业是否一键匹配过信息
     * @param companyMatchDto
     * @return com.july.company.response.ResultT<com.july.company.vo.company.CompanyMatchVo>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @PostMapping("/getCompanyBoolMatch")
    public ResultT<CompanyMatchVo> getCompanyBoolMatch(@RequestBody CompanyMatchDto companyMatchDto) {
        return ResultT.ok(companyService.getCompanyBoolMatch(companyMatchDto));
    }

    /**
     * 上传企业Logo信息
     * @param file
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/6/15
     */
    @PostMapping("/uploadCompanyLogo")
    public ResultT<String> uploadCompanyLogo(@RequestBody MultipartFile file) {
        companyService.uploadCompanyLogo(file);
        return ResultT.ok("上传成功！");
    }
    
}
