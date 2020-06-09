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

}
