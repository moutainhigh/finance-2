package com.july.company.controller;

import com.july.company.dto.institution.SaveInstitutionDto;
import com.july.company.entity.Institution;
import com.july.company.entity.enums.InstitutionTypeEnum;
import com.july.company.response.ResultT;
import com.july.company.service.InstitutionService;
import com.july.company.vo.finance.FinanceStatisticsVo;
import com.july.company.vo.institution.InstitutionAndRegionVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 金融机构信息 前端控制器
 * @author zengxueqi
 * @since 2020-05-20
 */
@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Resource
    private InstitutionService institutionService;

    /**
     * 保存机构信息
     * @param saveInstitutionDto
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/21
     */
    @PostMapping("/saveInstitution")
    public ResultT<String> saveInstitution(@RequestBody SaveInstitutionDto saveInstitutionDto) {
        institutionService.saveInstitution(saveInstitutionDto);
        return ResultT.ok("新增成功！");
    }

    /**
     * 获取机构与机构对应的地区信息
     * @param
     * @return com.july.company.response.ResultT<java.util.List < com.july.company.vo.institution.InstitutionAndRegionVo>>
     * @author zengxueqi
     * @since 2020/5/21
     */
    @PostMapping("/getInstitutionAndRegion")
    public ResultT<List<InstitutionAndRegionVo>> getInstitutionAndRegion() {
        return ResultT.ok(institutionService.getInstitutionAndRegion());
    }

    /**
     * 获取融资统计信息
     * @param
     * @return com.july.company.response.ResultT<com.july.company.vo.finance.FinanceStatisticsVo>
     * @author zengxueqi
     * @since 2020/5/21
     */
    @PostMapping("/getFinanceStatistics")
    public ResultT<FinanceStatisticsVo> getFinanceStatistics(){
        return ResultT.ok(institutionService.getFinanceStatistics());
    }

}
