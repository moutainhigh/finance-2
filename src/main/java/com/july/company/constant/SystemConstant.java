package com.july.company.constant;

/**
 * 系统常量
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 09:55
 **/
public class SystemConstant {

    /**
     * 是（正确）
     */
    public static final Integer SYS_TRUE = 0;
    /**
     * 否（错误）
     */
    public static final Integer SYS_FALSE = 1;
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
     * 排除指定url，不进行登录验证
     */
    public static String[] LOGIN_VERIFY_EXCLUDE_URL = {"/userInfo/login", "/userInfo/forgetPassword",
            "/userInfo/sendForgetPasswordCode", "/userInfo/generatorImgCode", "/authorize", "/userInfo/getAuthRedirect", "/enum/**"};
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
     * 营业收入
     */
    public static final String YYSR = "YYSR";
    /**
     * 营业收入增长率
     */
    public static final String YYZZL = "YYZZL";
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
    public static final String MBSCRL = "MBSCRL";
    /**
     * 目标市场增长率
     */
    public static final String MBSCZZL = "MBSCZZL";
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
    public static final String SFJSHG = "SFJSHG";
    /**
     * 发明专利数量
     */
    public static final String FMZLS = "FMZLS";
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



}
