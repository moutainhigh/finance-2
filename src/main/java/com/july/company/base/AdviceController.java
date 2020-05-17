package com.july.company.base;

import com.july.company.exception.BnException;
import com.july.company.response.ResultT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * 公共异常处理并返回前端的json数据
 * @author cqyhm
 */
@Slf4j
@RestControllerAdvice
public class AdviceController {

    private static final String UNIFIED_FAIL_MSG = "服务器内部错误";

    /**
     * 控制器层公共异常处理
     * @param be 接收异常
     * @return 返回结果
     */
    @ExceptionHandler(BnException.class)
    public ResultT bnException(BnException be) {
        log.info(BnException.stackTrace(be));
        return ResultT.error(be.getCode(), errorMsg(be.getMessage()));
    }

    /**
     * 对404异常进行处理
     * 需要设置
     * spring.mvc.throw-exception-if-no-handler-found=true 出现错误时, 直接抛出异常
     * spring.resources.add-mappings=false                          关闭工程中的资源文件建立映射
     * @param be
     * @return 异常处理结果
     * @author cqyhm
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultT noHandlerFoundException(NoHandlerFoundException be) {
        log.error(BnException.stackTrace(be));
        return ResultT.error(errorMsg(be.getMessage()));
    }

    /**
     * 控制器层公共异常处理
     * @param be 接收异常
     * @return 返回结果
     */
    @ExceptionHandler(Exception.class)
    public ResultT exception(Exception be) {
        be.printStackTrace();
        log.error(BnException.stackTrace(be));
        return ResultT.error(errorMsg(be.getMessage()));
    }

    /**
     * 方法参数校验异常
     * @param be 接收异常
     * @return 返回结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultT methodArgument(MethodArgumentNotValidException be) {
        BindingResult br = be.getBindingResult();
        FieldError error = br.getFieldError();
        StringBuffer sb = new StringBuffer("校验异常:");
        sb.append(error.getField()).append(error.getDefaultMessage());
        log.error(sb.toString());
        log.error(BnException.stackTrace(be));
        return ResultT.error(errorMsg(error.getDefaultMessage()));
    }

    /**
     * 参数绑定异常
     * @param be 接收异常
     * @return 返回结果
     */
    @ExceptionHandler(BindException.class)
    public ResultT bindException(BindException be) {
        FieldError error = be.getFieldError();
        StringBuffer sb = new StringBuffer("参数绑定异常:");
        sb.append(error.getField()).append(error.getDefaultMessage());
        log.error(sb.toString());
        log.error(BnException.stackTrace(be));
        return ResultT.error(errorMsg(error.getDefaultMessage()));
    }

    /**
     * 参数校验异常
     * @param be 接收异常
     * @return 返回结果
     */
    @ExceptionHandler(ValidationException.class)
    public ResultT constraintViolation(ValidationException be) {
        log.error(BnException.stackTrace(be));
        be.printStackTrace();
        return ResultT.error(errorMsg(be.getMessage()));
    }

    /**
     * http消息转换异常
     * @param be 接收异常
     * @return 返回结果
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultT parameterTypeConversionException(HttpMessageConversionException be) {
        log.error(BnException.stackTrace(be));
        return ResultT.error(BnException.stackTrace(be));
    }

    private String errorMsg(String message) {
        return StringUtils.isEmpty(message) ? UNIFIED_FAIL_MSG : message;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
