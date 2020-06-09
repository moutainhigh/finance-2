package com.july.company.service.impl;

import com.july.company.dto.Node;
import com.july.company.dto.finance.StockProductInfoDto;
import com.july.company.dto.finance.StockProductMatchDto;
import com.july.company.dto.user.UserInfoDto;
import com.july.company.entity.Company;
import com.july.company.entity.FinanceStockDetail;
import com.july.company.entity.FinanceStockMatch;
import com.july.company.entity.UserInfo;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceProductMapper;
import com.july.company.mapper.FinanceStockMatchMapper;
import com.july.company.service.CompanyService;
import com.july.company.service.FinanceStockMatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.UserInfoService;
import com.july.company.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
                if (productInfoDto.getRegisterAddress().equals(productMatchDto.getRegisterAddress().getCode()) && StringUtils.isEmpty(productMatchDto.getRegisterAddress().getValue())) {
                    matchingRate += everyOne;
                }
                //融资阶段
                if (productInfoDto.getFinanceState().equals(productMatchDto.getFinanceState().getCode()) && StringUtils.isEmpty(productMatchDto.getFinanceState().getValue())) {
                    matchingRate += everyOne;
                }
                //融资额度
                if (productInfoDto.getFinanceQuota().equals(productMatchDto.getFinanceQuota())) {
                    matchingRate += everyOne;
                }
                //行业方向
                if (productInfoDto.getIndustryDirect().equals(productMatchDto.getIndustryDirect().getCode()) && StringUtils.isEmpty(productMatchDto.getIndustryDirect().getValue())) {
                    matchingRate += everyOne;
                }
                //股东背景
                if (productInfoDto.getShareholder().equals(productMatchDto.getShareholder().getCode()) && StringUtils.isEmpty(productMatchDto.getShareholder().getValue())) {
                    matchingRate += everyOne;
                }
                //营业收入
                if (productInfoDto.getBusiness().equals(productMatchDto.getBusiness())) {
                    matchingRate += everyOne;
                }
                //产品阶段
                if (productInfoDto.getProductState().equals(productMatchDto.getProductState().getCode()) && StringUtils.isEmpty(productMatchDto.getProductState().getValue())) {
                    matchingRate += everyOne;
                }
                //营业收入增长率
                if (productInfoDto.getBusinessAddRate().equals(productMatchDto.getBusinessAddRate())) {
                    matchingRate += everyOne;
                }
                //产品毛利率/预期产品毛利率
                if (productInfoDto.getProductRate().equals(productMatchDto.getProductRate())) {
                    matchingRate += everyOne;
                }
                //净利率
                if (productInfoDto.getNetInterestRate().equals(productMatchDto.getNetInterestRate())) {
                    matchingRate += everyOne;
                }
                //过往融资金额
                if (productInfoDto.getOldFinanceQuota().equals(productMatchDto.getOldFinanceQuota())) {
                    matchingRate += everyOne;
                }
                //实际控制人创业经历
                if (productInfoDto.getExperience().equals(productMatchDto.getExperience())) {
                    matchingRate += everyOne;
                }
                //员工人数
                if (productInfoDto.getStaffCount().equals(productMatchDto.getStaffCount())) {
                    matchingRate += everyOne;
                }
                //目前市场容量
                if (productInfoDto.getMarketCapacity().equals(productMatchDto.getMarketCapacity())) {
                    matchingRate += everyOne;
                }
                //目标市场增长率
                if (productInfoDto.getMarketAddRate().equals(productMatchDto.getMarketAddRate())) {
                    matchingRate += everyOne;
                }
                //目标客户
                //TODO 匹配规则不清楚，不确定一个匹配上算成功还是全部匹配上，也不确定是多个节点还是一个节点
                List<Node> nodes1 = productMatchDto.getTargetCustomer();
                if (!CollectionUtils.isEmpty(nodes1)) {
                    for (Node node : nodes1) {
                        if (productInfoDto.getTargetCustomer().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //市场占有率/预期市场占有率
                if (productInfoDto.getMarketOccupyRate().equals(productMatchDto.getMarketOccupyRate())) {
                    matchingRate += everyOne;
                }
                //是否接收回购
                if (productInfoDto.getBoolBuyBack().equals(productMatchDto.getBoolBuyBack())) {
                    matchingRate += everyOne;
                }
                //发明专利数量
                if (productInfoDto.getPatentCount().equals(productMatchDto.getPatentCount())) {
                    matchingRate += everyOne;
                }
                //公司竞争优势 productInfoDto.getAdvantage() = {"code":"1","value":"1"}  node = {"code":"1","value":"1"}
                //TODO 匹配规则不清楚，不确定一个匹配上算成功还是全部匹配上，也不确定是多个节点还是一个节点
                List<Node> nodes = productMatchDto.getAdvantage();
                if (!CollectionUtils.isEmpty(nodes)) {
                    for (Node node : nodes) {
                        if (productInfoDto.getAdvantage().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //股东累计投入资金
                if (productInfoDto.getCapitals().equals(productMatchDto.getCapitals())) {
                    matchingRate += everyOne;
                }
                //公司所获评定名称
                //TODO 匹配规则不清楚，不确定一个匹配上算成功还是全部匹配上，也不确定是多个节点还是一个节点
                List<Node> nodes2 = productMatchDto.getEvaluateName();
                if (!CollectionUtils.isEmpty(nodes2)) {
                    for (Node node : nodes2) {
                        if (productInfoDto.getEvaluateName().equals(node) && StringUtils.isEmpty(node.getValue())) {
                            matchingRate += everyOne;
                            break;
                        }
                    }
                }
                //预计上市时间
                if (productInfoDto.getTimeToMarket().equals(productMatchDto.getTimeToMarket())) {
                    matchingRate += everyOne;
                }
                //历史创业企业状态
                if (productInfoDto.getCompanyStatus().equals(productMatchDto.getCompanyStatus())) {
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
     * 保存股权融资匹配信息
     * @param stockProductMatchDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/9
     */
    public void saveStockOneKeyMatching(StockProductMatchDto stockProductMatchDto) {
        UserInfoDto userInfoDto = UserUtils.getUser();
        BnException.of(userInfoDto == null, "用户信息获取失败！");

        FinanceStockDetail financeStockDetail = FinanceStockDetail.builder()
                //id,productId,registerAddress,financeState,financeQuota,industryDirect,shareholder,business,productState,businessAddRate,productRate,netInterestRate,oldFinanceQuota,experience,staffCount,marketCapacity,marketAddRate,targetCustomer,marketOccupyRate,boolBuyBack,patentCount,advantage,capitals,evaluateName,timeToMarket,companyStatus,createdTime,updatedTime,deleted
                .build();

        //获取公司信息并更新
        UserInfo userInfo = userInfoService.getById(userInfoDto.getId());
        Company company = companyService.getById(userInfo.getCompanyId());

        //更新公司信息
        company.setRegisterAddress(stockProductMatchDto.getRegisterAddress().getValue());
        company.setWorkAddress(stockProductMatchDto.getWorkAddress());
        company.setContact(stockProductMatchDto.getContact());
        company.setTel(stockProductMatchDto.getTel());
        company.setIntroduce(stockProductMatchDto.getIntroduce());
        companyService.updateById(company);
    }

}
