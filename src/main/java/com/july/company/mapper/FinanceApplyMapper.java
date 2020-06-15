package com.july.company.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.july.company.dto.apply.ProductDetailDto;
import com.july.company.dto.apply.SelectProductDto;
import com.july.company.entity.FinanceApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.apply.ProductDetalVo;
import com.july.company.vo.apply.SelectProductVo;
import org.apache.ibatis.annotations.Param;

/**
 * 企业申请产品信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-06-10
 */
public interface FinanceApplyMapper extends BaseMapper<FinanceApply> {

    /**
     * 获取产品申请信息
     * @param page
     * @param selectProductDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.july.company.vo.apply.SelectProductVo>
     * @author zengxueqi
     * @since 2020/6/12
     */
    IPage<SelectProductVo> getFinanceApply(Page<SelectProductVo> page, @Param("param") SelectProductDto selectProductDto);

    /**
     * 获取申请产品信息
     * @param applyId
     * @return com.july.company.vo.apply.ProductDetalVo
     * @author zengxueqi
     * @since 2020/6/12
     */
    ProductDetalVo getApplyInfo(Long applyId);

}
