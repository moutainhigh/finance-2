package com.july.company.vo.finance;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.Type;
import com.alibaba.excel.annotation.ExcelProperty;
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
@Excel(value = "债券产品信息", type = Type.XLS)
public class BondListExcelVo implements Serializable {


    private static final long serialVersionUID = 5843703271288489294L;
    /**
     * 股权融资标题
     */
    @ExcelProperty(value = "股权融资标题", index = 0)
    private String title;
    /**
     * 所属机构
     */
    @ExcelProperty(value = "所属机构", index = 1)
    private String mechanism;
    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式", index = 2)
    private String tel;
    /**
     * 营业收入
     */
    @ExcelProperty(value = "营业收入", index = 3)
    private String businessStr;
    /**
     * 申请时间
     */
    @ExcelProperty(value = "申请时间", index = 4)
    private String createdTimeStr;
    /**
     * 当前状态(驳回=-1，保存=0，待审核=1，已发布=2，已下线=3，已删除=4)
     */
    @ExcelProperty(value = "当前状态", index = 5)
    private String statusStr;

}
