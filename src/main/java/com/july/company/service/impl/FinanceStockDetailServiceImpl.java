package com.july.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dictionary.DictInit;
import com.july.company.dto.Node;
import com.july.company.dto.finance.FinanceProductDetailDto;
import com.july.company.entity.FinanceProduct;
import com.july.company.entity.FinanceStockDetail;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceStockDetailMapper;
import com.july.company.service.FinanceProductService;
import com.july.company.service.FinanceStockDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.StringUtils;
import com.july.company.vo.finance.FinanceStockProductDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 融资公司明细信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-26
 */
@Slf4j
@Service
public class FinanceStockDetailServiceImpl extends ServiceImpl<FinanceStockDetailMapper, FinanceStockDetail> implements FinanceStockDetailService {

    @Resource
    private FinanceProductService financeProductService;
    @Resource
    private FinanceStockDetailMapper financeStockDetailMapper;

    /**
     * 获取股权融资产品明细信息
     * @param financeProductDetailDto
     * @return com.july.company.vo.finance.FinanceProductDetailVo
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public FinanceStockProductDetailVo getFinanceStockProductDetail(FinanceProductDetailDto financeProductDetailDto) {
        BnException.of(financeProductDetailDto.getCompanyId() == null, "股权融资公司id不能为空！");

        FinanceProduct financeProduct = financeProductService.getById(financeProductDetailDto.getCompanyId());
        FinanceStockDetail financeProductDetail = getFinanceProductDetail(financeProduct.getId());

        String workAddress = org.springframework.util.StringUtils.isEmpty(DictInit.getCodeValue(SystemConstant.REGION, financeProduct.getWorkAddress())) ? "" :
                DictInit.getCodeValue(SystemConstant.REGION, financeProduct.getWorkAddress());
        workAddress = org.springframework.util.StringUtils.isEmpty(financeProduct.getDetailAddress()) ? workAddress : workAddress + financeProduct.getDetailAddress();

        return FinanceStockProductDetailVo.builder()
                .productId(financeProductDetail.getProductId())
                .mechanism(financeProduct.getMechanism())
                .workAddress(workAddress)
                .tel(financeProduct.getTel())
                .introduce(financeProduct.getIntroduce())
                .registerAddressStr(getListColunmNode(SystemConstant.REGION, financeProduct.getRegisterAddress() + ""))
                .financeStateStr(getListColunmNode(SystemConstant.RZJD, financeProductDetail.getFinanceState()))
                .financeQuotaStr(getListColunmNode(SystemConstant.RZED, financeProductDetail.getFinanceQuota() + ""))
                .industryDirectStr(getListColunmNode(SystemConstant.HYFX, financeProductDetail.getIndustryDirect()))
                .shareholderStr(getListColunmNode(SystemConstant.GDBJ, financeProductDetail.getShareholder() + ""))
                .productStateStr(getListColunmNode(SystemConstant.CPJD, financeProductDetail.getProductState() + ""))
                .businessStr(getListColunmNode(SystemConstant.STOCKRIGHT_YYSR, financeProductDetail.getBusiness() + ""))
                .businessAddRateStr(getListColunmNode(SystemConstant.YYSRZZL, financeProductDetail.getBusinessAddRate() + ""))
                .productRateStr(getListColunmNode(SystemConstant.CPMLL, financeProductDetail.getProductRate() + ""))
                .netInterestRateStr(getListColunmNode(SystemConstant.JLL, financeProductDetail.getNetInterestRate() + ""))
                .oldFinanceQuotaStr(getListColunmNode(SystemConstant.GWRZJE, financeProductDetail.getOldFinanceQuota() + ""))
                .experienceStr(getListColunmNode(SystemConstant.CYJL, financeProductDetail.getExperience() + ""))
                .staffCountStr(getListColunmNode(SystemConstant.YGRS, financeProductDetail.getStaffCount() + ""))
                .marketCapacityStr(getListColunmNode(SystemConstant.SCRL, financeProductDetail.getMarketCapacity() + ""))
                .marketAddRateStr(getListColunmNode(SystemConstant.SCRLZZL, financeProductDetail.getMarketAddRate() + ""))
                .targetCustomerStr(getListColunmNode(SystemConstant.MBKH, financeProductDetail.getTargetCustomer() + ""))
                .marketOccupyRateStr(getListColunmNode(SystemConstant.SCZYL, financeProductDetail.getMarketOccupyRate() + ""))
                .boolBuyBackStr(DictInit.getCodeValue(SystemConstant.SFHG, financeProductDetail.getBoolBuyBack() + ""))
                .patentCountStr(getListColunmNode(SystemConstant.STOCKRIGHT_FMZLSL, financeProductDetail.getPatentCount() + ""))
                .advantageStr(getListColunmNode(SystemConstant.GSJZYS, financeProductDetail.getAdvantage() + ""))
                .capitalsStr(getListColunmNode(SystemConstant.GDLJTRZJ, financeProductDetail.getCapitals() + ""))
                .evaluateNameStr(getListColunmNode(SystemConstant.PDCH, financeProductDetail.getEvaluateName() + ""))
                .build();
    }

    public String getColunmNode(String codeTypo, String colunm) {
        if (!StringUtils.isEmpty(colunm)) {
            Node node = JSONObject.parseObject(colunm, Node.class);
            String code = node.getCode();
            String value = node.getValue();
            if (StringUtils.isEmpty(value)) {
                return DictInit.getCodeValue(codeTypo, code);
            } else {
                return value;
            }
        }
        return null;
    }

    public String getListColunmNode(String codeTypo, String colunm) {
        if (!StringUtils.isEmpty(colunm)) {
            List<Node> nodes = JSONObject.parseArray(colunm, Node.class);
            if (!CollectionUtils.isEmpty(nodes)) {
                List<String> colunms = nodes.stream().map(node -> {
                    if (StringUtils.isEmpty(node.getValue())) {
                        return DictInit.getCodeValue(codeTypo, node.getCode());
                    } else {
                        return node.getValue();
                    }
                }).collect(Collectors.toList());
                return String.join(",", colunms);
            }
        }
        return null;
    }

    /**
     * 获取股权产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    public FinanceStockDetail getFinanceProductDetail(Long productId) {
        QueryWrapper<FinanceStockDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(productId != null, "productId", productId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }

    /**
     * 通过产品ID串获取股权产品的明细信息列表
     * @param stockStr
     * @return com.july.company.entity.FinanceProductDetail
     * @author xiajunwei
     * @since 2020/6/15
     */
    @Override
    public List<FinanceStockDetail> getStockDetailByProductIds(String stockStr) {
        List<FinanceStockDetail> financeStockDetailList = financeStockDetailMapper.getStockDetailByProductIds(stockStr);
        return financeStockDetailList;
    }

}
