package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.finance.FinanceBondProductDto;
import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.dto.finance.OneProductDto;
import com.july.company.entity.FinanceProduct;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.service.FinanceProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.finance.FinanceBondProductVo;
import com.july.company.vo.finance.FinanceStockProductVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
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

    /**
     * 删除产品信息
     * @param oneProductDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/4
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(OneProductDto oneProductDto) {
        BnException.of(StringUtils.isEmpty(oneProductDto.getProductIds()), "删除数据时，数据id不能为空！");
        List<String> productList = Arrays.asList(oneProductDto.getProductIds().split(","));
        productList.stream().forEach(s -> {
            FinanceProduct financeProduct = this.getById(Long.parseLong(s));
            financeProduct.setDeleted(SystemConstant.SYS_TRUE);
            this.updateById(financeProduct);
        });
    }

}
