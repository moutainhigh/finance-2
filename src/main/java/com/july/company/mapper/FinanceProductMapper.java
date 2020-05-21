package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.finance.FinanceProductDto;
import com.july.company.entity.FinanceProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.finance.FinanceProductVo;
import org.apache.ibatis.annotations.Param;

/**
 * 产品信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-20
 */
public interface FinanceProductMapper extends BaseMapper<FinanceProduct> {

    /**
     * 获取产品列表信息
     * @param page
     * @param stockCompanyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.stock.StockCompanyVo>
     * @author zengxueqi
     * @since 2020/5/17
     */
    IPage<FinanceProductVo> getFinanceProduct(Page<FinanceProductVo> page, @Param("param") FinanceProductDto stockCompanyDto);

}
