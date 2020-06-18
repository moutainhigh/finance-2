package com.july.company.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.july.company.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;
/**
 * <p>
 * 股权融资一键匹配
 * </p>
 *
 * @author zengxueqi
 * @since 2020-06-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_stock_match")
public class FinanceStockMatch extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 企业id
     */
    @TableField("companyId")
    private Long companyId;

    /**
     * 股权融资产品id
     */
    @TableField("detailId")
    private Long detailId;

    /**
     * 企业状态
     */
    @TableField("companyStatus")
    private String companyStatus;

    /**
     * 预计上市时间
     */
    @TableField("timeToMarket")
    private String timeToMarket;

    /**
     * 选择类型（0：保存，1：一键匹配）
     */
    @TableField("chooseType")
    private Integer chooseType;

}
