package com.july.company.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Api响应结果信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:42
 **/
public class ResultT<T> implements Serializable {

    private static final long serialVersionUID = -8194821450292902175L;
    /**
     * 成功状态
     * @ignore
     */
    private static final Integer SUCCESS = 0;
    /**
     * 成功状态
     * @ignore
     */
    private static final String MSG_SUCCESS = "业务处理成功";
    /**
     * 失败状态码
     * @ignore
     */
    private static final Integer FAIL = -1;
    /**
     * 失败状态码
     * @ignore
     */
    private static final String MSG_FAIL = "业务处理失败";

    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 结果集
     */
    private T content;

    /**
     * 业务处理成功，但无业务数据
     */
    public ResultT() {
        this(SUCCESS, MSG_SUCCESS, null);
    }

    /**
     * 业务处理成功，但有业务数据
     * @param content 结果数据
     */
    public ResultT(T content) {
        this(SUCCESS, MSG_SUCCESS, content);
    }

    /**
     * 默认处理时自定义业务编码，没有业务数据
     * @param code 错误编码
     * @param msg  提示消息
     */
    public ResultT(int code, String msg) {
        this(code, msg, null);
    }

    /**
     * 默认处理时，自定义业务编码消息和业务数据
     * @param code 错误编码
     * @param msg  提示消息
     */
    public ResultT(int code, String msg, T content) {
        this.setCode(code);
        this.setMsg(msg);
        this.setContent(content);
    }

    /**
     * 自定义返回错误的消息，无业务数据
     * @param code 错误编码
     * @param msg  错误消息
     * @return 错误结果
     */
    public static <T> ResultT<T> error(int code, String msg) {
        return new ResultT<>(code, msg, null);
    }

    /**
     * 返回默认错误. 有业务数据
     * @param msg 错误消息(业务错误)
     * @return 错误结果
     */
    public static ResultT<String> error(String msg) {
        return new ResultT<>(FAIL, msg, null);
    }

    /**
     * 返回默认错误，无业务数据
     * @return 错误结果
     */
    public static <T> ResultT<T> error() {
        return error(FAIL, MSG_FAIL);
    }

    /**
     * 返回自定义成功的消息，无业务数据
     * @param code 自定义成功编码
     * @param msg  正确消息
     * @return 正确结果
     */
    public static <T> ResultT<T> ok(Integer code, String msg) {
        return new ResultT<T>(code, msg);
    }

    /**
     * 成功默认成功状态，有业务数据
     * @param content 业务数据可以是List、Entity等数据
     * @return 正确结果
     */
    public static <T> ResultT<T> ok(T content) {
        return new ResultT<>(content);
    }

    /**
     * 返回默认成功的状态信息，无业务数据
     * @return 正确结果
     */
    public static <T> ResultT<T> ok() {
        return new ResultT<>();
    }

    /**
     * 根据业务条件返回成功或失败的结果,必须保证成功和失败返回的类型是一致的
     * @param checked 检查条件
     * @param sucess  条件为true时成功返回的消息
     * @param fail    条件为false的失败返回的消息
     * @return 返回结果
     */
    public static ResultT<String> on(boolean checked, String sucess, String fail) {
        return checked ? ResultT.ok(SUCCESS, sucess) : ResultT.error(FAIL, fail);
    }

    /**
     * 返回默认成功的状态，包含分页的业务
     * @param <T>   结果数据类型
     * @param data  data 业务数据可以是List
     * @param pager 分页参数
     * @return 正确结果
     */
    public static <T> ResultT<Page<T>> ok(List<T> data, PageVo<T> pager) {
        return new ResultT<>(new Page<>(data, pager));
    }

    /**
     * 返回默认成功的状态，包含分页的业务
     * @param <T>   结果数据类型
     * @param pager 分页参数,包含分页的数据
     * @return 正确结果
     */
    public static <T> ResultT<Page<T>> ok(PageVo<T> pager) {
        return new ResultT<>(new Page<>(pager.getList(), pager));
    }

    /**
     * 把mybatis的分页参数Ipage转换为内部的分页参数
     * @param <T>  对象
     * @param page mybatis的分页对象
     * @return 接口结果
     * @author zengxueqi
     */
    public static <T> ResultT<Page<T>> ok(IPage<T> page) {
        return ResultT.ok(page.getRecords(), new PageVo<>(page.getCurrent(), page.getSize(), page.getTotal()));
    }

    /**
     * 为了兼容增加,内容参数格式
     * "content": {
     * "list":[],
     * "pager":{}
     * }
     * @param <T> 实体类类型
     */
    @Data
    public static class Page<T> implements Serializable {

        private static final long serialVersionUID = 4526469736474982943L;
        /**
         * 分页数据列表
         */
        private List<T> list;
        /**
         * 分页相关参数
         */
        private PageVo<T> pager;

        public Page(List<T> data, PageVo<T> pager) {
            this.setList(data);
            this.setPager(pager);
        }
    }

    /**
     * 获取结果编码
     * @return 结果编码
     * @author zengxueqi
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置结果编码
     * @param code 结果编码
     * @return
     * @author zengxueqi
     */
    public ResultT<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 获取消息提示
     * @return 消息提示
     * @author zengxueqi
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置消息提示
     * @param msg 消息提示
     * @return 对象
     * @author zengxueqi
     */
    public ResultT<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 获取业务数据
     * @return 业务数据
     * @author zengxueqi
     */
    public T getContent() {
        return content;
    }

    /**
     * 设置业务数据
     * @param content 业务数据
     * @return 结果数据
     * @author zengxueqi
     */
    public ResultT<T> setContent(T content) {
        this.content = content;
        return this;
    }
}