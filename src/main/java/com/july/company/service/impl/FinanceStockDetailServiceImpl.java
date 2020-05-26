package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.Node;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.BondProductInfoDto;
import com.july.company.dto.finance.StockProductInfoDto;
import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.entity.FinanceProduct;
import com.july.company.entity.FinanceStockDetail;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.mapper.FinanceStockDetailMapper;
import com.july.company.service.FinanceProductService;
import com.july.company.service.FinanceStockDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.finance.FinanceStockProductDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 融资公司明细信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-26
 */
@Service
public class FinanceStockDetailServiceImpl extends ServiceImpl<FinanceStockDetailMapper, FinanceStockDetail> implements FinanceStockDetailService {

    @Resource
    private FinanceProductService financeProductService;
    @Resource
    private FinanceProductMapper financeProductMapper;

    /**
     * 获取股权融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public FinanceStockProductDetailVo getFinanceStockProductDetail(FinanceProductDetailDto financeProductDetailDto) {
        BnException.of(financeProductDetailDto.getCompanyId() == null, "股权融资公司id不能为空！");

        FinanceProduct financeProduct = financeProductService.getById(financeProductDetailDto.getCompanyId());
        FinanceStockDetail financeProductDetail = getFinanceProductDetail(financeProduct.getId());
        return FinanceStockProductDetailVo.builder()
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
                .businessStr(DictInit.getCodeValue(SystemConstant.STOCKRIGHT_YYSR, financeProductDetail.getBusiness() + ""))
                .businessAddRateStr(DictInit.getCodeValue(SystemConstant.YYSRZZL, financeProductDetail.getBusinessAddRate() + ""))
                .productRateStr(DictInit.getCodeValue(SystemConstant.CPMLL, financeProductDetail.getProductRate() + ""))
                .netInterestRateStr(DictInit.getCodeValue(SystemConstant.JLL, financeProductDetail.getNetInterestRate() + ""))
                .oldFinanceQuotaStr(DictInit.getCodeValue(SystemConstant.GWRZJE, financeProductDetail.getOldFinanceQuota() + ""))
                .experienceStr(DictInit.getCodeValue(SystemConstant.CYJL, financeProductDetail.getExperience() + ""))
                .staffCountStr(DictInit.getCodeValue(SystemConstant.YGRS, financeProductDetail.getStaffCount() + ""))
                .marketCapacityStr(DictInit.getCodeValue(SystemConstant.SCRL, financeProductDetail.getMarketCapacity() + ""))
                .marketAddRateStr(DictInit.getCodeValue(SystemConstant.SCRLZZL, financeProductDetail.getMarketAddRate() + ""))
                .targetCustomerStr(DictInit.getCodeValue(SystemConstant.MBKH, financeProductDetail.getTargetCustomer() + ""))
                .marketOccupyRateStr(DictInit.getCodeValue(SystemConstant.SCZYL, financeProductDetail.getMarketOccupyRate() + ""))
                .boolBuyBackStr(DictInit.getCodeValue(SystemConstant.SFHG, financeProductDetail.getBoolBuyBack() + ""))
                .patentCountStr(DictInit.getCodeValue(SystemConstant.STOCKRIGHT_FMZLSL, financeProductDetail.getPatentCount() + ""))
                .advantageStr(DictInit.getCodeValue(SystemConstant.GSJZYS, financeProductDetail.getAdvantage() + ""))
                .capitalsStr(DictInit.getCodeValue(SystemConstant.GDLJTRZJ, financeProductDetail.getCapitals() + ""))
                .evaluateNameStr(DictInit.getCodeValue(SystemConstant.PDCH, financeProductDetail.getEvaluateName() + ""))
                .build();
    }

    /**
     * 获取股权产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    public FinanceStockDetail getFinanceProductDetail(Long productId) {
        QueryWrapper<FinanceStockDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(productId != null, "productId", productId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

    /**
     * 一键匹配股权产品信息
     * @param stockProductMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    @Override
    public String getStockOneKeyMatching(StockProductMatchDto stockProductMatchDto) {
        List<String> matchingData = oneKeyMatchingData(stockProductMatchDto);
        if (!CollectionUtils.isEmpty(matchingData)) {
            return String.join(",", matchingData);
        }
        return null;
    }

    /**
     * 一键匹配债券产品信息
     * @param productMatchDto
     * @return java.util.List<java.lang.Long>
     * @author zengxueqi
     * @since 2020/5/25
     */
    public List<String> oneKeyMatchingData(StockProductMatchDto productMatchDto) {
        //TODO 目前按照匹配字段平均分配 100/24
        double everyOne = 4.1;

        List<StockProductInfoDto> productInfoDtos = financeProductMapper.getAllStockProduct();
        //匹配上的产品
        List<String> matchingProducts = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productInfoDtos)) {
            for (StockProductInfoDto productInfoDto : productInfoDtos) {
                double matchingRate = 0.0;
                //注册地址
                if (productInfoDto.getRegisterAddress().equals(productMatchDto.getRegisterAddress().getCode()) && StringUtils.isEmpty(productMatchDto.getRegisterAddress().getValue())) {
                    matchingRate += everyOne;
                }
                //融资阶段
                if (productInfoDto.getFinanceState().equals(productMatchDto.getFinanceState().getCode()) && StringUtils.isEmpty(productMatchDto.getFinanceState().getValue())) {
                    matchingRate += everyOne;
                }
                //融资额度
                if (productInfoDto.getFinanceQuota().equals(productMatchDto.getFinanceQuota())) {
                    matchingRate += everyOne;
                }
                //行业方向
                if (productInfoDto.getIndustryDirect().equals(productMatchDto.getIndustryDirect().getCode()) && StringUtils.isEmpty(productMatchDto.getIndustryDirect().getValue())) {
                    matchingRate += everyOne;
                }
                //股东背景
                if (productInfoDto.getShareholder().equals(productMatchDto.getShareholder().getCode()) && StringUtils.isEmpty(productMatchDto.getShareholder().getValue())) {
                    matchingRate += everyOne;
                }
                //营业收入
                if (productInfoDto.getBusiness().equals(productMatchDto.getBusiness())) {
                    matchingRate += everyOne;
                }
                //产品阶段
                if (productInfoDto.getProductState().equals(productMatchDto.getProductState().getCode()) && StringUtils.isEmpty(productMatchDto.getProductState().getValue())) {
                    matchingRate += everyOne;
                }
                //营业收入增长率
                if (productInfoDto.getBusinessAddRate().equals(productMatchDto.getBusinessAddRate())) {
                    matchingRate += everyOne;
                }
                //产品毛利率/预期产品毛利率
                if (productInfoDto.getProductRate().equals(productMatchDto.getProductRate())) {
                    matchingRate += everyOne;
                }
                //净利率
                if (productInfoDto.getNetInterestRate().equals(productMatchDto.getNetInterestRate())) {
                    matchingRate += everyOne;
                }
                //过往融资金额
                if (productInfoDto.getOldFinanceQuota().equals(productMatchDto.getOldFinanceQuota())) {
                    matchingRate += everyOne;
                }
                //实际控制人创业经历
                if (productInfoDto.getExperience().equals(productMatchDto.getExperience())) {
                    matchingRate += everyOne;
                }
                //员工人数
                if (productInfoDto.getStaffCount().equals(productMatchDto.getStaffCount())) {
                    matchingRate += everyOne;
                }
                //目前市场容量
                if (productInfoDto.getMarketCapacity().equals(productMatchDto.getMarketCapacity())) {
                    matchingRate += everyOne;
                }
                //目标市场增长率
                if (productInfoDto.getMarketAddRate().equals(productMatchDto.getMarketAddRate())) {
                    matchingRate += everyOne;
                }
                //目标客户
                if (productInfoDto.getTargetCustomer().equals(productMatchDto.getTargetCustomer().getCode()) && StringUtils.isEmpty(productMatchDto.getTargetCustomer().getValue())) {
                    matchingRate += everyOne;
                }
                //市场占有率/预期市场占有率
                if (productInfoDto.getMarketOccupyRate().equals(productMatchDto.getMarketOccupyRate())) {
                    matchingRate += everyOne;
                }
                //是否接收回购
                if (productInfoDto.getBoolBuyBack().equals(productMatchDto.getBoolBuyBack())) {
                    matchingRate += everyOne;
                }
                //发明专利数量
                if (productInfoDto.getPatentCount().equals(productMatchDto.getPatentCount())) {
                    matchingRate += everyOne;
                }
                //公司竞争优势 productInfoDto.getAdvantage() = {"code":"1","value":"1"}  node = {"code":"1","value":"1"}
                List<Node> nodes = productMatchDto.getAdvantage();
                if (!CollectionUtils.isEmpty(nodes)) {
                    for (Node node : nodes) {
                        if (productInfoDto.getAdvantage().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                        }
                    }
                }
                //股东累计投入资金
                if (productInfoDto.getCapitals().equals(productMatchDto.getCapitals())) {
                    matchingRate += everyOne;
                }
                //公司所获评定名称
                if (productInfoDto.getEvaluateName().equals(productMatchDto.getEvaluateName().getCode()) && StringUtils.isEmpty(productMatchDto.getEvaluateName())) {
                    matchingRate += everyOne;
                }
                //预计上市时间
                if (productInfoDto.getTimeToMarket().equals(productMatchDto.getTimeToMarket())) {
                    matchingRate += everyOne;
                }
                //历史创业企业状态
                if (productInfoDto.getCompanyStatus().equals(productMatchDto.getCompanyStatus())) {
                    matchingRate += everyOne;
                }
                if (matchingRate > 50) {
                    matchingProducts.add(productInfoDto.getProductId().toString());
                }
            }
        }
        return matchingProducts;
    }

}
