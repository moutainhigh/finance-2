package com.july.company.intercepts;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 公共字段处理器
 * @author zengxueqi
 * @since 2020/05/22
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //log.info("插入时为公共字段赋值开始");
        setFieldIfNull("createdTime", System.currentTimeMillis() / 1000, metaObject);
        setFieldIfNull("updatedTime", System.currentTimeMillis() / 1000, metaObject);
        setFieldIfNull("deleted", new Integer(0), metaObject);
        //log.info("插入时为公共字段赋值完成");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //log.info("更新时为公共字段赋值开始");
        setFieldIfNull("updatedTime", System.currentTimeMillis() / 1000, metaObject);
        //log.info("更新时为公共字段赋值完成");
    }

    /**
     * 当没有设置值是给公共字段赋值
     * @param fieldName  字段名
     * @param fieldVal   字段值
     * @param metaObject 元对象
     * @return void
     * @author zengxueqi
     * @since 2020/5/22
     */
    public void setFieldIfNull(String fieldName, Object fieldVal, MetaObject metaObject) {
        Object obj = getFieldValByName(fieldName, metaObject);
        if (obj == null) {
            setFieldValByName(fieldName, fieldVal, metaObject);
        }
    }

}
