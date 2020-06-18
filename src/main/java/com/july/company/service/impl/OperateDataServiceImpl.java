package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.finance.OperateMatchDto;
import com.july.company.entity.OperateData;
import com.july.company.mapper.OperateDataMapper;
import com.july.company.service.OperateDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengxueqi
 * @since 2020-06-04
 */
@Service
public class OperateDataServiceImpl extends ServiceImpl<OperateDataMapper, OperateData> implements OperateDataService {

    @Resource
    private OperateDataMapper operateDataMapper;

    /**
     * 一键匹配保存更新数据
     * @author xiajunwei
     * @since 2020/6/4
     */
    @Override
    public void saveOrUpdateMatchData(OperateMatchDto operateMatchDto) {
        List<OperateData> matchDataList = findMatchData(operateMatchDto);
        if (matchDataList.size() > 0){
            for (OperateData data : matchDataList) {
                if (data.getOperateType() == operateMatchDto.getOperateType()){
                    data.setContent(operateMatchDto.getContent());
                    data.setUpdatedTime(System.currentTimeMillis() / 1000);
                    this.updateById(data);
                    return ;
                }
            }
        }
        OperateData operateData = new OperateData();
        BeanUtils.copyProperties(operateMatchDto, operateData);
        this.save(operateData);
        return ;
    }

    /**
     * 通过用户Id查询一键匹配数据
     * @author xiajunwei
     * @since 2020/6/4
     */
    @Override
    public List<OperateData> findMatchData(OperateMatchDto operateMatchDto) {
        QueryWrapper<OperateData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(operateMatchDto.getUserId()),"userId", operateMatchDto.getUserId())
                .eq(!StringUtils.isEmpty(operateMatchDto.getOperateType()), "operateType", operateMatchDto.getOperateType())
                .eq("deleted", SystemConstant.SYS_FALSE);
        List<OperateData> list = this.list(queryWrapper);
        return list;
    }
}
