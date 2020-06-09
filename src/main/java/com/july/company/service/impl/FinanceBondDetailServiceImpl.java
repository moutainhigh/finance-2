package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.Node;
import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.dto.finance.BondProductInfoDto;
import com.july.company.entity.FinanceBondDetail;
import com.july.company.entity.FinanceProduct;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceBondDetailMapper;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.service.FinanceBondDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.FinanceProductService;
import com.july.company.vo.finance.FinanceBondProductDetailVo;
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
public class FinanceBondDetailServiceImpl extends ServiceImpl<FinanceBondDetailMapper, FinanceBondDetail> implements FinanceBondDetailService {

    @Resource
    private FinanceProductService financeProductService;

    /**
     * 获取债券融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public FinanceBondProductDetailVo getFinanceBondProductDetail(FinanceProductDetailDto financeProductDetailDto) {
        BnException.of(financeProductDetailDto.getCompanyId() == null, "债券融资公司id不能为空！");

        FinanceProduct financeProduct = financeProductService.getById(financeProductDetailDto.getCompanyId());
        FinanceBondDetail financeBondDetail = getFinanceProductDetail(financeProduct.getId());
        return FinanceBondProductDetailVo.builder()
                //所属机构
                .mechanism(financeProduct.getMechanism())
                //办公地址
                .workAddress(financeProduct.getWorkAddress())
                //联系方式
                .tel(financeProduct.getTel())
                //产品介绍
                .introduce(financeProduct.getIntroduce())
                //注册地址
                .registerAddressStr(DictInit.getCodeValue(SystemConstant.REGION, financeBondDetail.getRegisterAddress() + ""))
                //贷款期限
                .loanTerm(DictInit.getCodeValue(SystemConstant.DKQX, financeBondDetail.getLoanTerm() + ""))
                //贷款额度
                .loanQuota(DictInit.getCodeValue(SystemConstant.DKED, financeBondDetail.getLoanQuota() + ""))
                //行业方向
                .industryDirectStr(DictInit.getCodeValue(SystemConstant.BOND_HYFX, financeBondDetail.getIndustryDirect() + ""))
                //股东背景
                .shareholderStr(DictInit.getCodeValue(SystemConstant.BOND_GDBJ, financeBondDetail.getShareholder() + ""))
                //增信方式
                .creditType(DictInit.getCodeValue(SystemConstant.ZXFS, financeBondDetail.getCreditType() + ""))
                //是否接受房产抵押
                .houseMortgage(DictInit.getCodeValue(SystemConstant.FCDY, financeBondDetail.getHouseMortgage() + ""))
                //营业收入
                .businessStr(DictInit.getCodeValue(SystemConstant.BOND_YYSR, financeBondDetail.getBusiness() + ""))
                //净利润
                .jlr(DictInit.getCodeValue(SystemConstant.BOND_JLR, financeBondDetail.getJlr() + ""))
                //上年度经营活动现金流净额
                .cashFlow(DictInit.getCodeValue(SystemConstant.XJLJE, financeBondDetail.getCashFlow() + ""))
                //政府订单额
                .goverOrderAmount(DictInit.getCodeValue(SystemConstant.ZFDDE, financeBondDetail.getGoverOrderAmount() + ""))
                //央企/大型国企核心供应商订单额
                .nationOrderAmount(DictInit.getCodeValue(SystemConstant.GQDDE, financeBondDetail.getNationOrderAmount() + ""))
                //资产负债率
                .debtRatio(DictInit.getCodeValue(SystemConstant.ZCFZL, financeBondDetail.getDebtRatio() + ""))
                //净资产收益率
                .yield(DictInit.getCodeValue(SystemConstant.JZCSYL, financeBondDetail.getYield() + ""))

                //资产总额 TODO
                //.assetAmount(financeBondDetail.getAssetAmount())
                //负债总额 TODO
                //.liabilitiesAmount(financeBondDetail.getLiabilitiesAmount())
                //所有者收益 TODO
                //.owner(financeBondDetail.getOwner())

                //企业资质
                .qualification(DictInit.getCodeValue(SystemConstant.QYZZ, financeBondDetail.getQualification() + ""))
                //政府补贴
                .subsidy(DictInit.getCodeValue(SystemConstant.ZFBT, financeBondDetail.getSubsidy() + ""))
                //是否引入股权投资
                .boolIntroduce(DictInit.getCodeValue(SystemConstant.GQTZ, financeBondDetail.getBoolIntroduce() + ""))
                //纳税额度
                .taxAmount(DictInit.getCodeValue(SystemConstant.NRED, financeBondDetail.getTaxAmount() + ""))
                //发明专利数量
                .patentCountStr(DictInit.getCodeValue(SystemConstant.BOND_FMZLS, financeBondDetail.getPatentCount() + ""))
                //是否有其他贷款
                .boolLoan(DictInit.getCodeValue(SystemConstant.QTDK, financeBondDetail.getBoolLoan() + ""))
                //现有贷款金额
                .existAmount(DictInit.getCodeValue(SystemConstant.DKJE, financeBondDetail.getExistAmount() + ""))
                .build();
    }

    @Override
    public void updateFinanceBondProductDetailById(Long Id, FinanceBondDetail financeBondDetail) {
        QueryWrapper<FinanceBondDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", SystemConstant.SYS_FALSE);
        queryWrapper.eq("id", Id);
        FinanceBondDetail detail = this.getOne(queryWrapper);
        BnException.of(detail == null, "融资产品详情信息为空");
        financeBondDetail.setId(detail.getId());
        this.updateById(financeBondDetail);
    }


    /**
     * 获取债券产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    public FinanceBondDetail getFinanceProductDetail(Long productId) {
        QueryWrapper<FinanceBondDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(productId != null, "productId", productId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

}
