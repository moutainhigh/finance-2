package com.july.company.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保存债券详情（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusDto implements Serializable {


    private static final long serialVersionUID = -3723397088109075013L;
    /**
     * 产品ID
     */
    private Long productId;

}
