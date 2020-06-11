package com.july.company.service;

import com.july.company.dto.apply.ProductApplyDto;
import com.july.company.dto.apply.ProductCommitDto;
import com.july.company.entity.FinanceApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 企业申请产品信息 服务类
 * @author zengxueqi
 * @since 2020-06-10
 */
public interface FinanceApplyService extends IService<FinanceApply> {

    /**
     * 保存企业申请的产品信息
     * @param productApplyDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    void saveProductApply(ProductApplyDto productApplyDto);

    /**
     * 保存企业提交的产品信息
     * @param productCommitDto
     * @return void
     * @author zengxueqi
     * @since 2020/6/10
     */
    void commitProductApply(ProductCommitDto productCommitDto);

}
