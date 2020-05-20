package com.july.company.service.impl;

import com.july.company.entity.SysCode;
import com.july.company.mapper.SysCodeMapper;
import com.july.company.service.SysCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.code.SysCodeValueVo;
import com.july.company.vo.code.SysCodeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统字典表 服务实现类
 * @author zengxueqi
 * @since 2020-05-17
 */
@Service
public class SysCodeServiceImpl extends ServiceImpl<SysCodeMapper, SysCode> implements SysCodeService {

    @Resource
    private SysCodeMapper sysCodeMapper;

    /**
     * 列表查询条件信息
     * @param
     * @return java.util.List<com.july.company.dto.code.SysCodeVo>
     * @author zengxueqi
     * @since 2020/5/20
     */
    @Override
    public List<SysCodeVo> getSysCode() {
        List<SysCodeValueVo> sysCodeValueVos = sysCodeMapper.getSysCode();

        List<SysCodeVo> sysCodeVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysCodeValueVos)) {
            //按照codeType进行分组
            Map<String, List<SysCodeValueVo>> stringListMap = sysCodeValueVos.stream().collect(Collectors.groupingBy(SysCodeValueVo::getCodeType));
            stringListMap.forEach((s, sysCodes1) -> {
                sysCodeVos.add(SysCodeVo.builder()
                        .codeType(s)
                        .sysCodeValueVos(sysCodes1)
                        .build());
            });
        }
        return sysCodeVos;
    }

}
