package com.july.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.july.company.constant.SystemConstant;
import com.july.company.dto.code.QuerySysCodeDto;
import com.july.company.dto.code.SysCodeDto;
import com.july.company.entity.SysCode;
import com.july.company.mapper.SysCodeMapper;
import com.july.company.service.SysCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.code.QueryDetailSysCodeVo;
import com.july.company.vo.code.QuerySysCodeVo;
import com.july.company.vo.code.SysCodeValueVo;
import com.july.company.vo.code.SysCodeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
     * @param sysCodeDto
     * @return java.util.List<com.july.company.vo.code.SysCodeVo>
     * @author zengxueqi
     * @since 2020/5/21
     */
    @Override
    public List<SysCodeVo> getSysCode(SysCodeDto sysCodeDto) {
        List<SysCodeValueVo> sysCodeValueVos = sysCodeMapper.getSysCode(sysCodeDto);

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

    /**
     * 获取查询条件信息
     * @param querySysCodeDto
     * @return java.util.List<com.july.company.vo.code.QuerySysCodeVo>
     * @author zengxueqi
     * @since 2020/5/25
     */
    @Override
    public List<QuerySysCodeVo> getQuerySysCode(QuerySysCodeDto querySysCodeDto) {
        System.out.println("=====>" + querySysCodeDto.getFinanceType());
        QueryWrapper<SysCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("financeType", querySysCodeDto.getFinanceType())
                .eq("boolQuery", SystemConstant.SYS_TRUE)
                .eq("boolShow", SystemConstant.SYS_TRUE)
                .orderByAsc("querySort");
        List<SysCode> sysCodes = this.list(queryWrapper);

        List<SysCode> sysCodeList = sysCodeMapper.getQuerySysCode(querySysCodeDto.getFinanceType());

        List<QuerySysCodeVo> querySysCodeVos = new ArrayList<>();
        sysCodeList.stream().forEach(sysCode -> {
            QuerySysCodeVo querySysCodeVo = QuerySysCodeVo.builder()
                    .codeType(sysCode.getCodeType())
                    .codeName(sysCode.getCodeName())
                    .build();
            List<QueryDetailSysCodeVo> queryDetailSysCodeVos = new ArrayList<>();
            sysCodes.stream().forEach(sysCode1 -> {
                if (sysCode.getCodeType().equals(sysCode1.getCodeType())) {
                    QueryDetailSysCodeVo queryDetailSysCodeVo = QueryDetailSysCodeVo.builder()
                            .codeType(sysCode1.getCodeType())
                            .code(sysCode1.getCode())
                            .value(sysCode1.getValue())
                            .build();
                    queryDetailSysCodeVos.add(queryDetailSysCodeVo);
                }
            });
            querySysCodeVo.setQueryDetailSysCodeVos(queryDetailSysCodeVos);
            querySysCodeVos.add(querySysCodeVo);
        });
        return querySysCodeVos;
    }

    /**
     * 保存一键匹配的下拉框信息
     * @param
     * @return void
     * @author zengxueqi
     * @since 2020/5/22
     */
    @Override
    public void saveSysCode() {
        /*//GDBJ	无	副教授	正教授	国家千人	省千人	其他人才	上市公司高管	上市公司中层	其它
        List<String> gdbj = Arrays.asList("无", "副教授", "正教授", "国家千人", "省千人", "其他人才", "上市公司高管", "上市公司中层", "其它");
        for (int i = 0; i < gdbj.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("GDBJ")
                    .codeName("股东背景")
                    .code(gdbj.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //CPJD	研发	小试	中试	扩大生产	不涉及	其他
        List<String> cpjd = Arrays.asList("研发", "小试", "中试", "扩大生产", "不涉及", "其他");
        for (int i = 0; i < cpjd.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("CPJD")
                    .codeName("产品阶段")
                    .code(cpjd.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //YYSR	无	100万以内	100-500万元	500-1000万元	1000-5000万元	5000万元以上
        List<String> yysr = Arrays.asList("无", "100万以内", "100-500万元", "500-1000万元", "1000-5000万元", "5000万元以上");
        for (int i = 0; i < yysr.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("YYSR")
                    .codeName("营业收入")
                    .code(yysr.get(i))
                    .value(i + "'")
                    .build();
            this.save(sysCode);
        }
        //YYSRZZL	10%以内	10%-20%	20%-30%	30%-50%	50%以上
        List<String> yysrzzl = Arrays.asList("10%以内", "10%-20%", "20%-30%", "30%-50%", "50%以上");
        for (int i = 0; i < yysrzzl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("YYSRZZL")
                    .codeName("营业收入增长率")
                    .code(yysrzzl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //CPMLL
        List<String> cpmll = Arrays.asList("10%以内", "10%-20%", "20%-30%", "30%-50%", "50%以上");
        for (int i = 0; i < cpmll.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("CPMLL")
                    .codeName("产品毛利率")
                    .code(cpmll.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //JLL	100万以内	100-200万	200-500万	500-1000万	1000万以上
        List<String> jll = Arrays.asList("100万以内", "100-200万", "200-500万", "500-1000万", "1000万以上");
        for (int i = 0; i < jll.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("JLL")
                    .codeName("净利率")
                    .code(jll.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //GWRZJE	无	0-100万元	100-200万元	200-500万元	500-1000万元	1000万元以上
        List<String> gwrzje = Arrays.asList("无", "0-100万元", "100-200万元", "200-500万元", "500-1000万元", "1000万元以上");
        for (int i = 0; i < gwrzje.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("GWRZJE")
                    .codeName("过往融资金额")
                    .code(gwrzje.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //CYJL	无	1次	2次	3次	3次以上
        List<String> cyjl = Arrays.asList("无", "1次", "2次", "3次", "3次以上");
        for (int i = 0; i < cyjl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("CYJL")
                    .codeName("实控人创业经历")
                    .code(cyjl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //QYZT	注销	存续	获得融资	被并购	pre-IPO	已上市
        List<String> qyzt = Arrays.asList("注销", "存续", "获得融资", "被并购", "pre-IPO", "已上市");
        for (int i = 0; i < qyzt.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("QYZT")
                    .codeName("历史创业企业状态")
                    .code(qyzt.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //YGRS	1-10人	10-50人	50-100人	100-500人	500人以上
        List<String> ygrs = Arrays.asList("1-10人", "10-50人", "50-100人", "100-500人", "500人以上");
        for (int i = 0; i < ygrs.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("YGRS")
                    .codeName("员工人数")
                    .code(ygrs.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //SCRL	0-10亿元	10-30亿元	30-50亿元	50亿元以上
        List<String> scrl = Arrays.asList("0-10亿元", "10-30亿元", "30-50亿元", "50亿元以上");
        for (int i = 0; i < scrl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("SCRL")
                    .codeName("市场容量")
                    .code(scrl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //SCRLZZL	0-10%	10-20%	20%-30%	30%-50%	50%以上
        List<String> scrlzzl = Arrays.asList("0-10%", "10-20%", "20%-30%", "30%-50%", "50%以上");
        for (int i = 0; i < scrlzzl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("SCRLZZL")
                    .codeName("市场容量预期增长率")
                    .code(scrlzzl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //MBKH	个人	企业	政府	军工	其他
        List<String> mbkh = Arrays.asList("个人", "企业", "政府", "军工", "其他");
        for (int i = 0; i < mbkh.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("MBKH")
                    .codeName("目标客户")
                    .code(mbkh.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //SCZYL	0-10%	10%-30%	30%-50%	50%以上
        List<String> sczyl = Arrays.asList("0-10%", "10%-30%", "30%-50%", "50%以上");
        for (int i = 0; i < sczyl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("SCZYL")
                    .codeName("市场占有率")
                    .code(sczyl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //SFHG	是	否
        List<String> sfhg = Arrays.asList("是", "否");
        for (int i = 0; i < sfhg.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("SFHG")
                    .codeName("是否接受回购条款")
                    .code(sfhg.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //FMZLSL	无	1-3个	3-10个	10个以上
        List<String> fmzlsl = Arrays.asList("无", "1-3个", "3-10个", "10个以上");
        for (int i = 0; i < fmzlsl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("FMZLSL")
                    .codeName("发明专利数量")
                    .code(fmzlsl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //GSJZYS	成本优势	成本优势	渠道优势	先发优势	资质优势	其它
        List<String> gsjzys = Arrays.asList("成本优势", "成本优势", "渠道优势", "先发优势", "资质优势", "其它");
        for (int i = 0; i < gsjzys.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("gsjzys")
                    .codeName("公司竞争优势")
                    .code(gsjzys.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //GDLJTRZJ	无投入	0-100万元	100-500万	500-1000万	1000-3000万	3000万元以上
        List<String> gdljtrzj = Arrays.asList("无投入", "0-100万元", "100-500万", "500-1000万", "1000-3000万", "3000万元以上");
        for (int i = 0; i < gdljtrzj.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("GDLJTRZJ")
                    .codeName("股东累计投入资金")
                    .code(gdljtrzj.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //SSSJ	1-2年	2-3年	3-5年	5年以上
        List<String> sssj = Arrays.asList("1-2年", "2-3年", "3-5年", "5年以上");
        for (int i = 0; i < sssj.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("SSSJ")
                    .codeName("预计上市时间")
                    .code(sssj.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //PDCH	瞪羚企业	独角兽企业	平台生态型龙头企业	不涉及	其它
        List<String> pdch = Arrays.asList("瞪羚企业", "独角兽企业", "平台生态型龙头企业", "不涉及", "其它");
        for (int i = 0; i < pdch.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("PDCH")
                    .codeName("公司所获评定称号")
                    .code(pdch.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }*/
        //贷款期限 1年内	1-3年	3-5年	5年以上
        List<String> dkqx = Arrays.asList("1年内", "1-3年", "3-5年", "5年以上");
        for (int i = 0; i < dkqx.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("DKQX")
                    .codeName("贷款期限")
                    .code(dkqx.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //贷款额度 100万以内	100-200万	200-500万	500-1000万	1000万以上
        List<String> dked = Arrays.asList("100万以内", "100-200万", "200-500万", "500-1000万", "1000万以上");
        for (int i = 0; i < dked.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("DKED")
                    .codeName("贷款额度")
                    .code(dked.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //增信方式 担保	不动产抵押	信用	应收账款质押	票据贴现	股权质押	知识产权质押	其他（填写）
        List<String> zxfs = Arrays.asList("担保", "不动产抵押", "信用", "应收账款质押", "票据贴现", "股权质押", "知识产权质押", "其他");
        for (int i = 0; i < zxfs.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("ZXFS")
                    .codeName("增信方式")
                    .code(zxfs.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //是否接受房产抵押
        List<String> sfjsfcdy = Arrays.asList("是", "否");
        for (int i = 0; i < sfjsfcdy.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("FCDY")
                    .codeName("是否接受房产抵押")
                    .code(sfjsfcdy.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //营业收入 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> yysr = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < yysr.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("BOND_YYSR")
                    .codeName("营业收入")
                    .code(yysr.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //净利润 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> jlr = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < jlr.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("JLR")
                    .codeName("净利润")
                    .code(jlr.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //上年度经营活动现金流净额 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> xjl = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < xjl.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("XJLJE")
                    .codeName("上年度经营活动现金流净额")
                    .code(xjl.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //政府订单额 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> zfdde = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < zfdde.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("ZFDDE")
                    .codeName("政府订单额")
                    .code(zfdde.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //央企/大型国企核心供应商订单额 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> gqdde = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < gqdde.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("GQDDE")
                    .codeName("央企/大型国企核心供应商订单额")
                    .code(gqdde.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //企业资质 无	高新技术企业	技术先进型服务企业	创新型企业	知识产权试点示范企业	其他
        List<String> qyzz = Arrays.asList("无", "高新技术企业", "技术先进型服务企业", "创新型企业", "知识产权试点示范企业", "其他");
        for (int i = 0; i < qyzz.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("QYZZ")
                    .codeName("企业资质")
                    .code(qyzz.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //政府补贴 无	100万以内	100-300万	300-500万	500万以上
        List<String> zfbt = Arrays.asList("无", "100万以内", "100-300万", "300-500万", "500万以上");
        for (int i = 0; i < zfbt.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("ZFBT")
                    .codeName("政府补贴")
                    .code(zfbt.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //是否已引入股权投资
        List<String> sfyyrgqtz = Arrays.asList("是", "否");
        for (int i = 0; i < sfyyrgqtz.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("GQTZ")
                    .codeName("是否已引入股权投资")
                    .code(sfyyrgqtz.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //纳税额度 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> nred = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < nred.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("NRED")
                    .codeName("纳税额度")
                    .code(nred.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //发明专利数量 无	0-5个	5-10个	10-20个	20个以上
        List<String> fmzls = Arrays.asList("无", "0-5个", "5-10个", "10-20个", "20个以上");
        for (int i = 0; i < fmzls.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("BOND_FMZLS")
                    .codeName("股权投资_发明专利数量")
                    .code(fmzls.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //是否有其他贷款 无	科创贷	文创通	成长贷	其他
        List<String> sfyqtdk = Arrays.asList("无", "科创贷", "文创通", "成长贷", "其他");
        for (int i = 0; i < sfyqtdk.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("QTDK")
                    .codeName("是否有其他贷款")
                    .code(sfyqtdk.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
        //现有贷款金额 无	100万以内	100-500万	500-1000万	1000-3000万	3000-10000万	1亿元以上
        List<String> xydked = Arrays.asList("无", "100万以内", "100-500万", "500-1000万", "1000-3000万", "3000-10000万", "1亿元以上");
        for (int i = 0; i < xydked.size(); i++) {
            SysCode sysCode = SysCode.builder()
                    .codeType("DKJE")
                    .codeName("现有贷款金额")
                    .code(xydked.get(i))
                    .value(i + "")
                    .build();
            this.save(sysCode);
        }
    }

}
