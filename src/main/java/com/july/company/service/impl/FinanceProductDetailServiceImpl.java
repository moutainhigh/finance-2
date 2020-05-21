package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.entity.FinanceProductDetail;
import com.july.company.mapper.FinanceProductDetailMapper;
import com.july.company.service.FinanceProductDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 融资公司明细信息 服务实现类
 * @author zengxueqi
 * @since 2020-05-20
 */
@Service
public class FinanceProductDetailServiceImpl extends ServiceImpl<FinanceProductDetailMapper, FinanceProductDetail> implements FinanceProductDetailService {

    /**
     * 获取产品的明细信息
     * @param productId
     * @return com.july.company.entity.FinanceProductDetail
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public FinanceProductDetail getFinanceProductDetail(Long productId) {
        QueryWrapper<FinanceProductDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(productId != null, "productId", productId)
                .eq("deleted", SystemConstant.SYS_FALSE);
        return this.getOne(queryWrapper);
    }
}
