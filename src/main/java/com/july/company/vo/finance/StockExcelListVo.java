package com.july.company.vo.finance;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import cn.gjing.tools.excel.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 股权金融查询（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Excel(value = "股权产品信息", type = Type.XLS)
public class StockExcelListVo implements Serializable {

    private static final long serialVersionUID = -4855757993743183915L;

    /**
     * 产品名称
     */
    @ExcelField("产品名称")
    private String productName;
    /**
     * 所属机构
     */
    @ExcelField("所属机构")
    private String mechanism;
    /**
     * 联系方式
     */
    @ExcelField("联系方式")
    private String tel;
    /**
     * 融资额度
     */
    @ExcelField("融资额度")
    private String financeQuotaStr;
    /**
     * 申请时间
     */
    @ExcelField("申请时间")
    private String createdTimeStr;
    /**
     * 产品状态
     */
    @ExcelField("产品状态")
    private String statusStr;

}
