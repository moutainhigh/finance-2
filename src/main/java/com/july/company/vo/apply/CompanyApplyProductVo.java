package com.july.company.vo.apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 申请产品列表信息（前台：个人中心）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-015 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyApplyProductVo implements Serializable {


    private static final long serialVersionUID = 2553699846278867L;
    /**
     * 产品名称
     */
    private String title;
    /**
     * 所属机构
     */
    private String mechanism;
    /**
     * 申请时间
     */
    private String createdTime;
    /**
     * 申请状态（0=待审核，1=已通过，2=已驳回）
     */
    private Integer status;
    /**
     * 行业方向
     */
    private String industryDirect;
    /**
     * 融资额度（股权产品展示字段）
     */
    private String financeQuota;
    /**
     * 融资阶段（股权产品展示字段）
     */
    private String financeState;
    /**
     * 贷款期限（债权产品展示字段）
     */
    private String loanTerm;
    /**
     * 贷款额度（债权产品展示字段）
     */
    private String loanQuota;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 融资类型(股权融资=0，债券融资=1)
     */
    private Integer financeType;

}
