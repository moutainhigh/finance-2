package com.july.company.controller;


import com.july.company.dto.finance.OperateMatchDto;
import com.july.company.entity.OperateData;
import com.july.company.response.ResultT;
import com.july.company.service.OperateDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  用户操作 前端控制器
 * @author zengxueqi
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/operateData")
public class OperateDataController {

    @Resource
    private OperateDataService operateDataService;

    /**
     * 一键匹配保存或者更新数据
     * @author xiajunwei
     * @since 2020/6/4
     */
    @PostMapping("/saveOrUpdateMatchData")
    public ResultT<String> saveOrUpdateMatchData(@RequestBody OperateMatchDto operateMatchDto) {
        operateDataService.saveOrUpdateMatchData(operateMatchDto);
        return ResultT.ok("操作成功");
    }

    /**
     * 查询一键匹配数据
     * @author xiajunwei
     * @since 2020/6/4
     */
    @PostMapping("/findMatchData")
    public ResultT<List<OperateData>> findMatchData(@RequestBody OperateMatchDto operateMatchDto) {
        return ResultT.ok(operateDataService.findMatchData(operateMatchDto));
    }
}
