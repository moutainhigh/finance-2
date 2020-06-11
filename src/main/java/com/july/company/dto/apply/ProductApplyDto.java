package com.july.company.dto.apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 产品申请信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-10 15:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductApplyDto implements Serializable {

    private static final long serialVersionUID = 3148610107306562857L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 产品id
     */
    private Long productId;

}
