package com.july.company.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.finance.FinanceProductDto;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.ProductInfoDto;
import com.july.company.dto.finance.ProductMatchDto;
import com.july.company.entity.FinanceProduct;
import com.july.company.entity.FinanceProductDetail;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.service.FinanceProductDetailService;
import com.july.company.service.FinanceProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.finance.FinanceProductVo;
import com.july.company.vo.finance.FinanceProductDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品信息 服务实现类
 * </p>
 * @author zengxueqi
 * @since 2020-05-20
 */
@Service
public class FinanceProductServiceImpl extends ServiceImpl<FinanceProductMapper, FinanceProduct> implements FinanceProductService {

    @Resource
    private FinanceProductMapper financeProductMapper;
    @Resource
    private FinanceProductDetailService financeProductDetailService;

    /**
     * 获取产品列表信息
     * @param page
     * @param stockCompanyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceProductVo>
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public IPage<FinanceProductVo> getFinanceProduct(Page<FinanceProductVo> page, FinanceProductDto stockCompanyDto) {
        IPage<FinanceProductVo> companyVoIPage = financeProductMapper.getFinanceProduct(page, stockCompanyDto);
        if (!CollectionUtils.isEmpty(companyVoIPage.getRecords())) {
            List<FinanceProductVo> stockCompanyVos = companyVoIPage.getRecords().stream().map(stockCompanyVo -> {
                stockCompanyVo.setFinanceQuotaStr(DictInit.getCodeValue(SystemConstant.RZED, stockCompanyVo.getFinanceQuota() + ""));
                stockCompanyVo.setFinanceStateStr(DictInit.getCodeValue(SystemConstant.RZJD, stockCompanyVo.getFinanceState() + ""));
                stockCompanyVo.setIndustryDirectStr(DictInit.getCodeValue(SystemConstant.HYFX, stockCompanyVo.getIndustryDirect() + ""));
                return stockCompanyVo;
            }).collect(Collectors.toList());
            companyVoIPage.setRecords(stockCompanyVos);
        }
        return companyVoIPage;
    }

    /**
     * 获取产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public FinanceProductDetailVo getFinanceProductDetail(FinanceProductDetailDto financeProductDetailDto) {
        BnException.of(financeProductDetailDto.getCompanyId() == null, "股权融资公司id不能为空！");

        FinanceProduct financeProduct = this.getById(financeProductDetailDto.getCompanyId());
        FinanceProductDetail financeProductDetail = financeProductDetailService.getFinanceProductDetail(financeProduct.getId());
        return FinanceProductDetailVo.builder()
                .mechanism(financeProduct.getMechanism())
                .workAddress(financeProduct.getWorkAddress())
                .tel(financeProduct.getTel())
                .introduce(financeProduct.getIntroduce())
                .registerAddressStr(DictInit.getCodeValue(SystemConstant.REGION, financeProductDetail.getRegisterAddress() + ""))
                .financeStateStr(DictInit.getCodeValue(SystemConstant.RZJD, financeProductDetail.getFinanceState() + ""))
                .financeQuotaStr(DictInit.getCodeValue(SystemConstant.RZED, financeProductDetail.getFinanceQuota() + ""))
                .industryDirectStr(DictInit.getCodeValue(SystemConstant.HYFX, financeProductDetail.getIndustryDirect() + ""))
                .shareholderStr(DictInit.getCodeValue(SystemConstant.GDBJ, financeProductDetail.getShareholder() + ""))
                .productStateStr(DictInit.getCodeValue(SystemConstant.CPJD, financeProductDetail.getProductState() + ""))
                .businessStr(DictInit.getCodeValue(SystemConstant.YYSR, financeProductDetail.getBusiness() + ""))
                .businessAddRateStr(DictInit.getCodeValue(SystemConstant.YYZZL, financeProductDetail.getBusinessAddRate() + ""))
                .productRateStr(DictInit.getCodeValue(SystemConstant.CPMLL, financeProductDetail.getProductRate() + ""))
                .netInterestRateStr(DictInit.getCodeValue(SystemConstant.JLL, financeProductDetail.getNetInterestRate() + ""))
                .oldFinanceQuotaStr(DictInit.getCodeValue(SystemConstant.GWRZJE, financeProductDetail.getOldFinanceQuota() + ""))
                .experienceStr(DictInit.getCodeValue(SystemConstant.CYJL, financeProductDetail.getExperience() + ""))
                .staffCountStr(DictInit.getCodeValue(SystemConstant.YGRS, financeProductDetail.getStaffCount() + ""))
                .marketCapacityStr(DictInit.getCodeValue(SystemConstant.MBSCRL, financeProductDetail.getMarketCapacity() + ""))
                .marketAddRateStr(DictInit.getCodeValue(SystemConstant.MBSCZZL, financeProductDetail.getMarketAddRate() + ""))
                .targetCustomerStr(DictInit.getCodeValue(SystemConstant.MBKH, financeProductDetail.getTargetCustomer() + ""))
                .marketOccupyRateStr(DictInit.getCodeValue(SystemConstant.SCZYL, financeProductDetail.getMarketOccupyRate() + ""))
                .boolBuyBackStr(DictInit.getCodeValue(SystemConstant.SFJSHG, financeProductDetail.getBoolBuyBack() + ""))
                .patentCountStr(DictInit.getCodeValue(SystemConstant.FMZLS, financeProductDetail.getPatentCount() + ""))
                .advantageStr(DictInit.getCodeValue(SystemConstant.GSJZYS, financeProductDetail.getAdvantage() + ""))
                .capitalsStr(DictInit.getCodeValue(SystemConstant.GDLJTRZJ, financeProductDetail.getCapitals() + ""))
                .evaluateNameStr(DictInit.getCodeValue(SystemConstant.PDCH, financeProductDetail.getEvaluateName() + ""))


                .loanTerm(DictInit.getCodeValue(SystemConstant.DKQX, financeProductDetail.getLoanTerm() + ""))
                .loanQuota(DictInit.getCodeValue(SystemConstant.DKED, financeProductDetail.getLoanQuota() + ""))
                .creditType(DictInit.getCodeValue(SystemConstant.ZXFS, financeProductDetail.getCreditType() + ""))
                .houseMortgage(DictInit.getCodeValue(SystemConstant.FCDY, financeProductDetail.getHouseMortgage() + ""))
                .cashFlow(DictInit.getCodeValue(SystemConstant.XJLJE, financeProductDetail.getCashFlow() + ""))
                .goverOrderAmount(DictInit.getCodeValue(SystemConstant.ZFDDE, financeProductDetail.getGoverOrderAmount() + ""))
                .nationOrderAmount(DictInit.getCodeValue(SystemConstant.GQDDE, financeProductDetail.getNationOrderAmount() + ""))
                .assetAmount(financeProductDetail.getNationOrderAmount() + "")
                .liabilitiesAmount(financeProductDetail.getLiabilitiesAmount())
                .owner(financeProductDetail.getOwner())
                .qualification(DictInit.getCodeValue(SystemConstant.QYZZ, financeProductDetail.getQualification() + ""))
                .subsidy(DictInit.getCodeValue(SystemConstant.ZFBT, financeProductDetail.getSubsidy() + ""))
                .boolIntroduce(DictInit.getCodeValue(SystemConstant.GQTZ, financeProductDetail.getBoolIntroduce() + ""))
                .taxAmount(DictInit.getCodeValue(SystemConstant.NRED, financeProductDetail.getTaxAmount() + ""))
                .boolLoan(DictInit.getCodeValue(SystemConstant.QTDK, financeProductDetail.getBoolLoan() + ""))
                .existAmount(DictInit.getCodeValue(SystemConstant.DKJE, financeProductDetail.getExistAmount() + ""))
                .jlr(DictInit.getCodeValue(SystemConstant.JLR, financeProductDetail.getJlr() + ""))
                .build();
    }

    /**
     * 一键匹配信息
     * @param productMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    @Override
    public String getOneKeyMatching(ProductMatchDto productMatchDto) {
        List<String> matchingData = oneKeyMatchingData(productMatchDto);
        if (!CollectionUtils.isEmpty(matchingData)) {
            return String.join(",", matchingData);
        }
        return null;
    }

    /**
     * 一键匹配的产品数据
     * @param productMatchDto
     * @return java.util.List<java.lang.Long>
     * @author zengxueqi
     * @since 2020/5/25
     */
    public List<String> oneKeyMatchingData(ProductMatchDto productMatchDto) {
        //TODO 目前按照匹配字段平均分配 100/24
        double everyOne = 4.1;

        List<ProductInfoDto> productInfoDtos = financeProductMapper.getAllProduct();
        //匹配上的产品
        List<String> matchingProducts = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productInfoDtos)) {
            for (ProductInfoDto productInfoDto : productInfoDtos) {
                double matchingRate = 0.0;
                //注册地址
                if (productInfoDto.getRegisterAddress().equals(productMatchDto.getRegisterAddress())) {
                    matchingRate += 4.1;
                }
                //融资阶段
                if (productInfoDto.getFinanceState().equals(productMatchDto.getFinanceState())) {
                    matchingRate += 4.1;
                }
                //融资额度
                if (productInfoDto.getFinanceQuota().equals(productMatchDto.getFinanceQuota())) {
                    matchingRate += 4.1;
                }
                //行业方向
                if (productInfoDto.getIndustryDirect().equals(productMatchDto.getIndustryDirect())) {
                    matchingRate += 4.1;
                }
                //股东背景
                if (productInfoDto.getShareholder().equals(productMatchDto.getShareholder())) {
                    matchingRate += 4.1;
                }
                //营业收入
                if (productInfoDto.getBusiness().equals(productMatchDto.getBusiness())) {
                    matchingRate += 4.1;
                }
                //产品阶段
                if (productInfoDto.getProductState().equals(productMatchDto.getProductState())) {
                    matchingRate += 4.1;
                }
                //营业收入增长率
                if (productInfoDto.getBusinessAddRate().equals(productMatchDto.getBusinessAddRate())) {
                    matchingRate += 4.1;
                }
                //产品毛利率/预期产品毛利率
                if (productInfoDto.getProductRate().equals(productMatchDto.getProductRate())) {
                    matchingRate += 4.1;
                }
                //净利率
                if (productInfoDto.getNetInterestRate().equals(productMatchDto.getNetInterestRate())) {
                    matchingRate += 4.1;
                }
                //过往融资金额
                if (productInfoDto.getOldFinanceQuota().equals(productMatchDto.getOldFinanceQuota())) {
                    matchingRate += 4.1;
                }
                //实际控制人创业经历
                if (productInfoDto.getExperience().equals(productMatchDto.getExperience())) {
                    matchingRate += 4.1;
                }
                //员工人数
                if (productInfoDto.getStaffCount().equals(productMatchDto.getStaffCount())) {
                    matchingRate += 4.1;
                }
                //目前市场容量
                if (productInfoDto.getMarketCapacity().equals(productMatchDto.getMarketCapacity())) {
                    matchingRate += 4.1;
                }
                //目标市场增长率
                if (productInfoDto.getMarketAddRate().equals(productMatchDto.getMarketAddRate())) {
                    matchingRate += 4.1;
                }
                //目标客户
                if (productInfoDto.getTargetCustomer().equals(productMatchDto.getTargetCustomer())) {
                    matchingRate += 4.1;
                }
                //市场占有率/预期市场占有率
                if (productInfoDto.getMarketOccupyRate().equals(productMatchDto.getMarketOccupyRate())) {
                    matchingRate += 4.1;
                }
                //是否接收回购
                if (productInfoDto.getBoolBuyBack().equals(productMatchDto.getBoolBuyBack())) {
                    matchingRate += 4.1;
                }
                //发明专利数量
                if (productInfoDto.getPatentCount().equals(productMatchDto.getPatentCount())) {
                    matchingRate += 4.1;
                }
                //公司竞争优势
                if (productInfoDto.getAdvantage().equals(productMatchDto.getAdvantage())) {
                    matchingRate += 4.1;
                }
                //股东累计投入资金
                if (productInfoDto.getCapitals().equals(productMatchDto.getCapitals())) {
                    matchingRate += 4.1;
                }
                //公司所获评定名称
                if (productInfoDto.getEvaluateName().equals(productMatchDto.getEvaluateName())) {
                    matchingRate += 4.1;
                }
                //预计上市时间
                if (productInfoDto.getTimeToMarket().equals(productMatchDto.getTimeToMarket())) {
                    matchingRate += 4.1;
                }
                //历史创业企业状态
                if (productInfoDto.getCompanyStatus().equals(productMatchDto.getCompanyStatus())) {
                    matchingRate += 4.1;
                }

                //贷款期限
                if (productInfoDto.getLoanTerm().equals(productMatchDto.getLoanTerm())) {
                    matchingRate += 4.1;
                }
                //贷款额度
                if (productInfoDto.getLoanQuota().equals(productMatchDto.getLoanQuota())) {
                    matchingRate += 4.1;
                }
                //增信方式
                if (productInfoDto.getCreditType().equals(productMatchDto.getCreditType())) {
                    matchingRate += 4.1;
                }
                //房产抵押
                if (productInfoDto.getHouseMortgage().equals(productMatchDto.getHouseMortgage())) {
                    matchingRate += 4.1;
                }
                //现金流
                if (productInfoDto.getCashFlow().equals(productMatchDto.getCashFlow())) {
                    matchingRate += 4.1;
                }
                //政府订单额
                if (productInfoDto.getGoverOrderAmount().equals(productMatchDto.getGoverOrderAmount())) {
                    matchingRate += 4.1;
                }
                //国企订单额
                if (productInfoDto.getNationOrderAmount().equals(productMatchDto.getNationOrderAmount())) {
                    matchingRate += 4.1;
                }
                //资产总额
                if (productInfoDto.getAssetAmount().equals(productMatchDto.getAssetAmount())) {
                    matchingRate += 4.1;
                }
                //负债总额
                if (productInfoDto.getLiabilitiesAmount().equals(productMatchDto.getLiabilitiesAmount())) {
                    matchingRate += 4.1;
                }
                //所有者权益
                if (productInfoDto.getOwner().equals(productMatchDto.getOwner())) {
                    matchingRate += 4.1;
                }
                //企业资质
                if (productInfoDto.getQualification().equals(productMatchDto.getQualification())) {
                    matchingRate += 4.1;
                }
                //政府补贴
                if (productInfoDto.getSubsidy().equals(productMatchDto.getSubsidy())) {
                    matchingRate += 4.1;
                }
                //是否引入股权投资
                if (productInfoDto.getBoolIntroduce().equals(productMatchDto.getBoolIntroduce())) {
                    matchingRate += 4.1;
                }
                //纳税额度
                if (productInfoDto.getTaxAmount().equals(productMatchDto.getTaxAmount())) {
                    matchingRate += 4.1;
                }
                //是否有其他贷款
                if (productInfoDto.getBoolLoan().equals(productMatchDto.getBoolLoan())) {
                    matchingRate += 4.1;
                }
                //现有贷款金额
                if (productInfoDto.getExistAmount().equals(productMatchDto.getExistAmount())) {
                    matchingRate += 4.1;
                }
                //净利润
                if (productInfoDto.getJlr().equals(productMatchDto.getJlr())) {
                    matchingRate += 4.1;
                }

                if (matchingRate > 50) {
                    matchingProducts.add(productInfoDto.getProductId().toString());
                }
            }
        }
        return matchingProducts;
    }

}
