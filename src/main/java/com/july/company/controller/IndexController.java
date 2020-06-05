package com.july.company.controller;

import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.service.CompanyService;
import com.july.company.service.FinanceStockDetailService;
import com.july.company.vo.company.CompanyVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 页面控制器
 * @author zengxueqi
 * @program finance-front
 * @since 2020-06-03 14:57
 **/
@Controller
public class IndexController {

    @Resource
    private FinanceStockDetailService financeStockDetailService;
    @Resource
    private CompanyService companyService;

    @GetMapping("loginh")
    public String login() {
        return "login";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 用于地址跳转
     * @param menuUrl
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/6/4
     */
    @GetMapping("/admin/system/rescource/list")
    public String menu(String menuUrl) {
        return "admin/system/rescource/list";
    }
    @GetMapping("/admin/company/list")
    public String menu1(String menuUrl) {
        return "/admin/company/list";
    }

    /**
     * 查询产品信息
     * @param id
     * @param model
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/6/5
     */
    @GetMapping("/product/queryProduct")
    public String edit(Long id, Model model) {
        FinanceProductDetailDto financeProductDetailDto = new FinanceProductDetailDto();
        financeProductDetailDto.setCompanyId(id);
        model.addAttribute("product", financeStockDetailService.getFinanceStockProductDetail(financeProductDetailDto));
        return "admin/system/rescource/edit";
    }
    /**
     * 通过ID查询企业信息
     * @author xia.junwei
     * @since 2020/5/28
     */
    @RequestMapping("/company/queryCompany")
    public String queryCompany(@RequestParam("id") Integer Id, Model model){
        CompanyVo companyVo = companyService.findCompanyById(Id);
        model.addAttribute("company", companyVo);
        return "/admin/company/edit";
    }

    @GetMapping("changePassword")
    public String changePassword() {
        return "admin/system/user/changePassword";
    }

}
