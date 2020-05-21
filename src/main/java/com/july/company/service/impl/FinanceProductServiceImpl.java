package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.finance.FinanceProductDto;
import com.july.company.dto.finance.FinanceProductDetailDto;
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

import javax.annotation.Resource;
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
        FinanceProductDetailVo stockCompanyDetailVo = new FinanceProductDetailVo();
        stockCompanyDetailVo.setMechanism(financeProduct.getMechanism());
        stockCompanyDetailVo.setWorkAddress(financeProduct.getWorkAddress());
        stockCompanyDetailVo.setTel(financeProduct.getTel());
        stockCompanyDetailVo.setIntroduce(financeProduct.getIntroduce());
        if (financeProduct != null) {
            FinanceProductDetail financeProductDetail = financeProductDetailService.getFinanceProductDetail(financeProduct.getId());
            stockCompanyDetailVo.setRegisterAddressStr(financeProductDetail.getRegisterAddress() + ""); //TODO 这里是选择还是输入？
            stockCompanyDetailVo.setFinanceStateStr(DictInit.getCodeValue(SystemConstant.RZJD, financeProductDetail.getFinanceState() + ""));
            stockCompanyDetailVo.setFinanceQuotaStr(DictInit.getCodeValue(SystemConstant.RZED, financeProductDetail.getFinanceQuota() + ""));
            stockCompanyDetailVo.setIndustryDirectStr(DictInit.getCodeValue(SystemConstant.HYFX, financeProductDetail.getIndustryDirect() + ""));
            stockCompanyDetailVo.setShareholderStr(DictInit.getCodeValue(SystemConstant.GDBJ, financeProductDetail.getShareholder() + ""));
            stockCompanyDetailVo.setProductStateStr(DictInit.getCodeValue(SystemConstant.CPJD, financeProductDetail.getProductState() + ""));
            stockCompanyDetailVo.setBusinessStr(DictInit.getCodeValue(SystemConstant.YYSR, financeProductDetail.getBusiness() + ""));
            stockCompanyDetailVo.setBusinessAddRateStr(DictInit.getCodeValue(SystemConstant.YYZZL, financeProductDetail.getBusinessAddRate() + ""));
            stockCompanyDetailVo.setProductRateStr(DictInit.getCodeValue(SystemConstant.CPMLL, financeProductDetail.getProductRate() + ""));
            stockCompanyDetailVo.setNetInterestRateStr(DictInit.getCodeValue(SystemConstant.JLL, financeProductDetail.getNetInterestRate() + ""));
            stockCompanyDetailVo.setOldFinanceQuotaStr(DictInit.getCodeValue(SystemConstant.GWRZJE, financeProductDetail.getOldFinanceQuota() + ""));
            stockCompanyDetailVo.setExperienceStr(DictInit.getCodeValue(SystemConstant.CYJL, financeProductDetail.getExperience() + ""));
            stockCompanyDetailVo.setStaffCountStr(DictInit.getCodeValue(SystemConstant.YGRS, financeProductDetail.getStaffCount() + ""));
            stockCompanyDetailVo.setMarketCapacityStr(DictInit.getCodeValue(SystemConstant.MBSCRL, financeProductDetail.getMarketCapacity() + ""));
            stockCompanyDetailVo.setMarketAddRateStr(DictInit.getCodeValue(SystemConstant.MBSCZZL, financeProductDetail.getMarketAddRate() + ""));
            stockCompanyDetailVo.setTargetCustomerStr(DictInit.getCodeValue(SystemConstant.MBKH, financeProductDetail.getTargetCustomer() + ""));
            stockCompanyDetailVo.setMarketOccupyRateStr(DictInit.getCodeValue(SystemConstant.SCZYL, financeProductDetail.getMarketOccupyRate() + ""));
            stockCompanyDetailVo.setBoolBuyBackStr(DictInit.getCodeValue(SystemConstant.SFJSHG, financeProductDetail.getBoolBuyBack() + ""));
            stockCompanyDetailVo.setPatentCountStr(DictInit.getCodeValue(SystemConstant.FMZLS, financeProductDetail.getPatentCount() + ""));
            stockCompanyDetailVo.setAdvantageStr(DictInit.getCodeValue(SystemConstant.GSJZYS, financeProductDetail.getAdvantage() + ""));
            stockCompanyDetailVo.setCapitalsStr(DictInit.getCodeValue(SystemConstant.GDLJTRZJ, financeProductDetail.getCapitals() + ""));
            stockCompanyDetailVo.setEvaluateNameStr(DictInit.getCodeValue(SystemConstant.PDCH, financeProductDetail.getEvaluateName() + ""));
        }
        return stockCompanyDetailVo;
    }

}
