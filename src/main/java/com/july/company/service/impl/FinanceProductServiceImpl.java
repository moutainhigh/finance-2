package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.finance.FinanceBondProductDto;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.entity.FinanceProduct;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.service.FinanceProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-20
 */
@Service
public class FinanceProductServiceImpl extends ServiceImpl<FinanceProductMapper, FinanceProduct> implements FinanceProductService {

    @Resource
    private FinanceProductMapper financeProductMapper;

    /**
     * 获取股权产品列表信息
     * @param page
     * @param financeStockProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceStockProductVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @Override
    public IPage<FinanceStockProductVo> getFinanceStockProduct(Page<FinanceStockProductVo> page, FinanceStockProductDto financeStockProductDto) {
        IPage<FinanceStockProductVo> companyVoIPage = financeProductMapper.getFinanceStockProduct(page, financeStockProductDto);
        if (!CollectionUtils.isEmpty(companyVoIPage.getRecords())) {
            List<FinanceStockProductVo> stockCompanyVos = companyVoIPage.getRecords().stream().map(stockCompanyVo -> {
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
     * 获取债券产品列表信息
     * @param page
     * @param financeBondProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.FinanceBondProductVo>
     * @author zengxueqi
     * @since 2020/5/26
     */
    @Override
    public IPage<FinanceBondProductVo> getFinanceBondProduct(Page<FinanceBondProductVo> page, FinanceBondProductDto financeBondProductDto) {
        System.out.println("paixu====> " + financeBondProductDto.getOrderByField());
        IPage<FinanceBondProductVo> companyVoIPage = financeProductMapper.getFinanceBondProduct(page, financeBondProductDto);
        if (!CollectionUtils.isEmpty(companyVoIPage.getRecords())) {
            List<FinanceBondProductVo> stockCompanyVos = companyVoIPage.getRecords().stream().map(stockCompanyVo -> {
                stockCompanyVo.setLoanQuotaStr(DictInit.getCodeValue(SystemConstant.DKED, stockCompanyVo.getLoanQuota() + ""));
                stockCompanyVo.setLoanTermStr(DictInit.getCodeValue(SystemConstant.DKQX, stockCompanyVo.getLoanTerm() + ""));
                stockCompanyVo.setIndustryDirectStr(DictInit.getCodeValue(SystemConstant.HYFX, stockCompanyVo.getIndustryDirect() + ""));
                return stockCompanyVo;
            }).collect(Collectors.toList());
            companyVoIPage.setRecords(stockCompanyVos);
        }
        return companyVoIPage;
    }

}
