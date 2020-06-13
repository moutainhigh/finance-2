package com.july.company.vo.finance;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import cn.gjing.tools.excel.Type;
import com.alibaba.excel.annotation.ExcelProperty;
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
    //@ExcelField("产品名称")
    @ExcelProperty(value = "产品名称",index = 0)
    private String productName;
    /**
     * 所属机构
     */
    //@ExcelField("所属机构")
    @ExcelProperty(value = "所属机构",index = 1)
    private String mechanism;
    /**
     * 联系方式
     */
    //@ExcelField("联系方式")
    @ExcelProperty(value = "联系方式",index = 2)
    private String tel;
    /**
     * 融资额度
     */
    //@ExcelField("融资额度")
    @ExcelProperty(value = "融资额度",index = 3)
    private String financeQuotaStr;
    /**
     * 申请时间
     */
    //@ExcelField("申请时间")
    @ExcelProperty(value = "申请时间",index = 4)
    private String createdTimeStr;
    /**
     * 产品状态
     */
    //@ExcelField("产品状态")
    @ExcelProperty(value = "产品状态",index = 5)
    private String statusStr;

}
