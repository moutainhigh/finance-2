package com.july.company.base;

import com.july.company.dto.finance.FinanceStockProductDto;
import com.july.company.response.PageParamVo;
import com.july.company.response.PageVo;

/**
 * 分页信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-04 16:35
 **/
public class PageRequestT<T> {

    public PageParamVo<T> getPageRequest(T t, Long pageSize, Long currentPage) {
        PageParamVo<T> pageParamVo = new PageParamVo();

        PageVo<T> productDtoPageVo = new PageVo<>();
        productDtoPageVo.setCurrent(currentPage);
        productDtoPageVo.setSize(pageSize);

        pageParamVo.setContent(t);
        pageParamVo.setPager(productDtoPageVo);
        return pageParamVo;
    }

}
