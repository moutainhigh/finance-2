package com.july.company.dictionary;

import com.july.company.entity.SysCode;
import com.july.company.service.SysCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化系统参数
 */
@Service("dictInit")
public class DictInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictInit.class);

    /**
     * 存放系统参数
     */
    public static Map<String, String> dictMap = new HashMap<>();
    /**
     * 字典列表数据
     */
    public static Map<String, Object> dictListMap = new HashMap<>();

    @Resource
    private SysCodeService SysCodeService;

    /**
     * 参数初始化工作
     * @author zqk
     */
    public void start() {
        injectDictConfigByDb();
    }

    /**
     * 读取字典表
     * @author zqk
     */
    private void injectDictConfigByDb() {
        List<SysCode> exDictList = SysCodeService.list();
        if (exDictList != null && exDictList.size() > 0) {
            for (SysCode sysCode : exDictList) {
                String key = sysCode.getCodeType() + "_" + sysCode.getCode();
                dictMap.put(key, sysCode.getValue());
            }
        }
    }

    /**
     * 通过字典类型、字典code获取字典value
     * @param codeType
     * @param code
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/5/17
     */
    public static String getCodeValue(String codeType, String code) {
        return dictMap.get(codeType + "_" + code);
    }

    /**
     * 通过字典类型获取字典列表数据
     * @param codeType
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @author zengxueqi
     * @since 2020/5/17
     */
    public static List<Map<String, String>> getCodeList(String codeType) {
        List<Map<String, String>> retuList = new ArrayList<>();
        Map<String, Object> dictMap = DictInit.dictListMap;
        for (Map.Entry<String, Object> entry : dictMap.entrySet()) {
            String key = entry.getKey().substring(0, entry.getKey().lastIndexOf("_"));
            Map<String, String> retuMap = new HashMap<>();
            if ((codeType).equals(key)) {
                SysCode sysCode = (SysCode) entry.getValue();
                retuMap.put("codeType", sysCode.getCodeType());
                retuMap.put("code", sysCode.getCode());
                retuMap.put("value", sysCode.getValue());
                retuList.add(retuMap);
            }
        }
        return retuList;
    }

}
