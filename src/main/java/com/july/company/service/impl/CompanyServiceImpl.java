package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.company.CompanyDto;
import com.july.company.dto.company.DeleteCompanyDto;
import com.july.company.dto.company.SelectCompanyDto;
import com.july.company.dto.company.UpdateCompanyDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.Company;

import com.july.company.exception.BnException;
import com.july.company.mapper.CompanyMapper;
import com.july.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.DateUtils;
import com.july.company.vo.company.CompanyVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
    public IPage<CompanyVo> getCompanyList(Page<CompanyVo> page, CompanyDto companyDto) {
        IPage<CompanyVo> companyVoIPage = companyMapper.findCompanyList(page, companyDto);
        List<CompanyVo> companyVoList = companyVoIPage.getRecords();

        List<CompanyVo> companyVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(companyVoList)) {
            companyVos = companyVoList.stream().map(companyVo -> {
                companyVo.setRegisterTime(DateUtils.timeStamp2Date(Long.parseLong(companyVo.getRegisterTime())));
                return companyVo;
            }).collect(Collectors.toList());
        }
        companyVoIPage.setRecords(companyVos);
        return companyVoIPage;
    }

    /**
     * 修改企业信息
     * @param updateCompanyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/7
     */
    @Override
    public void updateCompany(UpdateCompanyDto updateCompanyDto) {
        Company company = this.getById(updateCompanyDto.getCompanyId());
        BnException.of(company == null, "没有找到该企业信息！");

        company.setCreditCode(updateCompanyDto.getCreditCode());
        company.setCompanyName(updateCompanyDto.getCompanyName());
        //如果status不等于空，并且status=on，表示企业开启0，否则企业禁用1
        company.setStatus(!StringUtils.isEmpty(updateCompanyDto.getStatus())
                && SystemConstant.SWITCH_ON.equals(updateCompanyDto.getStatus()) ? SystemConstant.SYS_FALSE : SystemConstant.SYS_TRUE);
        this.updateById(company);
    }

    /**
     * 获取某个企业的信息
     * @param selectCompanyDto
     * @return com.july.company.vo.company.CompanyVo
     * @author zengxueqi
     * @since 2020/6/7
     */
    @Override
    public CompanyVo getCompanyInfo(SelectCompanyDto selectCompanyDto) {
        Company company = this.getById(selectCompanyDto.getCompanyId());
        BnException.of(company == null, "没有找到该企业信息！");

        return CompanyVo.builder()
                .creditCode(company.getCreditCode())
                .companyName(company.getCompanyName())
                .status(company.getStatus())
                .id(company.getId())
                .build();
    }

    /**
     * 删除企业信息
     * @param deleteCompanyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/7
     */
    @Override
    public void deleteCompany(DeleteCompanyDto deleteCompanyDto) {
        BnException.of(StringUtils.isEmpty(deleteCompanyDto.getCompanyIds()), "删除数据时，数据id不能为空！");
        List<String> companyIdList = Arrays.asList(deleteCompanyDto.getCompanyIds().split(","));

        companyIdList.stream().forEach(s -> {
            Company company = this.getById(Long.parseLong(s));
            BnException.of(company == null, "没有找到该企业信息！");
            company.setDeleted(SystemConstant.SYS_TRUE);
            this.updateById(company);
        });
    }
    /**
     * 通过用户查询公司信息
     * @author xiajunwei
     * @since 2020/6/9
     */
    public Company getCompanyByUser(UserInfoDto userInfoDto){
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", SystemConstant.SYS_FALSE);
        queryWrapper.eq("companyName", userInfoDto.getCompanyName());
        Company company = this.getOne(queryWrapper);
        return company;
    }

    @Override
    public void updateCompanyForSaveOneMatch(Company company) {
        this.updateById(company);
    }
}
