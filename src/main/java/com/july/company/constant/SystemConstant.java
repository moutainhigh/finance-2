package com.july.company.constant;

import com.july.company.dto.Node;

import java.util.*;

/**
 * 系统常量
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:55
 **/
public class SystemConstant {

    /**
     * 否（错误）
     */
    public static final Integer SYS_FALSE = 0;
    /**
     * 是（正确）
     */
    public static final Integer SYS_TRUE = 1;
    /**
     * 登录缓存前缀名称
     */
    public static final String CACHE_NAME = "login_";
    /**
     * 登录过期时间(分)
     */
    public static final Integer EXPIRE_LOGIN = 8 * 60;
    /**
     * 图片code前缀
     */
    public static final String IMG_CODE_FLAG = "imgCodeFlag_";
    /**
     * 登录异常状态码
     */
    public static final Integer LOGIN_EXCEPTION = -1000;
    /**
     * 排除指定url，不进行token验证
     */
    public static String[] LOGIN_VERIFY_EXCLUDE_URL = {
            //用户登录
            "/userInfo/login",
            //忘记密码
            "/userInfo/forgetPassword",
            //发送短信
            "/userInfo/sendSmsCode",
            //注册
            "/userInfo/register",
            //验证短信有效性
            "/userInfo/verifySmsCode",
            //忘记密码时查询用户是否存在
            "/userInfo/getUserInfoForForgetPassword",
            //获取字典信息
            "/sysCode/getSysCode",
            //获取指定字典信息
            "/sysCode/getQuerySysCode",
            //股权融资列表信息
            "/financeProduct/getFinanceStockProduct",
            //债券融资列表信息
            "/financeProduct/getFinanceBondProduct",
            //债券融资详细信息
            "/financeBondDetail/getFinanceBondProductDetail",
            //股权融资详细信息
            "/financeStockDetail/getFinanceStockProductDetail",
            //机构与地图对应信息
            "/institution/getInstitutionAndRegion",
            //统计信息
            "/institution/getFinanceStatistics",
            "/enum/**"
    };
    /**
     * 行业方向
     */
    public static final String HYFX = "HYFX";
    /**
     * 融资额度
     */
    public static final String RZED = "RZED";
    /**
     * 融资阶段
     */
    public static final String RZJD = "RZJD";
    /**
     * 股东背景
     */
    public static final String GDBJ = "GDBJ";
    /**
     * 产品阶段
     */
    public static final String CPJD = "CPJD";
    /**
     * 企业状态
     */
    public static final String QYZT = "QYZT";
    /**
     * 营业收入
     */
    public static final String STOCKRIGHT_YYSR = "STOCKRIGHT_YYSR";
    /**
     * 营业收入增长率
     */
    public static final String YYSRZZL = "YYSRZZL";
    /**
     * 产品毛利率/预期产品毛利率
     */
    public static final String CPMLL = "CPMLL";
    /**
     * 净利率
     */
    public static final String JLL = "JLL";
    /**
     * 过往融资金额
     */
    public static final String GWRZJE = "GWRZJE";
    /**
     * 实际控制人创业经历
     */
    public static final String CYJL = "CYJL";
    /**
     * 员工人数
     */
    public static final String YGRS = "YGRS";
    /**
     * 目标市场容量
     */
    public static final String SCRL = "SCRL";
    /**
     * 目标市场增长率
     */
    public static final String SCRLZZL = "SCRLZZL";
    /**
     * 目标客户
     */
    public static final String MBKH = "MBKH";
    /**
     * 市场占有率/预期市场占有率
     */
    public static final String SCZYL = "SCZYL";
    /**
     * 是否接受回购条款
     */
    public static final String SFHG = "SFHG";
    /**
     * 预计上市时间
     */
    public static final String SSSJ = "SSSJ";
    /**
     * 发明专利数量
     */
    public static final String STOCKRIGHT_FMZLSL = "STOCKRIGHT_FMZLSL";
    /**
     * 公司竞争优势
     */
    public static final String GSJZYS = "GSJZYS";
    /**
     * 股东累计投入资金
     */
    public static final String GDLJTRZJ = "GDLJTRZJ";
    /**
     * 公司所获评定称号
     */
    public static final String PDCH = "PDCH";
    /**
     * 注册地址
     */
    public static final String REGION = "REGION";
    /**
     * 机构信息
     */
    public static final String INSTITUTION = "INSTITUTION";
    /**
     * http正常响应状态
     */
    public static final int HTTP_STATUS = 200;
    /**
     * 注册短信
     */
    public static final String SMS_REGISTER = "register_";
    /**
     * 登录过期时间(分)
     */
    public static final Integer SMS_EXPIRE_LOGIN = 3;

    /**
     * 债券融资_发明专利数量
     */
    public static final String BOND_FMZLS = "BOND_FMZLS";
    /**
     * 债券融资_营业收入
     */
    public static final String BOND_YYSR = "BOND_YYSR";
    /**
     * 贷款额度
     */
    public static final String DKED = "DKED";
    /**
     * 现有贷款金额
     */
    public static final String DKJE = "DKJE";
    /**
     * 贷款期限
     */
    public static final String DKQX = "DKQX";
    /**
     * 是否接受房产抵押
     */
    public static final String FCDY = "FCDY";
    /**
     * 央企/大型国企核心供应商订单额
     */
    public static final String GQDDE = "GQDDE";
    /**
     * 是否已引入股权投资
     */
    public static final String GQTZ = "GQTZ";
    /**
     * 净利润
     */
    public static final String JLR = "JLR";
    /**
     * 纳税额度
     */
    public static final String NRED = "NRED";
    /**
     * 是否有其他贷款
     */
    public static final String QTDK = "QTDK";
    /**
     * 企业资质
     */
    public static final String QYZZ = "QYZZ";
    /**
     * 上年度经营活动现金流净额
     */
    public static final String XJLJE = "XJLJE";
    /**
     * 政府补贴
     */
    public static final String ZFBT = "ZFBT";
    /**
     * 政府订单额
     */
    public static final String ZFDDE = "ZFDDE";
    /**
     * 增信方式
     */
    public static final String ZXFS = "ZXFS";
    /**
     * 行业方向
     */
    public static final String BOND_HYFX = "BOND_HYFX";
    /**
     * 股东背景
     */
    public static final String BOND_GDBJ = "BOND_GDBJ";
    /**
     * 资产负债率
     */
    public static final String ZCFZL = "ZCFZL";
    /**
     * 资产收益率
     */
    public static final String JZCSYL = "JZCSYL";
    /**
     * 净利润
     */
    public static final String BOND_JLR = "BOND_JLR";

    public static final List<Node> getDebtratioInfo() {
        Node node1 = Node.builder()
                .type("1")
                .code("0")
                .value("20")
                .build();

        Node node2 = Node.builder()
                .type("2")
                .code("20")
                .value("40")
                .build();

        Node node3 = Node.builder()
                .type("3")
                .code("40")
                .value("60")
                .build();

        Node node4 = Node.builder()
                .type("4")
                .code("60")
                .value("80")
                .build();

        Node node5 = Node.builder()
                .type("5")
                .code("80")
                .value("100")
                .build();

        Node node6 = Node.builder()
                .type("6")
                .code("100")
                .value("10000")
                .build();
        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        return nodes;
    }

    /**
     * 债券融资查询字段匹配
     * @return
     */
    public static final List<Node> getBondField() {
        List<Node> bondField = Arrays.asList(
                //贷款期限
                Node.builder().code("DKQX").value("loanTerm").build(),
                //贷款额度
                Node.builder().code("DKED").value("loanQuota").build(),
                //行业方向
                Node.builder().code("BOND_HYFX").value("industryDirect").build(),
                //股东背景
                Node.builder().code("BOND_GDBJ").value("shareholder").build(),
                //增信方式
                Node.builder().code("ZXFS").value("creditType").build(),
                //企业资质
                Node.builder().code("QYZZ").value("qualification").build(),
                //政府补贴
                Node.builder().code("ZFBT").value("subsidy").build()
        );
        return bondField;
    }

    /**
     * 股权融资查询字段匹配
     * @return
     */
    public static final List<Node> getStockField() {
        List<Node> stockField = Arrays.asList(
                //融资额度
                Node.builder().code("RZED").value("financeQuota").build(),
                //融资阶段
                Node.builder().code("RZJD").value("financeState").build(),
                //行业方向
                Node.builder().code("HYFX").value("industryDirect").build(),
                //注册地址
                Node.builder().code("REGION").value("registerAddress").build(),
                //股东背景
                Node.builder().code("GDBJ").value("shareholder").build(),
                //过往融资金额
                Node.builder().code("GWRZJE").value("oldFinanceQuota").build(),
                //实控人创业经历
                Node.builder().code("CYJL").value("experience").build(),
                //营业收入
                Node.builder().code("STOCKRIGHT_YYSR").value("business").build(),
                //营业收入增长率
                Node.builder().code("YYSRZZL").value("businessAddRate").build(),
                //产品毛利率
                Node.builder().code("CPMLL").value("productRate").build(),
                //净利率
                Node.builder().code("JLL").value("netInterestRate").build(),
                //员工人数
                Node.builder().code("YGRS").value("staffCount").build(),
                //市场容量
                Node.builder().code("SCRL").value("marketCapacity").build(),
                //市场容量预期增长率
                Node.builder().code("SCRLZZL").value("marketAddRate").build(),
                //目标客户
                Node.builder().code("MBKH").value("targetCustomer").build(),
                //市场占有率
                Node.builder().code("SCZYL").value("marketOccupyRate").build(),
                //发明专利数量
                Node.builder().code("STOCKRIGHT_FMZLSL").value("patentCount").build(),
                //公司竞争优势
                Node.builder().code("GSJZYS").value("advantage").build(),
                //股东累计投入资金
                Node.builder().code("GDLJTRZJ").value("capitals").build(),
                //公司所获评定称号
                Node.builder().code("PDCH").value("evaluateName").build()
        );
        return stockField;
    }

    /**
     * 用户退出api url
     */
    public static final String LOGIN_OUT = "/userInfo/logout";
    /**
     * 开关(on=开)
     */
    public static final String SWITCH_ON = "on";

}
