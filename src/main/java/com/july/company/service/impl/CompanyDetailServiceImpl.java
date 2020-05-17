package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.entity.CompanyDetail;
import com.july.company.mapper.CompanyDetailMapper;
import com.july.company.service.CompanyDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 融资公司明细信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-17
 */
@Service
public class CompanyDetailServiceImpl extends ServiceImpl<CompanyDetailMapper, CompanyDetail> implements CompanyDetailService {

    /**
     * 获取融资公司的明细信息
     * @param companyId
     * @return com.july.company.entity.CompanyDetail
     * @author zengxueqi
     * @since 2020/5/17
     */
    @Override
    public CompanyDetail getCompanyDetail(Long companyId) {
        QueryWrapper<CompanyDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(companyId != null, "companyId", companyId)
                .eq("deleted", SystemConstant.SYS_TRUE);
        return this.getOne(queryWrapper);
    }

}
