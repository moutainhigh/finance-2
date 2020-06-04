package com.july.company.dto.finance;

import com.july.company.dto.Node;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 产品匹配信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-25 10:22
 **/
@Data
public class OperateMatchDto implements Serializable {

    private static final long serialVersionUID = -8152047767416797656L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 融资机构类型
     */
    private Integer operateType;
    /**
     * 操作数据
     */
    private String content;
}
