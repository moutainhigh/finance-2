package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.stock.StockCompanyDetailDto;
import com.july.company.dto.stock.StockCompanyDto;
import com.july.company.entity.FinanceCompany;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceCompanyMapper;
import com.july.company.service.CompanyDetailService;
import com.july.company.service.FinanceCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.stock.StockCompanyDetailVo;
import com.july.company.vo.stock.StockCompanyVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 融资公司信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-17
 */
@Service
public class FinanceCompanyServiceImpl extends ServiceImpl<FinanceCompanyMapper, FinanceCompany> implements FinanceCompanyService {

    @Resource
    private FinanceCompanyMapper financeCompanyMapper;
    @Resource
    private CompanyDetailService companyDetailService;

    /**
     * 获取股权融资公司列表信息
     * @param page
     * @param stockCompanyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.stock.StockCompanyVo>
     * @author zengxueqi
     * @since 2020/5/17
     */
    @Override
    public IPage<StockCompanyVo> getFinanceCompany(Page<StockCompanyVo> page, StockCompanyDto stockCompanyDto) {
        IPage<StockCompanyVo> companyVoIPage = financeCompanyMapper.getFinanceCompany(page, stockCompanyDto);
        if (!CollectionUtils.isEmpty(companyVoIPage.getRecords())) {
            List<StockCompanyVo> stockCompanyVos = companyVoIPage.getRecords().stream().map(stockCompanyVo -> {
                stockCompanyVo.setFinanceQuotaStr(DictInit.getCodeName(SystemConstant.RZED, stockCompanyVo.getFinanceQuota() + ""));
                stockCompanyVo.setFinanceStateStr(DictInit.getCodeName(SystemConstant.RZJD, stockCompanyVo.getFinanceState() + ""));
                stockCompanyVo.setIndustryDirectStr(DictInit.getCodeName(SystemConstant.HYFX, stockCompanyVo.getIndustryDirect() + ""));
                return stockCompanyVo;
            }).collect(Collectors.toList());
            companyVoIPage.setRecords(stockCompanyVos);
        }
        return companyVoIPage;
    }

    /**
     * 获取股权融资公司明细信息
     * @param stockCompanyDetailDto
     * @return com.july.company.vo.stock.StockCompanyDetailVo
     * @author zengxueqi
     * @since 2020/5/17
     */
    @Override
    public StockCompanyDetailVo getFinanceCompanyDetail(StockCompanyDetailDto stockCompanyDetailDto) {
        BnException.of(stockCompanyDetailDto.getCompanyId() == null, "股权融资公司id不能为空！");
        StockCompanyDetailVo stockCompanyDetailVo = new StockCompanyDetailVo();

        FinanceCompany financeCompany = this.getById(stockCompanyDetailDto.getCompanyId());

        return null;
    }

}
