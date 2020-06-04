package com.july.company.service;

import com.july.company.dto.finance.OperateMatchDto;
import com.july.company.entity.OperateData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengxueqi
 * @since 2020-06-04
 */
public interface OperateDataService extends IService<OperateData> {

    /**
     * 一键匹配保存更新数据
     * @author xiajunwei
     * @since 2020/6/4
     */
    void saveOrUpdateMatchData(OperateMatchDto operateMatchDto);

    /**
     * 通过用户ID查询一键匹配数据
     * @author xiajunwei
     * @since 2020/6/4
     */
    List<OperateData> findMatchData(OperateMatchDto operateMatchDto);
}
