package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.finance.*;
import com.july.company.entity.FinanceProduct;
import com.july.company.entity.FinanceStockDetail;
import com.july.company.entity.enums.ProductStatusEnum;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.service.FinanceProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.DateUtils;
import com.july.company.vo.finance.*;
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

    /**
     * 获取股权融资信息分页（后台）
     * @param page
     * @param listStockConditionDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.finance.StockListVo>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public IPage<StockListVo> getStockList(Page<StockListVo> page, ListStockConditionDto listStockConditionDto) {
        IPage<StockListVo> stockList = financeProductMapper.getStockList(page, listStockConditionDto);
        if (!CollectionUtils.isEmpty(stockList.getRecords())) {
            List<StockListVo> stockListVos = stockList.getRecords().stream().map(stockListVo -> {
                //融资额度
                stockListVo.setFinanceQuotaStr(DictInit.getCodeValue(SystemConstant.RZED, stockListVo.getFinanceQuota() + ""));
                //产品状态
                stockListVo.setStatusStr(ProductStatusEnum.getDescByValue(stockListVo.getStatus()));
                stockListVo.setCreatedTimeStr(DateUtils.timeStamp2Date(stockListVo.getCreatedTime()));
                return stockListVo;
            }).collect(Collectors.toList());
            stockList.setRecords(stockListVos);
        }
        return stockList;
    }

    @Override
    public FinanceProduct getFinanceProductById(Long id) {
        QueryWrapper<FinanceProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<BondListVo> getBondList(Page<ListConditionDto> page, ListConditionDto listConditionDto) {
        IPage<BondListVo> bondList = financeProductMapper.getBondList(page, listConditionDto);
        if (!CollectionUtils.isEmpty(bondList.getRecords())) {
            //字典转换
            List<BondListVo> bondListVos = bondList.getRecords().stream().map(bondListVo -> {
                //注册地址
                bondListVo.setRegisterAddressStr(DictInit.getCodeValue(SystemConstant.REGION, bondListVo.getRegisterAddress() + ""));
                //营业收入
                bondListVo.setBusinessStr(DictInit.getCodeValue(SystemConstant.BOND_YYSR, bondListVo.getBusiness() + ""));
                //发明专利数量
                bondListVo.setPatentCountStr(DictInit.getCodeValue(SystemConstant.BOND_FMZLS, bondListVo.getPatentCount() + ""));
                //行业方向
                bondListVo.setIndustryDirectStr(DictInit.getCodeValue(SystemConstant.HYFX, bondListVo.getIndustryDirect() + ""));
                //股东背景
                bondListVo.setShareholderStr(DictInit.getCodeValue(SystemConstant.GDBJ, bondListVo.getShareholder() + ""));
                return bondListVo;
            }).collect(Collectors.toList());
            bondList.setRecords(bondListVos);
        }
        return bondList;
    }

}
