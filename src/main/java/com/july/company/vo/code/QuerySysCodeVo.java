package com.july.company.vo.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 列表查询条件信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-20 13:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuerySysCodeVo {

    /**
     * 字典类型
     */
    private String codeType;
    /**
     * 字典名称
     */
    private String codeName;
    /**
     * 字段名称
     */
    private String field;
    /**
     * 字典详细信息
     */
    private List<QueryDetailSysCodeVo> queryDetailSysCodeVos;

}
