package com.july.company.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.dto.login.UserRegisterDto;
import com.july.company.entity.Company;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;
import com.july.company.response.ResultT;
import com.july.company.service.CompanyService;
import com.july.company.utils.MailUtil;
import com.july.company.vo.company.CompanyVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private MailUtil mailUtil;
    /**
     * 获取公司列表信息
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
    @PostMapping("/sendEmail")
    public ResultT<String> register(){
        try {
            mailUtil.sendToReceiveMail("347164649@qq.com" ,"测试","这是测试内容", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultT.ok("发送邮件成功");
    }
}
