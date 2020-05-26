package com.july.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * code-value
 * @author zengxueqi
 * @program finance
 * @since 2020-05-26 15:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node implements Serializable {

    private static final long serialVersionUID = -996528990113382431L;

    private String type;
    private String code;
    private String value;

}
