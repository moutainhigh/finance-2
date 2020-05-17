package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.stock.StockCompanyDetailDto;
import com.july.company.dto.stock.StockCompanyDto;
import com.july.company.entity.FinanceCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.stock.StockCompanyVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 融资公司信息 Mapper 接口
 * </p>
 *
 * @author zengxueqi
 * @since 2020-05-17
 */
public interface FinanceCompanyMapper extends BaseMapper<FinanceCompany> {

    /**
     * 获取股权融资公司列表信息
     * @param page
     * @param stockCompanyDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.stock.StockCompanyVo>
     * @author zengxueqi
     * @since 2020/5/17
     */
    IPage<StockCompanyVo> getFinanceCompany(Page<StockCompanyVo> page, @Param("param") StockCompanyDto stockCompanyDto);

}
