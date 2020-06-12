package com.july.company.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 删除债券详情（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BondDeleteDetailDto implements Serializable {

    private static final long serialVersionUID = 2181756579076591329L;
    /**
     * 产品id串
     */
    private String ids;
}
