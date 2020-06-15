package com.july.company.mapper;

import com.july.company.entity.FinanceStockDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 融资公司明细信息 Mapper 接口
 * </p>
 *
 * @author zengxueqi
 * @since 2020-05-26
 */
public interface FinanceStockDetailMapper extends BaseMapper<FinanceStockDetail> {

    /**
     * 通过产品ID串获取股权产品的明细信息列表
     * @param stockStr
     * @return com.july.company.entity.FinanceProductDetail
     * @author xiajunwei
     * @since 2020/6/15
     */
    List<FinanceStockDetail> getStockDetailByProductIds(String stockStr);
}
