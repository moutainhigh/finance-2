package com.july.company.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.Node;
import com.july.company.dto.finance.BondProductInfoDto;
import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.*;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceBondMatchMapper;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 债券融资一键匹配 服务实现类
 * @author zengxueqi
 * @since 2020-06-08
 */
@Service
public class FinanceBondMatchServiceImpl extends ServiceImpl<FinanceBondMatchMapper, FinanceBondMatch> implements FinanceBondMatchService {

    @Resource
    private FinanceProductMapper financeProductMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private CompanyService companyService;
    @Resource
    private FinanceBondDetailService financeBondDetailService;
    @Resource
    private OperateDataService operateDataService;

    /**
     * 一键匹配债券产品信息
     * @param bondProductMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    @Override
    public String getBondOneKeyMatching(BondProductMatchDto bondProductMatchDto) {
        List<String> matchingData = oneKeyMatchingData(bondProductMatchDto);
        if (!CollectionUtils.isEmpty(matchingData)) {
            return String.join(",", matchingData);
        }
        return null;
    }

    /**
     * 一键匹配保存债券产品信息
     * @param bondProductMatchDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBondOneKeyMatching(BondProductMatchDto bondProductMatchDto) {
        UserInfoDto userInfoDto = UserUtils.getUser();
        BnException.of(userInfoDto == null, "用户信息获取失败！");

        //获取公司信息
        UserInfo userInfo = userInfoService.getById(userInfoDto.getId());
        Company company = companyService.getById(userInfo.getCompanyId());

        //保存债权融资匹配信息
        FinanceBondDetail financeBondDetail = FinanceBondDetail.builder()
                .registerAddress(getObjectToJSONStr(bondProductMatchDto.getRegisterAddress()))
                .industryDirect(getObjectToJSONStr(bondProductMatchDto.getIndustryDirect()))
                .shareholder(getObjectToJSONStr(bondProductMatchDto.getShareholder()))
                .business(bondProductMatchDto.getBusiness())
                .patentCount(bondProductMatchDto.getPatentCount())
                .loanTerm(bondProductMatchDto.getLoanTerm())
                .loanQuota(bondProductMatchDto.getLoanQuota())
                .creditType(getObjectToJSONStr(bondProductMatchDto.getCreditType()))
                .houseMortgage(bondProductMatchDto.getHouseMortgage())
                .cashFlow(bondProductMatchDto.getCashFlow())
                .goverOrderAmount(bondProductMatchDto.getGoverOrderAmount())
                .nationOrderAmount(bondProductMatchDto.getNationOrderAmount())
                .termLoan(bondProductMatchDto.getTermLoan())
                .assetAmount(bondProductMatchDto.getAssetAmount())
                .liabilitiesAmount(bondProductMatchDto.getLiabilitiesAmount())
                .owner(bondProductMatchDto.getOwner())
                .qualification(getObjectToJSONStr(bondProductMatchDto.getQualification()))
                .subsidy(bondProductMatchDto.getSubsidy())
                .boolIntroduce(bondProductMatchDto.getBoolIntroduce())
                .taxAmount(bondProductMatchDto.getTaxAmount())
                .boolLoan(getObjectToJSONStr(bondProductMatchDto.getBoolLoan()))
                .existAmount(bondProductMatchDto.getExistAmount())
                .jlr(bondProductMatchDto.getJlr())
                .debtRatio(bondProductMatchDto.getDebtRatio())
                .yield(bondProductMatchDto.getYield())
                .lastSubsidy(bondProductMatchDto.getLastSubsidy())
                .build();
        financeBondDetailService.save(financeBondDetail);

        //保存一键匹配的信息
        FinanceBondMatch financeBondMatch = FinanceBondMatch.builder()
                .companyId(company.getId())
                .detailId(financeBondDetail.getId())
                .lastSubsidy(bondProductMatchDto.getLastSubsidy())
                .liabilitiesAmount(bondProductMatchDto.getLiabilitiesAmount())
                .assetAmount(bondProductMatchDto.getAssetAmount())
                .owner(bondProductMatchDto.getOwner())
                .build();
        this.save(financeBondMatch);

        //保存一键匹配json信息
        operateDataService.saveOrUpdateMatchData(bondProductMatchDto.getOperateMatchDto());

        //更新公司信息
        company.setRegisterAddress(getObjectToJSONStr(bondProductMatchDto.getRegisterAddress()));
        company.setWorkAddress(bondProductMatchDto.getWorkAddress());
        company.setContact(bondProductMatchDto.getContact());
        company.setTel(bondProductMatchDto.getTel());
        company.setIntroduce(bondProductMatchDto.getIntroduce());
        companyService.updateById(company);
    }

    public String getObjectToJSONStr(Node node) {
        if (node == null) {
            return null;
        } else {
            return JSON.toJSONString(node);
        }
    }

    public String getObjectToJSONStr(List<Node> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return null;
        } else {
            return JSON.toJSONString(nodes);
        }
    }

    public String getCode(String code, String value) {
        if (StringUtils.isEmpty(value)) {
            return code;
        } else {
            return value;
        }
    }

    /**
     * 一键匹配的债券产品数据
     * @param productMatchDto
     * @return java.util.List<java.lang.Long>
     * @author zengxueqi
     * @since 2020/5/25
     */
    public List<String> oneKeyMatchingData(BondProductMatchDto productMatchDto) {
        //TODO 目前按照匹配字段平均分配 100/21
        double everyOne = 4.5;

        List<BondProductInfoDto> productInfoDtos = financeProductMapper.getAllBondProduct();
        //匹配上的产品
        List<String> matchingProducts = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productInfoDtos)) {
            for (BondProductInfoDto productInfoDto : productInfoDtos) {
                double matchingRate = 0.0;
                //注册地址
                if (productInfoDto.getRegisterAddress().equals(productMatchDto.getRegisterAddress().getCode()) && StringUtils.isEmpty(productMatchDto.getRegisterAddress().getValue())) {
                    matchingRate += everyOne;
                }
                //行业方向
                List<Node> nodes1 = productMatchDto.getIndustryDirect();
                if (!CollectionUtils.isEmpty(nodes1)) {
                    for (Node node : nodes1) {
                        if (productInfoDto.getIndustryDirect().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //股东背景
                List<Node> nodes2 = productMatchDto.getShareholder();
                if (!CollectionUtils.isEmpty(nodes2)) {
                    for (Node node : nodes2) {
                        if (productInfoDto.getShareholder().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //营业收入
                if (productInfoDto.getBusiness().equals(productMatchDto.getBusiness())) {
                    matchingRate += everyOne;
                }
                //贷款期限
                if (productInfoDto.getLoanTerm().equals(productMatchDto.getLoanTerm())) {
                    matchingRate += everyOne;
                }
                //贷款额度
                if (productInfoDto.getLoanQuota().equals(productMatchDto.getLoanQuota())) {
                    matchingRate += everyOne;
                }
                //增信方式
                List<Node> nodes3 = productMatchDto.getCreditType();
                if (!CollectionUtils.isEmpty(nodes3)) {
                    for (Node node : nodes3) {
                        if (productInfoDto.getCreditType().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //房产抵押
                if (productInfoDto.getHouseMortgage().equals(productMatchDto.getHouseMortgage())) {
                    matchingRate += everyOne;
                }
                //现金流
                if (productInfoDto.getCashFlow().equals(productMatchDto.getCashFlow())) {
                    matchingRate += everyOne;
                }
                //政府订单额
                if (productInfoDto.getGoverOrderAmount().equals(productMatchDto.getGoverOrderAmount())) {
                    matchingRate += everyOne;
                }
                //国企订单额
                if (productInfoDto.getNationOrderAmount().equals(productMatchDto.getNationOrderAmount())) {
                    matchingRate += everyOne;
                }
                //资产负债率=负债总额/资产总额
                String debtRatio = String.format("%.2f", Double.parseDouble(productMatchDto.getLiabilitiesAmount()) / Double.parseDouble(productMatchDto.getAssetAmount()));
                int a = (int) (Double.parseDouble(debtRatio) * 100);
                List<Node> nodes = SystemConstant.getDebtratioInfo();
                for (Node node : nodes) {
                    if (node.getType().equals(productInfoDto.getDebtRatio()) && Integer.parseInt(node.getCode()) <= a && a < Integer.parseInt(node.getValue())) {
                        matchingRate += everyOne;
                        break;
                    }
                }
                //所有者权益 TODO 暂时不匹配
                /*if (productInfoDto.getOwner().equals(productMatchDto.getOwner())) {
                    matchingRate += everyOne;
                }*/
                //企业资质
                List<Node> nodes4 = productMatchDto.getQualification();
                if (!CollectionUtils.isEmpty(nodes4)) {
                    for (Node node : nodes4) {
                        if (productInfoDto.getQualification().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //本年度政府补贴
                if (productInfoDto.getSubsidy().equals(productMatchDto.getSubsidy())) {
                    matchingRate += everyOne;
                }
                //上一年度政府补贴
                if (productInfoDto.getLastSubsidy().equals(productMatchDto.getLastSubsidy())) {
                    matchingRate += everyOne;
                }
                //是否引入股权投资
                if (productInfoDto.getBoolIntroduce().equals(productMatchDto.getBoolIntroduce())) {
                    matchingRate += everyOne;
                }
                //纳税额度
                if (productInfoDto.getTaxAmount().equals(productMatchDto.getTaxAmount())) {
                    matchingRate += everyOne;
                }
                //是否有其他贷款
                List<Node> nodes5 = productMatchDto.getBoolLoan();
                if (!CollectionUtils.isEmpty(nodes5)) {
                    for (Node node : nodes5) {
                        if (productInfoDto.getBoolLoan().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //现有贷款金额
                if (productInfoDto.getExistAmount().equals(productMatchDto.getExistAmount())) {
                    matchingRate += everyOne;
                }
                //净利润
                if (productInfoDto.getJlr().equals(productMatchDto.getJlr())) {
                    matchingRate += everyOne;
                }

                if (matchingRate > 50) {
                    matchingProducts.add(productInfoDto.getProductId().toString());
                }
            }
        }
        return matchingProducts;
    }

    /**
     * 获取企业的一键匹配信息
     * @param companyId
     * @return java.util.List<com.july.company.entity.FinanceBondMatch>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public List<FinanceBondMatch> getFinanceBondMatch(Long companyId) {
        QueryWrapper<FinanceBondMatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(companyId != null, "companyId", companyId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.list(queryWrapper);
    }

}
