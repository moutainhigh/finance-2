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
public class SysCodeVo {

    /**
     * 字典类型
     */
    private String codeType;
    /**
     * 字典值信息
     */
    private List<SysCodeValueVo> sysCodeValueVos;

}
