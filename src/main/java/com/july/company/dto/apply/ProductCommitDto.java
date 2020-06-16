package com.july.company.dto.apply;

import com.july.company.dto.finance.BondProductMatchDto;
import com.july.company.dto.finance.OperateMatchDto;
import com.july.company.dto.finance.StockProductMatchDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品提交信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-10 15:51
 **/
@Data
public class ProductCommitDto implements Serializable {

    private static final long serialVersionUID = 7451293536166486311L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 产品类型(0=股权融资，1=债券融资)
     */
    private Integer financeType;
    /**
     * 股权融资产品(financeType=0时，必传)
     */
    private StockProductMatchDto stockProductMatchDto;
    /**
     * 债券融资产品(financeType=1时，必传)
     */
    private BondProductMatchDto bondProductMatchDto;
    /**
     * 一键匹配json数据
     */
    private OperateMatchDto operateMatchDto;

}
