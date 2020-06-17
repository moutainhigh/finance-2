package com.july.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.Node;
import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.finance.BondSaveDetailDto;
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
import com.july.company.utils.StringUtils;
import com.july.company.vo.finance.FinanceBondProductDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .productId(financeBondDetail.getProductId())
                //所属机构
                .mechanism(financeProduct.getMechanism())
                //办公地址
                .workAddress(DictInit.getCodeValue(SystemConstant.REGION, financeProduct.getWorkAddress()))
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
                .industryDirectStr(getListColunmNode(SystemConstant.BOND_HYFX, financeBondDetail.getIndustryDirect() + ""))
                //股东背景
                .shareholderStr(getListColunmNode(SystemConstant.BOND_GDBJ, financeBondDetail.getShareholder() + ""))
                //增信方式
                .creditType(getListColunmNode(SystemConstant.ZXFS, financeBondDetail.getCreditType() + ""))
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

                //资产总额
                .assetAmount(financeBondDetail.getAssetAmount())
                //负债总额
                .liabilitiesAmount(financeBondDetail.getLiabilitiesAmount())
                //所有者收益
                .owner(financeBondDetail.getOwner())

                //企业资质
                .qualification(getListColunmNode(SystemConstant.QYZZ, financeBondDetail.getQualification() + ""))
                //政府补贴
                .subsidy(DictInit.getCodeValue(SystemConstant.ZFBT, financeBondDetail.getSubsidy() + ""))
                //是否引入股权投资
                .boolIntroduce(DictInit.getCodeValue(SystemConstant.GQTZ, financeBondDetail.getBoolIntroduce() + ""))
                //纳税额度
                .taxAmount(DictInit.getCodeValue(SystemConstant.NRED, financeBondDetail.getTaxAmount() + ""))
                //发明专利数量
                .patentCountStr(DictInit.getCodeValue(SystemConstant.BOND_FMZLS, financeBondDetail.getPatentCount() + ""))
                //是否有其他贷款
                .boolLoan(getListColunmNode(SystemConstant.QTDK, financeBondDetail.getBoolLoan() + ""))
                //现有贷款金额
                .existAmount(DictInit.getCodeValue(SystemConstant.DKJE, financeBondDetail.getExistAmount() + ""))
                .build();
    }

    @Override
    public void updateFinanceBondProductDetailById(BondSaveDetailDto bondSaveDetailDto) {
        QueryWrapper<FinanceBondDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", SystemConstant.SYS_FALSE);
        queryWrapper.eq("productId", bondSaveDetailDto.getId());
        FinanceBondDetail financeBondDetail = this.getOne(queryWrapper);
        Long id = financeBondDetail.getId();
        BeanUtils.copyProperties(bondSaveDetailDto, financeBondDetail);
        financeBondDetail.setId(id);
        this.updateById(financeBondDetail);
    }


    /**
     * 根据产品ID获取债券产品的明细信息
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

    public String getColunmNode(String codeTypo, String colunm) {
        if (!com.july.company.utils.StringUtils.isEmpty(colunm)) {
            Node node = JSONObject.parseObject(colunm, Node.class);
            String code = node.getCode();
            String value = node.getValue();
            if (com.july.company.utils.StringUtils.isEmpty(value)) {
                return DictInit.getCodeValue(codeTypo, code);
            } else {
                return value;
            }
        }
        return null;
    }

    public String getListColunmNode(String codeTypo, String colunm) {
        if (!com.july.company.utils.StringUtils.isEmpty(colunm)) {
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
}
