package com.july.company.vo.finance;

import cn.gjing.tools.excel.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 债权金融查询（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BondListVo implements Serializable {


    private static final long serialVersionUID = 5843703271288489294L;
    /**
     * 股权融资产品id
     */
    private Long id;
    /**
     * 股权融资标题
     */
    private String title;
    /**
     * 所属机构
     */
    private String mechanism;
    /**
     * 办公地址
     */
    private String workAddress;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 债权融资产品id
     */
    private Integer productId;
    /**
     * 营业收入
     */
    private String businessStr;

    private String business;

    /**
     * 申请时间
     */
    private Long createdTime;

    private String createdTimeStr;

    /**
     * 当前状态(驳回=-1，保存=0，待审核=1，已发布=2，已下线=3，已删除=4)
     */
    private String status;

}
