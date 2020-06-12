package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.dto.apply.ProductApplyDto;
import com.july.company.dto.apply.ProductCommitDto;
import com.july.company.entity.FinanceApply;
import com.july.company.entity.UserInfo;
import com.july.company.entity.enums.FinanceTypeEnum;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceApplyMapper;
import com.july.company.service.FinanceApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.FinanceBondMatchService;
import com.july.company.service.FinanceStockMatchService;
import com.july.company.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 企业申请产品信息 服务实现类
 * @author zengxueqi
 * @since 2020-06-10
 */
@Service
public class FinanceApplyServiceImpl extends ServiceImpl<FinanceApplyMapper, FinanceApply> implements FinanceApplyService {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FinanceBondMatchService financeBondMatchService;
    @Resource
    private FinanceStockMatchService financeStockMatchService;

    /**
     * 保存企业申请的产品信息
     * @param productApplyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProductApply(ProductApplyDto productApplyDto) {
        //获取企业id
        UserInfo userInfo = userInfoService.getById(productApplyDto.getUserId());
        BnException.of(userInfo == null, "无法获取到企业信息！");

        Boolean boolApply = boolApply(userInfo.getCompanyId(), productApplyDto.getProductId());
        BnException.of(boolApply, "该产品已经申请过，请不要重复申请！");

        FinanceApply financeApply = FinanceApply.builder()
                .companyId(userInfo.getCompanyId())
                .productId(productApplyDto.getProductId())
                .build();
        this.save(financeApply);
    }

    /**
     * 判断这个产品企业是否已经申请过
     * @param companyId
     * @param productId
     * @return java.lang.Boolean
     * @author zengxueqi
     * @since 2020/6/10
     */
    public Boolean boolApply(Long companyId, Long productId) {
        QueryWrapper<FinanceApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("companyId", companyId)
                .eq("productId", productId);
        List<FinanceApply> financeApplies = this.list(queryWrapper);
        return !CollectionUtils.isEmpty(financeApplies) ? true : false;
    }

    /**
     * 保存企业提交的产品信息
     * @param productCommitDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    @Override
    public void commitProductApply(ProductCommitDto productCommitDto) {
        //保存一键匹配数据
        if (FinanceTypeEnum.STOCKRIGHT.equals(productCommitDto.getFinanceType())) {
            financeStockMatchService.saveStockOneKeyMatching(productCommitDto.getStockProductMatchDto());
        } else {
            financeBondMatchService.saveBondOneKeyMatching(productCommitDto.getBondProductMatchDto());
        }

        //保存产品申请信息
        ProductApplyDto productApplyDto = ProductApplyDto.builder()
                .productId(productCommitDto.getProductId())
                .userId(productCommitDto.getUserId())
                .build();
        saveProductApply(productApplyDto);
    }

}
