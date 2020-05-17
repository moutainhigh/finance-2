package com.july.company.response;

import lombok.Data;

/**
 * 分页参数信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:46
 **/
@Data
public class PageParamVo<T> {

    /**
     * 作为参数的业务数据
     * content或List
     */
    private T content;
    /**
     * 分页相关参数
     */
    private PageVo<T> pager;

    /**
     * 用于接收list属性对应的对象
     * @param list 列表
     */
    public void setList(T list) {
        this.content = list;
    }

    /**
     * 当前页 current = page = pageNo = currentPage
     * @param page 页号
     */
    public void setPage(Long page) {
        if (this.pager != null) {
            this.pager.setPage(page);
        }
    }

    /**
     * 每页记录数size  = limt = limit = pageSize
     * @param limit 每页记录数
     */
    public void setLimit(Long limit) {
        if (this.pager != null) {
            this.pager.setLimt(limit);
        }
    }

    /**
     * 每页记录数size  = limt = limit = pageSize
     * @param limt 每页记录数
     */
    public void setLimt(Long limt) {
        if (this.pager != null) {
            this.pager.setLimt(limt);
        }
    }

}
