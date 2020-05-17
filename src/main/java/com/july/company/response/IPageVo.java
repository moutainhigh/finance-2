package com.july.company.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * 分页信息
 * @author zengxueqi
 * @since 2020/05/16
 */
public interface IPageVo<T> extends Serializable {

    /**
     * 降序字段数组
     * @return order by desc 的字段数组
     */
    default String[] descs() {
        return null;
    }

    /**
     * 升序字段数组
     * @return order by asc 的字段数组
     */
    default String[] ascs() {
        return null;
    }

    /**
     * KEY/VALUE 条件
     * @return ignore
     */
    default Map<Object, Object> condition() {
        return null;
    }

    /**
     * 自动优化 COUNT SQL【 默认：true 】
     * @return true 是 / false 否
     */
    default boolean optimizeCountSql() {
        return true;
    }

    /**
     * 进行 count 查询 【 默认: true 】
     * @return true 是 / false 否
     */
    default boolean isSearchCount() {
        return true;
    }

    /**
     * 计算当前分页偏移量
     * @return 偏移量
     */
    default long offset() {
        return getCurrent() > 0 ? (getCurrent() - 1) * getSize() : 0;
    }

    /**
     * 当前分页总页数
     * @return 总页数
     */
    @JsonIgnore
    @JSONField(serialize = false)
    default long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    /**
     * 内部什么也不干
     * <p>只是为了 json 反序列化时不报错</p>
     * @param pages 分页参数
     * @return 分页结果
     */
    default IPageVo<T> setPages(long pages) {
        return this;
    }

    /**
     * 分页记录列表
     * @return 分页对象记录列表
     */
    List<T> getRecords();

    /**
     * 设置分页记录列表
     * @param records 记录
     * @return 分页结果
     */
    IPageVo<T> setRecords(List<T> records);

    /**
     * 当前满足条件总行数
     * @return 总条数
     */
    long getTotal();

    /**
     * 设置当前满足条件总行数
     * @param total 总记录数
     * @return 分页结果
     */
    IPageVo<T> setTotal(long total);

    /**
     * 当前分页总页数
     * @return 总页数
     */
    long getSize();

    /**
     * 设置当前每页大小
     * @param size 分页大小
     * @return 分页结果
     */
    IPageVo<T> setSize(long size);

    /**
     * 当前页，默认 1
     * @return 当然页
     */
    long getCurrent();

    /**
     * 设置当前页
     * @param current 当前页
     * @return 分页结果
     */
    IPageVo<T> setCurrent(long current);

    /**
     * IPage 的泛型转换
     * @param mapper 转换函数
     * @param <R>    转换后的泛型
     * @return 转换泛型后的 IPage
     */
    @SuppressWarnings("unchecked")
    default <R> IPageVo<R> convert(Function<? super T, ? extends R> mapper) {
        List<R> collect = this.getRecords().stream().map(mapper).collect(toList());
        return ((IPageVo<R>) this).setRecords(collect);
    }

}