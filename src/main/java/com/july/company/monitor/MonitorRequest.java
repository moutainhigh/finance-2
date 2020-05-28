package com.july.company.monitor;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author zengxueqi
 * @since 2019/07/02
 */
@Data
public class MonitorRequest implements Serializable {

    private static final int LIMIT_LENGTH = 1000;
    private static final long serialVersionUID = -541095790920081492L;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 请求方法
     */
    private String requestSignatureMethod;

    /**
     * 请求参数
     */
    private String requestArgs;

    /**
     * 返回结果
     */
    private String returnResult;

    /**
     * 请求唯一id
     */
    private String sessionId;

    public String beforeFormatDump() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("唯一Id:").append(getSessionId()).append("\n")
                .append("请求ip: ").append(getRequestIp()).append("\n")
                .append("请求地址: ").append(getRequestUrl()).append("\n")
                .append("请求方法: ").append(getRequestSignatureMethod()).append("\n")
                .append("请求参数: ").append(getRequestArgs()).append("\n");

        return sb.toString();
    }

    public String afterFormatDump() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("唯一Id: ").append(getSessionId()).append("\n")
                .append("请求方法: ").append(getRequestSignatureMethod()).append("\n")
                .append("返回结果: ").append(strLengthLimit(getReturnResult())).append("\n");

        return sb.toString();
    }

    private String strLengthLimit(String str) {
        if (StringUtils.length(str) > LIMIT_LENGTH) {
            return str.substring(0, LIMIT_LENGTH) + "...";
        }
        return str;
    }

}
