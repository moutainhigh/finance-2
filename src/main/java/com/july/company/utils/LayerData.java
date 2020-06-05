package com.july.company.utils;

import lombok.Data;

import java.util.List;

/**
 * Created by wangl on 2017/11/27.
 * todo:
 */
@Data
public class LayerData<T> {
    private Integer code = 0;
    private Long count;
    private List<T> data;
    private String msg = "";

}
