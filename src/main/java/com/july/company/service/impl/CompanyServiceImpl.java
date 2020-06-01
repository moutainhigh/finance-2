package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.entity.Company;
import com.july.company.mapper.CompanyMapper;
import com.july.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.company.CompanyVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 注册的公司信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-20
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;
    /**
     * 获取公司入驻数量
     * @param
     * @return java.lang.Integer
     * @author zengxueqi
     * @since 2020/5/21
     */
    @Override
    public Integer getCompanyCount() {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", SystemConstant.SYS_FALSE);
        List<Company> companies = this.list(queryWrapper);
        return CollectionUtils.isEmpty(companies) ? 0 : companies.size();
    }

    /**
     * 获取入驻公司列表信息
     * @param
     * @author xia.junwei
     * @since 2020/5/28
     */
    @Override
    public IPage<CompanyVo> getCompanyList(Page<CompanyVo> page, Company company) {
        IPage<CompanyVo> companyVoIPage = companyMapper.findCompanyList(page, company);
        return companyVoIPage;
    }

}
