package com.july.company.service;

import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.entity.FinanceBondMatch;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 债券融资一键匹配 服务类
 * @author zengxueqi
 * @since 2020-06-08
 */
public interface FinanceBondMatchService extends IService<FinanceBondMatch> {

    /**
     * 一键匹配债券产品信息
     * @param bondProductMatchDto
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/25
     */
    String getBondOneKeyMatching(BondProductMatchDto bondProductMatchDto);

    /**
     * 一键匹配保存债券产品信息
     * @author xiajunwei
     * @since 2020/6/9
     */
    void saveBondOneKeyMatching(BondProductMatchDto bondProductMatchDto);

    /**
     * 获取企业的一键匹配信息
     * @param companyId
     * @return java.util.List<com.july.company.entity.FinanceBondMatch>
     * @author zengxueqi
     * @since 2020/6/10
     */
    List<FinanceBondMatch> getFinanceBondMatch(Long companyId);

}
