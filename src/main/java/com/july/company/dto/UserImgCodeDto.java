package com.july.company.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户验证码信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 10:28
 **/
@Data
public class UserImgCodeDto implements Serializable {

    private static final long serialVersionUID = -6178243427859378554L;

    /**
     * 图片code
     */
    @NotNull(message = "请传入图片参数标识")
    private String code;

}
