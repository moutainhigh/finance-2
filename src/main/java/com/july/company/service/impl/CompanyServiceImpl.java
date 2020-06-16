package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.company.*;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.Company;

import com.july.company.entity.FinanceBondMatch;
import com.july.company.entity.FinanceStockMatch;
import com.july.company.entity.UserInfo;
import com.july.company.exception.BnException;
import com.july.company.mapper.CompanyMapper;
import com.july.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.FinanceBondMatchService;
import com.july.company.service.FinanceStockMatchService;
import com.july.company.service.UserInfoService;
import com.july.company.utils.DateUtils;
import com.july.company.utils.FileUtils;
import com.july.company.utils.UserUtils;
import com.july.company.vo.company.CompanyMatchVo;
import com.july.company.vo.company.CompanyVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FinanceStockMatchService financeStockMatchService;
    @Resource
    private FinanceBondMatchService financeBondMatchService;

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
                companyVo.setRegisterAddress(DictInit.getCodeValue(SystemConstant.REGION, companyVo.getRegisterAddress()));
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
     * 判断企业是否一键匹配过信息
     * @param companyMatchDto
     * @return com.july.company.vo.company.CompanyMatchVo
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public CompanyMatchVo getCompanyBoolMatch(CompanyMatchDto companyMatchDto) {
        UserInfo userInfo = userInfoService.getById(companyMatchDto.getUserId());
        BnException.of(userInfo == null, "无法获取到企业信息！");

        CompanyMatchVo companyMatchVo = new CompanyMatchVo();

        if (SystemConstant.SYS_FALSE.equals(companyMatchDto.getProductType())) {
            List<FinanceStockMatch> financeStockMatches = financeStockMatchService.getFinanceStockMatch(userInfo.getCompanyId());
            companyMatchVo.setBoolMatch(CollectionUtils.isEmpty(financeStockMatches) ? 0 : 1);
        } else {
            List<FinanceBondMatch> financeBondMatches = financeBondMatchService.getFinanceBondMatch(userInfo.getCompanyId());
            companyMatchVo.setBoolMatch(CollectionUtils.isEmpty(financeBondMatches) ? 0 : 1);
        }
        return companyMatchVo;
    }

    /**
     * 通过用户ID获取某个企业的信息(admin)
     * @param companyByUserDto
     * @return com.july.company.response.ResultT<com.july.company.vo.company.CompanyVo>
     * @author xiajunwei
     * @since 2020/6/15
     */
    @Override
    public CompanyVo getCompanyInfoById(CompanyByUserDto companyByUserDto) {
        UserInfo userInfo = userInfoService.getById(companyByUserDto.getUserId());
        BnException.of(userInfo == null, "无法获取到用户信息！");
        BnException.of(userInfo.getCompanyId() == null, "无法获取到企业信息！");

        Company company = this.getById(userInfo.getCompanyId());

        BnException.of(company == null, "没有找到该企业信息！");

        return CompanyVo.builder()
                .creditCode(company.getCreditCode())
                .companyName(company.getCompanyName())
                .id(company.getId())
                .contact(company.getContact())
                .tel(company.getTel())
                .introduce(company.getIntroduce())
                .build();
    }

    /**
     * 上传企业Logo信息
     * @param file
     * @return void
     * @author zengxueqi
     * @since 2020/6/15
     */
    @Override
    public void uploadCompanyLogo(MultipartFile file) {
        String logoPath = uploadImages(file);
        UserInfoDto userInfoDto = UserUtils.getUser();
        BnException.of(userInfoDto == null, "企业信息获取失败！");
        UserInfo userInfo = userInfoService.getById(userInfoDto.getId());

        Company company = this.getById(userInfo.getCompanyId());
        company.setCompanyName(logoPath);
    }

    /**
     * 上传企业Logo信息
     * @param file
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/6/15
     */
    public String uploadImages(MultipartFile file) {
        //将图片上传到服务器
        BnException.of(file.isEmpty(), "企业Logo不能为空！");
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //图片名称为uuid+图片后缀防止冲突
        String fileName = UUID.randomUUID().toString() + "." + suffix;
        String os = System.getProperty("os.name");
        //文件保存路径
        String filePath = "";
        if (os.toLowerCase().startsWith("win")) {
            //windows下的路径
            filePath = "D:/finance/upload/";
        } else {
            //linux下的路径
            filePath = "/Users/zengxueqi/Desktop/";//"/data/web/finance/upload/";
        }
        try {
            //写入图片
            Boolean writePictureflag = FileUtils.uploadFile(file.getBytes(), filePath, fileName);
            BnException.of(!writePictureflag, "企业Logo上传失败！");
            //上传成功后，将可以访问的完整路径返回
            return filePath + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            //上传图片失败
            throw new BnException("企业Logo上传失败！");
        }
    }

}
