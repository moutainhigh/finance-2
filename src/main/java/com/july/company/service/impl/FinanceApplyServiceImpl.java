package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.apply.*;
import com.july.company.entity.FinanceApply;
import com.july.company.entity.UserInfo;
import com.july.company.entity.enums.ApplyStatusEnum;
import com.july.company.entity.enums.FinanceTypeEnum;
import com.july.company.exception.BnException;
import com.july.company.mapper.FinanceApplyMapper;
import com.july.company.service.FinanceApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.service.FinanceBondMatchService;
import com.july.company.service.FinanceStockMatchService;
import com.july.company.service.UserInfoService;
import com.july.company.utils.DateUtils;
import com.july.company.vo.apply.ProductDetalVo;
import com.july.company.vo.apply.SelectProductVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private FinanceApplyMapper financeApplyMapper;

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

    /**
     * 获取产品申请信息（后台）
     * @param page
     * @param selectProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.apply.SelectProductVo>
     * @author zengxueqi
     * @since 2020/6/12
     */
    @Override
    public IPage<SelectProductVo> getFinanceApply(Page<SelectProductVo> page, SelectProductDto selectProductDto) {
        IPage<SelectProductVo> selectProductVoIPage = financeApplyMapper.getFinanceApply(page, selectProductDto);
        List<SelectProductVo> selectProductVoList = selectProductVoIPage.getRecords();

        //数据类型转换
        List<SelectProductVo> selectProductVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(selectProductVoList)) {
            selectProductVos = selectProductVoList.stream().map(selectProductVo -> {
                selectProductVo.setFinanceTypeStr(FinanceTypeEnum.getDescByValue(selectProductVo.getFinanceType()));
                selectProductVo.setCreatedTimeStr(DateUtils.timeStamp2Date(selectProductVo.getCreatedTime()));
                return selectProductVo;
            }).collect(Collectors.toList());
        }
        selectProductVoIPage.setRecords(selectProductVos);
        return selectProductVoIPage;
    }

    /**
     * 申请产品操作
     * @param productOperateDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/12
     */
    @Override
    public void applyProductOperate(ProductOperateDto productOperateDto) {
        FinanceApply financeApply = this.getById(productOperateDto.getApplyId());
        BnException.of(financeApply == null, "没有找到该产品申请信息！");

        if (ApplyStatusEnum.PASS.getValue().equals(productOperateDto.getOperateType())) {
            financeApply.setStatus(ApplyStatusEnum.PASS.getValue());
        } else if (ApplyStatusEnum.REJECT.getValue().equals(productOperateDto.getOperateType())) {
            financeApply.setStatus(ApplyStatusEnum.REJECT.getValue());
            financeApply.setRemark(productOperateDto.getRemark());
        }
        this.updateById(financeApply);
    }

    /**
     * 获取申请产品详细信息
     * @param productDetailDto
     * @return com.july.company.vo.apply.ProductDetalVo
     * @author zengxueqi
     * @since 2020/6/12
     */
    @Override
    public ProductDetalVo getApplyInfo(ProductDetailDto productDetailDto) {
        BnException.of(productDetailDto.getApplyId() == null, "申请产品id不能为空！");
        ProductDetalVo productDetalVo = financeApplyMapper.getApplyInfo(productDetailDto.getApplyId());
        productDetalVo.setCreatedTimeStr(DateUtils.timeStamp2Date(productDetalVo.getCreatedTime()));
        productDetalVo.setFinanceTypeStr(FinanceTypeEnum.getDescByValue(productDetalVo.getFinanceType()));
        productDetalVo.setStatusStr(ApplyStatusEnum.getDescByValue(productDetalVo.getStatus()));
        return productDetalVo;
    }

}
