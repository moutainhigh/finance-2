package com.july.company.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.Node;
import com.july.company.dto.finance.StockProductInfoDto;
import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.*;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.mapper.FinanceStockMatchMapper;
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
 * 股权融资一键匹配 服务实现类
 * @author zengxueqi
 * @since 2020-06-08
 */
@Service
public class FinanceStockMatchServiceImpl extends ServiceImpl<FinanceStockMatchMapper, FinanceStockMatch> implements FinanceStockMatchService {

    @Resource
    private FinanceProductMapper financeProductMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private CompanyService companyService;
    @Resource
    private FinanceStockDetailService financeStockDetailService;
    @Resource
    private OperateDataService operateDataService;

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
                if (boolOneEquals(productInfoDto.getRegisterAddress(), productMatchDto.getRegisterAddress().getCode()) && StringUtils.isEmpty(productMatchDto.getRegisterAddress().getValue())) {
                    matchingRate += everyOne;
                }
                //融资阶段
                if (boolOneEquals(productInfoDto.getFinanceState(), productMatchDto.getFinanceState().getCode()) && StringUtils.isEmpty(productMatchDto.getFinanceState().getValue())) {
                    matchingRate += everyOne;
                }
                //融资额度
                if (boolOneEquals(productInfoDto.getFinanceQuota(), productMatchDto.getFinanceQuota())) {
                    matchingRate += everyOne;
                }
                //行业方向
                if (boolOneEquals(productInfoDto.getIndustryDirect(), productMatchDto.getIndustryDirect().getCode()) && StringUtils.isEmpty(productMatchDto.getIndustryDirect().getValue())) {
                    matchingRate += everyOne;
                }
                //股东背景
                if (boolOneEquals(productInfoDto.getShareholder(), productMatchDto.getShareholder().getCode()) && StringUtils.isEmpty(productMatchDto.getShareholder().getValue())) {
                    matchingRate += everyOne;
                }
                //营业收入
                if (boolOneEquals(productInfoDto.getBusiness(), productMatchDto.getBusiness())) {
                    matchingRate += everyOne;
                }
                //产品阶段
                if (boolOneEquals(productInfoDto.getProductState(), productMatchDto.getProductState().getCode()) && StringUtils.isEmpty(productMatchDto.getProductState().getValue())) {
                    matchingRate += everyOne;
                }
                //营业收入增长率
                if (boolOneEquals(productInfoDto.getBusinessAddRate(), productMatchDto.getBusinessAddRate())) {
                    matchingRate += everyOne;
                }
                //产品毛利率/预期产品毛利率
                if (boolOneEquals(productInfoDto.getProductRate(), productMatchDto.getProductRate())) {
                    matchingRate += everyOne;
                }
                //净利率
                if (boolOneEquals(productInfoDto.getNetInterestRate(), productMatchDto.getNetInterestRate())) {
                    matchingRate += everyOne;
                }
                //过往融资金额
                if (boolOneEquals(productInfoDto.getOldFinanceQuota(), productMatchDto.getOldFinanceQuota())) {
                    matchingRate += everyOne;
                }
                //实际控制人创业经历
                if (boolOneEquals(productInfoDto.getExperience(), productMatchDto.getExperience())) {
                    matchingRate += everyOne;
                }
                //员工人数
                if (boolOneEquals(productInfoDto.getStaffCount(), productMatchDto.getStaffCount())) {
                    matchingRate += everyOne;
                }
                //目前市场容量
                if (boolOneEquals(productInfoDto.getMarketCapacity(), productMatchDto.getMarketCapacity())) {
                    matchingRate += everyOne;
                }
                //目标市场增长率
                if (boolOneEquals(productInfoDto.getMarketAddRate(), productMatchDto.getMarketAddRate())) {
                    matchingRate += everyOne;
                }
                //目标客户
                //TODO 匹配规则不清楚，不确定一个匹配上算成功还是全部匹配上，也不确定是多个节点还是一个节点
                if (boolListEquals(productInfoDto.getTargetCustomer(), productMatchDto.getTargetCustomer())) {
                    matchingRate += everyOne;
                }
                /*List<Node> nodes1 = productMatchDto.getTargetCustomer();
                if (!CollectionUtils.isEmpty(nodes1)) {
                    for (Node node : nodes1) {
                        if (boolEquals(productInfoDto.getTargetCustomer(), node.getCode()) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }*/
                //市场占有率/预期市场占有率
                if (boolOneEquals(productInfoDto.getMarketOccupyRate(), productMatchDto.getMarketOccupyRate())) {
                    matchingRate += everyOne;
                }
                //是否接收回购
                if (boolEquals(productInfoDto.getBoolBuyBack(), productMatchDto.getBoolBuyBack())) {
                    matchingRate += everyOne;
                }
                //发明专利数量
                if (boolOneEquals(productInfoDto.getPatentCount(), productMatchDto.getPatentCount())) {
                    matchingRate += everyOne;
                }
                //公司竞争优势 productInfoDto.getAdvantage() = {"code":"1","value":"1"}  node = {"code":"1","value":"1"}
                //TODO 匹配规则不清楚，不确定一个匹配上算成功还是全部匹配上，也不确定是多个节点还是一个节点
                if (boolListEquals(productInfoDto.getAdvantage(), productMatchDto.getAdvantage())) {
                    matchingRate += everyOne;
                }
                /*List<Node> nodes = productMatchDto.getAdvantage();
                if (!CollectionUtils.isEmpty(nodes)) {
                    for (Node node : nodes) {
                        if (productInfoDto.getAdvantage().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }*/
                //股东累计投入资金
                if (boolOneEquals(productInfoDto.getCapitals(), productMatchDto.getCapitals())) {
                    matchingRate += everyOne;
                }
                //公司所获评定名称
                //TODO 匹配规则不清楚，不确定一个匹配上算成功还是全部匹配上，也不确定是多个节点还是一个节点
                /*List<Node> nodes2 = productMatchDto.getEvaluateName();
                if (!CollectionUtils.isEmpty(nodes2)) {
                    for (Node node : nodes2) {
                        if (productInfoDto.getEvaluateName().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }*/
                if (boolListEquals(productInfoDto.getEvaluateName(), productMatchDto.getEvaluateName())) {
                    matchingRate += everyOne;
                }
                //预计上市时间
                if (boolOneEquals(productInfoDto.getTimeToMarket(), productMatchDto.getTimeToMarket())) {
                    matchingRate += everyOne;
                }
                //历史创业企业状态
                if (boolOneEquals(productInfoDto.getCompanyStatus(), productMatchDto.getCompanyStatus())) {
                    matchingRate += everyOne;
                }
                if (matchingRate > 50) {
                    matchingProducts.add(productInfoDto.getProductId().toString());
                }
            }
        }
        return matchingProducts;
    }

    public Boolean boolEquals(String backColunm, String frontColunm) {
        if (!StringUtils.isEmpty(backColunm) && !StringUtils.isEmpty(frontColunm)) {
            return backColunm.equals(frontColunm) ? true : false;
        }
        return false;
    }

    public Boolean boolOneEquals(String backColunm, String frontColunm) {
        if (!StringUtils.isEmpty(backColunm) && !StringUtils.isEmpty(frontColunm)) {
            List<Node> backNode = JSON.parseArray(backColunm, Node.class);
            if (!CollectionUtils.isEmpty(backNode)) {
                for (Node node : backNode) {
                    if (node.getCode().equals(frontColunm) && StringUtils.isEmpty(node.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boolListEquals(String backColunm, List<Node> frontColunm) {
        if (!StringUtils.isEmpty(backColunm) && !CollectionUtils.isEmpty(frontColunm)) {
            List<Node> backNode = JSON.parseArray(backColunm, Node.class);
            for (Node node : backNode) {
                for (Node node1 : frontColunm) {
                    if (node.getCode().equals(node1.getCode()) && StringUtils.isEmpty(node.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 保存股权融资匹配信息
     * @param stockProductMatchDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStockOneKeyMatching(StockProductMatchDto stockProductMatchDto) {
        UserInfoDto userInfoDto = UserUtils.getUser();
        BnException.of(userInfoDto == null, "用户信息获取失败！");

        //获取公司信息并更新
        UserInfo userInfo = userInfoService.getById(userInfoDto.getId());
        Company company = companyService.getById(userInfo.getCompanyId());

        //保存股权融资匹配信息
        FinanceStockDetail financeStockDetail = FinanceStockDetail.builder()
                .financeState(getObjectToJSONStr(stockProductMatchDto.getFinanceState()))
                .financeQuota(stockProductMatchDto.getFinanceQuota())
                .industryDirect(getObjectToJSONStr(stockProductMatchDto.getIndustryDirect()))
                .shareholder(getObjectToJSONStr(stockProductMatchDto.getShareholder()))
                .business(stockProductMatchDto.getBusiness())
                .productState(getObjectToJSONStr(stockProductMatchDto.getProductState()))
                .businessAddRate(stockProductMatchDto.getBusinessAddRate())
                .productRate(stockProductMatchDto.getProductRate())
                .netInterestRate(stockProductMatchDto.getNetInterestRate())
                .oldFinanceQuota(stockProductMatchDto.getOldFinanceQuota())
                .experience(stockProductMatchDto.getExperience())
                .staffCount(stockProductMatchDto.getStaffCount())
                .marketCapacity(stockProductMatchDto.getMarketCapacity())
                .marketAddRate(stockProductMatchDto.getMarketAddRate())
                .targetCustomer(getObjectToJSONStr(stockProductMatchDto.getTargetCustomer()))
                .marketOccupyRate(stockProductMatchDto.getMarketOccupyRate())
                .boolBuyBack(stockProductMatchDto.getBoolBuyBack())
                .patentCount(stockProductMatchDto.getPatentCount())
                .advantage(getObjectToJSONStr(stockProductMatchDto.getAdvantage()))
                .capitals(stockProductMatchDto.getCapitals())
                .evaluateName(getObjectToJSONStr(stockProductMatchDto.getEvaluateName()))
                //.timeToMarket(stockProductMatchDto.getTimeToMarket())
                //.companyStatus(stockProductMatchDto.getCompanyStatus())
                .build();
        financeStockDetailService.save(financeStockDetail);

        //保存一键匹配的信息
        FinanceStockMatch financeStockMatch = FinanceStockMatch.builder()
                .companyId(company.getId())
                .detailId(financeStockDetail.getId())
                .companyStatus(stockProductMatchDto.getCompanyStatus())
                .timeToMarket(stockProductMatchDto.getTimeToMarket())
                .build();
        this.save(financeStockMatch);

        //保存一键匹配json信息
        operateDataService.saveOrUpdateMatchData(stockProductMatchDto.getOperateMatchDto());

        //更新公司信息
        company.setRegisterAddress(stockProductMatchDto.getRegisterAddress().getValue());
        company.setWorkAddress(stockProductMatchDto.getWorkAddress());
        company.setContact(stockProductMatchDto.getContact());
        company.setTel(stockProductMatchDto.getTel());
        company.setIntroduce(stockProductMatchDto.getIntroduce());
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

    public String getJSONStr(List<Node> nodes) {
        if (!CollectionUtils.isEmpty(nodes)) {
            List<String> codes = nodes.stream().map(node -> getCode(node.getCode(), node.getValue())).collect(Collectors.toList());
            return String.join(",", codes);
        }
        return "";
    }

    /**
     * 获取企业的一键匹配信息
     * @param companyId
     * @return java.util.List<com.july.company.entity.FinanceStockMatch>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public List<FinanceStockMatch> getFinanceStockMatch(Long companyId) {
        QueryWrapper<FinanceStockMatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(companyId != null, "companyId", companyId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.list(queryWrapper);
    }

}
