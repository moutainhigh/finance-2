package com.july.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.Node;
import com.july.company.dto.apply.*;
import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.entity.*;
import com.july.company.entity.enums.ApplyStatusEnum;
import com.july.company.entity.enums.FinanceTypeEnum;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceApplyMapper;
import com.july.company.mapper.FinanceBondMatchMapper;
import com.july.company.mapper.FinanceStockMatchMapper;
import com.july.company.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.DateUtils;
import com.july.company.utils.StringUtils;
import com.july.company.vo.apply.BondCompanyDetailVo;
import com.july.company.vo.apply.CompanyApplyProductVo;
import com.july.company.vo.apply.SelectProductVo;
import com.july.company.vo.apply.StockCompanyDetalVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业申请产品信息 服务实现类
 * @author zengxueqi
 * @since 2020-06-10
 */
@Service
public class FinanceApplyServiceImpl extends ServiceImpl<FinanceApplyMapper, FinanceApply> implements FinanceApplyService {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FinanceBondMatchService financeBondMatchService;
    @Resource
    private FinanceStockMatchService financeStockMatchService;
    @Resource
    private FinanceApplyMapper financeApplyMapper;
    @Resource
    private CompanyService companyService;
    @Resource
    private FinanceStockDetailService financeStockDetailService;
    @Resource
    private FinanceBondDetailService financeBondDetailService;
    @Resource
    private FinanceStockMatchMapper financeStockMatchMapper;
    @Resource
    private FinanceBondMatchMapper financeBondMatchMapper;

    /**
     * 保存企业申请的产品信息
     * @param productApplyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProductApply(ProductApplyDto productApplyDto) {
        //获取企业id
        UserInfo userInfo = userInfoService.getById(productApplyDto.getUserId());
        BnException.of(userInfo == null, "无法获取到企业信息！");

        Boolean boolApply = boolApply(userInfo.getCompanyId(), productApplyDto.getProductId());
        BnException.of(boolApply, "该产品已经申请过，请不要重复申请！");

        FinanceApply financeApply = FinanceApply.builder()
                .companyId(userInfo.getCompanyId())
                .productId(productApplyDto.getProductId())
                .status(ApplyStatusEnum.AUDIT.getValue())
                .build();
        this.save(financeApply);
    }

    /**
     * 判断这个产品企业是否已经申请过
     * @param companyId
     * @param productId
     * @return java.lang.Boolean
     * @author zengxueqi
     * @since 2020/6/10
     */
    public Boolean boolApply(Long companyId, Long productId) {
        QueryWrapper<FinanceApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("companyId", companyId)
                .eq("productId", productId);
        List<FinanceApply> financeApplies = this.list(queryWrapper);
        return !CollectionUtils.isEmpty(financeApplies) ? true : false;
    }

    /**
     * 保存企业提交的产品信息
     * @param productCommitDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public void commitProductApply(ProductCommitDto productCommitDto) {
        //保存一键匹配数据
        if (FinanceTypeEnum.STOCKRIGHT.getValue().equals(productCommitDto.getFinanceType())) {
            StockProductMatchDto stockProductMatchDto = productCommitDto.getStockProductMatchDto();
            stockProductMatchDto.setOperateMatchDto(productCommitDto.getOperateMatchDto());
            financeStockMatchService.saveStockOneKeyMatching(productCommitDto.getStockProductMatchDto());
        } else {
            BondProductMatchDto bondProductMatchDto = productCommitDto.getBondProductMatchDto();
            bondProductMatchDto.setOperateMatchDto(productCommitDto.getOperateMatchDto());
            financeBondMatchService.saveBondOneKeyMatching(productCommitDto.getBondProductMatchDto());
        }

        //保存产品申请信息
        ProductApplyDto productApplyDto = ProductApplyDto.builder()
                .productId(productCommitDto.getProductId())
                .userId(productCommitDto.getUserId())
                .build();
        saveProductApply(productApplyDto);
    }

    /**
     * 获取产品申请信息（后台）
     * @param page
     * @param selectProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.apply.SelectProductVo>
     * @author zengxueqi
     * @since 2020/6/12
     */
    @Override
    public IPage<SelectProductVo> getFinanceApply(Page<SelectProductVo> page, SelectProductDto selectProductDto) {
        IPage<SelectProductVo> selectProductVoIPage = financeApplyMapper.getFinanceApply(page, selectProductDto);
        List<SelectProductVo> selectProductVoList = selectProductVoIPage.getRecords();

        //数据类型转换
        List<SelectProductVo> selectProductVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(selectProductVoList)) {
            selectProductVos = selectProductVoList.stream().map(selectProductVo -> {
                selectProductVo.setFinanceTypeStr(FinanceTypeEnum.getDescByValue(selectProductVo.getFinanceType()));
                selectProductVo.setCreatedTimeStr(DateUtils.timeStamp2Date(selectProductVo.getCreatedTime()));
                return selectProductVo;
            }).collect(Collectors.toList());
        }
        selectProductVoIPage.setRecords(selectProductVos);
        return selectProductVoIPage;
    }

    /**
     * 申请产品操作
     * @param productOperateDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/12
     */
    @Override
    public void applyProductOperate(ProductOperateDto productOperateDto) {
        FinanceApply financeApply = this.getById(productOperateDto.getApplyId());
        BnException.of(financeApply == null, "没有找到该产品申请信息！");

        if (ApplyStatusEnum.PASS.getValue().equals(productOperateDto.getOperateType())) {
            financeApply.setStatus(ApplyStatusEnum.PASS.getValue());
        } else if (ApplyStatusEnum.REJECT.getValue().equals(productOperateDto.getOperateType())) {
            financeApply.setStatus(ApplyStatusEnum.REJECT.getValue());
            financeApply.setRemark(productOperateDto.getRemark());
        }
        this.updateById(financeApply);
    }

    /**
     * 获取申请的企业信息(后台)
     * @param stockCompanyDto
     * @return com.july.company.vo.apply.StockCompanyDetalVo
     * @author zengxueqi
     * @since 2020/6/13
     */
    @Override
    public StockCompanyDetalVo getStockCompanyInfo(StockCompanyDto stockCompanyDto) {
        //企业信息
        Company company = companyService.getById(stockCompanyDto.getCompanyId());
        BnException.of(company == null, "没有找到该企业信息！");
        //企业最新信息
        FinanceStockMatch financeStockMatch = financeStockMatchMapper.getNewestMathInfo(company.getId());
        if (financeStockMatch == null) {
            String workAddress = org.springframework.util.StringUtils.isEmpty(DictInit.getCodeValue(SystemConstant.REGION, company.getWorkAddress())) ? "" :
                    DictInit.getCodeValue(SystemConstant.REGION, company.getWorkAddress());
            workAddress = org.springframework.util.StringUtils.isEmpty(company.getDetailAddress()) ? workAddress : workAddress + company.getDetailAddress();
            return StockCompanyDetalVo.builder()
                    .companyName(company.getCompanyName())
                    .contact(company.getContact())
                    .tel(company.getTel())
                    .registerAddress(DictInit.getCodeValue(SystemConstant.REGION, company.getRegisterAddress()))
                    .workAddress(workAddress)
                    .introduce(company.getIntroduce())
                    .build();
        }
        //股权产品信息
        FinanceStockDetail financeStockDetail = financeStockDetailService.getById(financeStockMatch.getDetailId());

        String workAddress = org.springframework.util.StringUtils.isEmpty(DictInit.getCodeValue(SystemConstant.REGION, company.getWorkAddress())) ? "" :
                DictInit.getCodeValue(SystemConstant.REGION, company.getWorkAddress());
        workAddress = org.springframework.util.StringUtils.isEmpty(company.getDetailAddress()) ? workAddress : workAddress + company.getDetailAddress();

        StockCompanyDetalVo stockCompanyDetalVo = StockCompanyDetalVo.builder()
                .companyName(company.getCompanyName())
                .contact(company.getContact())
                .tel(company.getTel())
                .registerAddress(DictInit.getCodeValue(SystemConstant.REGION, company.getRegisterAddress()))
                .workAddress(workAddress)
                .introduce(company.getIntroduce())
                .financeState(getColunmNode(SystemConstant.RZJD, financeStockDetail.getFinanceState()))
                .financeQuota(DictInit.getCodeValue(SystemConstant.RZED, financeStockDetail.getFinanceQuota()))
                .industryDirect(getColunmNode(SystemConstant.HYFX, financeStockDetail.getIndustryDirect()))
                .shareholder(getColunmNode(SystemConstant.GDBJ, financeStockDetail.getShareholder()))
                .productState(getColunmNode(SystemConstant.CPJD, financeStockDetail.getProductState()))
                .business(DictInit.getCodeValue(SystemConstant.STOCKRIGHT_YYSR, financeStockDetail.getBusiness()))
                .businessAddRate(DictInit.getCodeValue(SystemConstant.YYSRZZL, financeStockDetail.getBusinessAddRate()))
                .productRate(DictInit.getCodeValue(SystemConstant.CPMLL, financeStockDetail.getProductRate()))
                .netInterestRate(DictInit.getCodeValue(SystemConstant.JLL, financeStockDetail.getNetInterestRate()))
                .oldFinanceQuota(DictInit.getCodeValue(SystemConstant.GWRZJE, financeStockDetail.getOldFinanceQuota()))
                .experience(DictInit.getCodeValue(SystemConstant.CYJL, financeStockDetail.getExperience()))
                .companyStatus(DictInit.getCodeValue(SystemConstant.QYZT, financeStockMatch.getCompanyStatus()))
                .staffCount(DictInit.getCodeValue(SystemConstant.YGRS, financeStockDetail.getStaffCount()))
                .marketCapacity(DictInit.getCodeValue(SystemConstant.SCRL, financeStockDetail.getMarketCapacity()))
                .marketAddRate(DictInit.getCodeValue(SystemConstant.SCRLZZL, financeStockDetail.getMarketAddRate()))
                .targetCustomer(getListColunmNode(SystemConstant.MBKH, financeStockDetail.getTargetCustomer()))
                .boolBuyBack(DictInit.getCodeValue(SystemConstant.SFHG, financeStockDetail.getBoolBuyBack()))
                .patentCount(DictInit.getCodeValue(SystemConstant.STOCKRIGHT_FMZLSL, financeStockDetail.getPatentCount()))
                .advantage(getListColunmNode(SystemConstant.GSJZYS, financeStockDetail.getAdvantage()))
                .capitals(DictInit.getCodeValue(SystemConstant.GDLJTRZJ, financeStockDetail.getCapitals()))
                .timeToMarket(DictInit.getCodeValue(SystemConstant.SSSJ, financeStockMatch.getTimeToMarket()))
                .evaluateName(getListColunmNode(SystemConstant.PDCH, financeStockDetail.getEvaluateName()))
                .build();
        return stockCompanyDetalVo;
    }

    /**
     * 获取申请的企业信息(债权后台)
     * @param stockCompanyDto
     * @return com.july.company.vo.apply.StockCompanyDetalVo
     * @author zengxueqi
     * @since 2020/6/13
     */
    @Override
    public BondCompanyDetailVo getBondCompanyInfo(StockCompanyDto stockCompanyDto) {
        //企业信息
        Company company = companyService.getById(stockCompanyDto.getCompanyId());
        BnException.of(company == null, "没有找到该企业信息！");
        //企业最新信息
        FinanceBondMatch financeBondMatch = financeBondMatchMapper.getNewestMathInfo(company.getId());
        if (financeBondMatch == null) {
            return BondCompanyDetailVo.builder()
                    .companyName(company.getCompanyName())
                    .contact(company.getContact())
                    .tel(company.getTel())
                    .registerAddress(DictInit.getCodeValue(SystemConstant.REGION, company.getRegisterAddress()))
                    .workAddress(company.getWorkAddress() + company.getDetailAddress())
                    .introduce(company.getIntroduce())
                    .build();
        }
        //股权产品信息
        FinanceBondDetail financeBondDetail = financeBondDetailService.getById(financeBondMatch.getDetailId());
        if (StringUtils.isEmpty(company.getDetailAddress())) {
            company.setDetailAddress("");
        }
        BondCompanyDetailVo bondCompanyDetailVo = BondCompanyDetailVo.builder()
                .companyName(company.getCompanyName())
                .contact(company.getContact())
                .tel(company.getTel())
                .registerAddress(DictInit.getCodeValue(SystemConstant.REGION, company.getRegisterAddress()))
                .workAddress(company.getWorkAddress() + company.getDetailAddress())
                .introduce(company.getIntroduce())
                .loanTerm(DictInit.getCodeValue(SystemConstant.DKQX, financeBondDetail.getLoanTerm()))
                .loanQuota(DictInit.getCodeValue(SystemConstant.DKED, financeBondDetail.getLoanQuota()))
                .industryDirect(getListColunmNode(SystemConstant.HYFX, financeBondDetail.getIndustryDirect()))
                .shareholder(getListColunmNode(SystemConstant.GDBJ, financeBondDetail.getShareholder()))
                .creditType(getListColunmNode(SystemConstant.ZXFS, financeBondDetail.getCreditType()))
                .houseMortgage(DictInit.getCodeValue(SystemConstant.FCDY, financeBondDetail.getHouseMortgage()))
                .business(DictInit.getCodeValue(SystemConstant.BOND_YYSR, financeBondDetail.getBusiness()))
                .jlr(DictInit.getCodeValue(SystemConstant.BOND_JLR, financeBondDetail.getJlr()))
                .cashFlow(DictInit.getCodeValue(SystemConstant.XJLJE, financeBondDetail.getCashFlow()))
                .goverOrderAmount(DictInit.getCodeValue(SystemConstant.ZFDDE, financeBondDetail.getGoverOrderAmount()))
                .nationOrderAmount(DictInit.getCodeValue(SystemConstant.GQDDE, financeBondDetail.getNationOrderAmount()))
                .assetAmount(financeBondDetail.getAssetAmount())
                .liabilitiesAmount(financeBondDetail.getLiabilitiesAmount())
                .owner(financeBondDetail.getOwner())
                .qualification(getListColunmNode(SystemConstant.QYZZ, financeBondDetail.getQualification()))
                .subsidy(DictInit.getCodeValue(SystemConstant.ZFBT, financeBondDetail.getSubsidy()))
                .lastSubsidy(DictInit.getCodeValue(SystemConstant.ZFBT, financeBondDetail.getLastSubsidy()))
                .boolIntroduce(DictInit.getCodeValue(SystemConstant.GQTZ, financeBondDetail.getBoolIntroduce()))
                .taxAmount(DictInit.getCodeValue(SystemConstant.NRED, financeBondDetail.getTaxAmount()))
                .patentCount(DictInit.getCodeValue(SystemConstant.BOND_FMZLS, financeBondDetail.getPatentCount()))
                .boolLoan(getListColunmNode(SystemConstant.QTDK, financeBondDetail.getBoolLoan()))
                .existAmount(DictInit.getCodeValue(SystemConstant.DKJE, financeBondDetail.getExistAmount()))
                .build();
        return bondCompanyDetailVo;
    }

    /**
     * 获取申请的企业信息(前台：个人中心)
     * @param page
     * @param companyApplyDto
     * @author xiajunwei
     * @since 2020/6/15
     */
    @Override
    public IPage<CompanyApplyProductVo> getCompanyApplyProductVo(Page<CompanyApplyProductVo> page, CompanyApplyDto companyApplyDto) {
        IPage<CompanyApplyProductVo> companyApplyProductVoIPage = financeApplyMapper.getCompanyApplyProduct(page, companyApplyDto);
        List<CompanyApplyProductVo> companyApplyProductVos = companyApplyProductVoIPage.getRecords();

        List<CompanyApplyProductVo> companyApplyProductVoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(companyApplyProductVos)) {
            /*List<String> stock = companyApplyProductVos.stream().map(companyApplyProductVo -> {
                if(companyApplyProductVo.getFinanceType().equals(SystemConstant.SYS_FALSE)){
                    return companyApplyProductVo.getProductId().toString();
                }
                return null;
            }).collect(Collectors.toList());
            List<String> bond = companyApplyProductVos.stream().map(companyApplyProductVo -> {
                if(companyApplyProductVo.getFinanceType().equals(SystemConstant.SYS_TRUE)){
                    return companyApplyProductVo.getProductId().toString();
                }
                return null;
            }).collect(Collectors.toList());
            String stockStr = String.join(",",stock);
            String bondStr = String.join(",",bond);
            List<FinanceStockDetail> stockDetailByProductIds = financeStockDetailService.getStockDetailByProductIds(stockStr);
            stockDetailByProductIds.stream().map(financeStockDetail -> {

            }).collect(Collectors.toList());*/
            //根据financeType查询股权和债权各自展示的字段
            companyApplyProductVoList = companyApplyProductVos.stream().map(companyApplyProductVo -> {
                companyApplyProductVo.setStatus(getStatusValue(companyApplyProductVo.getStatus()));
                //股权
                if (companyApplyProductVo.getFinanceType().equals(SystemConstant.SYS_FALSE)) {
                    FinanceStockDetail financeStockDetail = financeStockDetailService.
                            getFinanceProductDetail(companyApplyProductVo.getProductId());
                    if (financeStockDetail != null) {
                        companyApplyProductVo.setIndustryDirect(getListColunmNode(SystemConstant.HYFX, financeStockDetail.getIndustryDirect()));
                        companyApplyProductVo.setFinanceQuota(getListColunmNode(SystemConstant.RZED, financeStockDetail.getFinanceQuota()));
                        companyApplyProductVo.setFinanceState(getListColunmNode(SystemConstant.RZJD, financeStockDetail.getFinanceState()));
                    }
                } else {
                    //债权
                    FinanceBondDetail financeBondDetail = financeBondDetailService.
                            getFinanceProductDetail(companyApplyProductVo.getProductId());
                    if (financeBondDetail != null) {
                        companyApplyProductVo.setIndustryDirect(getListColunmNode(SystemConstant.HYFX, financeBondDetail.getIndustryDirect()));
                        companyApplyProductVo.setLoanQuota(DictInit.getCodeValue(SystemConstant.DKED, financeBondDetail.getLoanQuota()));
                        companyApplyProductVo.setLoanTerm(DictInit.getCodeValue(SystemConstant.DKQX, financeBondDetail.getLoanTerm()));
                    }
                }
                return companyApplyProductVo;
            }).collect(Collectors.toList());
        }
        companyApplyProductVoIPage.setRecords(companyApplyProductVoList);
        return companyApplyProductVoIPage;
    }

    public String getColunmNode(String codeTypo, String colunm) {
        if (!StringUtils.isEmpty(colunm)) {
            Node node = JSONObject.parseObject(colunm, Node.class);
            String code = node.getCode();
            String value = node.getValue();
            if (StringUtils.isEmpty(value)) {
                return DictInit.getCodeValue(codeTypo, code);
            } else {
                return value;
            }
        }
        return null;
    }

    public String getListColunmNode(String codeTypo, String colunm) {
        if (!StringUtils.isEmpty(colunm)) {
            List<Node> nodes = JSONObject.parseArray(colunm, Node.class);
            List<String> colunms = nodes.stream().map(node -> {
                if (StringUtils.isEmpty(node.getValue())) {
                    return DictInit.getCodeValue(codeTypo, node.getCode());
                } else {
                    return node.getValue();
                }
            }).collect(Collectors.toList());
            return String.join(",", colunms);
        }
        return null;
    }

    public String getStatusValue(String status) {
        switch (status) {
            case "0":
                return "待审核";
            case "1":
                return "已通过";
            case "2":
                return "已驳回";
            default:
                return null;
        }
    }

}
