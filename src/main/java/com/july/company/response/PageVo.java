package com.july.company.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

/**
 * 分页信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:44
 **/
public class PageVo <T> implements IPageVo<T> {

    private static final long serialVersionUID = 8545996863226528798L;

    /**
     * 查询数据列表
     */
    @JSONField(serialize = false)
    private List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    @JSONField(name = "totalCount")
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    @JSONField(name = "pageSize")
    private long size = 10;
    /**
     * 当前页
     */
    @JSONField(name = "currentPage")
    private long current = 1;
    /**
     * SQL 排序 ASC 数组
     */
    @JSONField(serialize = false)
    private String[] ascs;
    /**
     * SQL 排序 DESC 数组
     */
    @JSONField(serialize = false)
    private String[] descs;
    /**
     * 自动优化 COUNT SQL
     */
    @JSONField(serialize = false)
    private boolean optimizeCountSql = true;
    /**
     * 是否进行 count 查询
     */
    @JSONField(serialize = false)
    private boolean isSearchCount = true;

    /**
     * 默认构造函数
     */
    public PageVo() {
    }

    /**
     * 当前页 current = page = currentPage = pageNo
     *
     * @param page 当前页
     * @return 分页对象
     *
     */
    public PageVo<T> setPage(Long page) {
        return this.setCurrent(page);
    }

    /**
     * 当前页 current = page = currentPage = pageNo
     *
     * @param currentPage 当前页
     * @return 分页对象
     */
    public PageVo<T> setCurrentPage(Long currentPage) {
        return this.setCurrent(currentPage);
    }

    /**
     * 获取当前页 current = page = currentPage = pageNo
     *
     * @return 当前页
     */
    public Long getCurrentPage() {
        return getCurrent();
    }

    /**
     * 当前页 current = page = currentPage = pageNo
     * @param pageNo 当前页
     * @return 分页对象
     */
    public PageVo<T> setPageNo(Long pageNo) {
        return this.setCurrent(pageNo);
    }

    /**
     * 每页记录数size = pageSize = limt = limit
     *
     * @param pageSize 分页大小
     * @return 分页对象
     */
    public PageVo<T> setPageSize(Long pageSize) {
        return this.setSize(pageSize);
    }

    /**
     * 每页记录数size = pageSize = limt = limit
     * @return 分页对象
     */
    public Long getPageSize() {
        return this.getSize();
    }

    /**
     * 每页记录数size = pageSize = limt = limit
     *
     * @param limt 分页大小
     * @return 分页对象
     */
    public PageVo<T> setLimt(Long limt) {
        return this.setSize(limt);
    }

    /**
     * 总的记录数
     *
     * @param totalCount total = totalCount = count 总记录数
     * @return 分页对象
     */
    public PageVo<T> setTotalCount(Long totalCount) {
        return this.setTotal(totalCount);
    }

    /**
     * 总的记录数 total = totalCount = count 总记录数据
     *
     * @return 总记录数
     */
    public Long getTotalCount() {
        return this.getTotal();
    }

    /**
     * 总的记录数
     *
     * @param count total = totalCount = count 总记录数
     * @return 分页对象
     */
    public PageVo<T> setCount(Long count) {
        return this.setTotal(count);
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public PageVo(long current, long size) {
        this(current, size, 0);
    }

    /**
     * 构造函数
     *
     * @param current 当前页
     * @param size    分页大小
     * @param total   总记录数
     */
    public PageVo(long current, long size, long total) {
        this(current, size, total, true);
    }

    /**
     * 构造函数
     *
     * @param current       当前页
     * @param size          分页大小
     * @param isSearchCount 查询数量
     */
    public PageVo(long current, long size, boolean isSearchCount) {
        this(current, size, 0, isSearchCount);
    }

    /**
     * 构造函数
     *
     * @param current       当前页
     * @param size          分页大小
     * @param total         总记录数
     * @param isSearchCount 查询数量
     */
    public PageVo(long current, long size, long total, boolean isSearchCount) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    /**
     * 是否存在上一页
     *
     * @return true / false
     */
    public boolean hasPrevious() {
        return this.current > 1;
    }

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    @Override
    @JsonIgnore
    @JSONField(serialize = false)
    public List<T> getRecords() {
        return this.records;
    }

    /**
     * 数据列表,等与 getList=getRecords
     *
     * @return 列表数据
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public List<T> getList() {
        return getRecords();
    }

    @Override
    public PageVo<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    /**
     * 数据列表,等与 setList=setRecords
     *
     * @param list 列表数据
     * @return 分页对象
     */
    public PageVo<T> setList(List<T> list) {
        return setRecords(list);
    }

    @Override
    @JsonIgnore
    public long getTotal() {
        return this.total;
    }

    @Override
    public PageVo<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    @JsonIgnore
    public long getSize() {
        return this.size;
    }

    @Override
    public PageVo<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    @JsonIgnore
    public long getCurrent() {
        return this.current;
    }

    @Override
    public PageVo<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public String[] ascs() {
        return ascs;
    }

    /**
     * 排序字段
     *
     * @param ascs 排序字段
     * @return 分页对象
     */
    public PageVo<T> setAscs(List<String> ascs) {
        if (ascs != null && !ascs.isEmpty()) {
            this.ascs = ascs.toArray(new String[0]);
        }
        return this;
    }

    /**
     * 升序
     *
     * @param ascs 多个升序字段
     * @return 分页对象
     */
    public PageVo<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    @Override
    public String[] descs() {
        return descs;
    }

    /**
     * 排序
     *
     * @param descs 排序字段
     * @return 分页对象
     */
    public PageVo<T> setDescs(List<String> descs) {
        if (descs != null && !descs.isEmpty()) {
            this.descs = descs.toArray(new String[0]);
        }
        return this;
    }

    /**
     * 降序
     *
     * @param descs 多个降序字段
     * @return 分页对象
     */
    public PageVo<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    @Override
    public boolean optimizeCountSql() {
        return optimizeCountSql;
    }

    @Override
    @JsonIgnore
    public boolean isSearchCount() {
        if (total < 0) {
            return false;
        }
        return isSearchCount;
    }

    /**
     * 查询数量
     *
     * @param isSearchCount 查询数量是否
     * @return 分页对象
     */
    public PageVo<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    /**
     * 优化数量sql
     *
     * @param optimizeCountSql 是否
     * @return 分页对象
     */
    public PageVo<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

    @Override
    public String toString() {
        return "[totalCount=" + getTotalCount() + ", pageSize=" + getPageSize() + ", currentPage=" + getCurrentPage()
                + "]";
    }
}
