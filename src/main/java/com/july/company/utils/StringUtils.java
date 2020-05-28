package com.july.company.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.*;

/**
 * 字符串工具
 * @author zengxueqi
 */
public class StringUtils {
    /**
     * 问题答案的大写字母表示
     */
    public static final String[] ANSWER = {"A", "B", "C", "D", "E", "F", "G"};

    /**
     * <pre>
     * 把字符表示的答案转换为整数表是的答案
     * A=1 B=2 C=4 D=8 E=16 F=32 G=64
     * 比如:
     * AB    = 1+2=3
     * ACD = 1+4+8 = 13
     * </pre>
     * @param answer 字符串表示的答案比如:AB或ACD等
     * @return 返回整数表示的答案
     */
    public static Integer toAnswer(String answer) {
        Integer actualAnswer = 0;
        if (hasLength(answer)) {
            for (int i = 0; i < ANSWER.length; i++) {
                if (answer.contains(ANSWER[i])) {
                    actualAnswer += (int) Math.pow(2, i);
                }
            }
        }
        return actualAnswer;
    }

    /**
     * <pre>
     * 把整数表示答案转换为字符串表示的答案
     *  A=1 B=2 C=4 D=8 E=16 F=32 G=64
     *  比如
     *  3  = 1 + 2 = AB
     *  13 =1 + 4 + 8 = ACD 答案
     * </pre>
     * @param answer 整数表示的答案
     * @return 字符串表示的答案
     */
    public static String toAnswer(Integer answer) {
        String actualAnswer = "";
        if (answer != null) {
            for (int i = 0; i < ANSWER.length; i++) {
                int an = (int) Math.pow(2, i);
                if ((answer & an) == an) {
                    actualAnswer += ANSWER[i];
                }
            }
        }
        return actualAnswer;
    }

    /**
     * 检查对象t是否包含在数组args中 注意: 1.null会包含在null中, ""包含在""中 2.元素包含检查使用equals方法.
     * @param t    待检查的字符串
     * @param args 对象数组
     * @param <T>  包含的对象
     * @return 如果对象包含在数组中则返回true, 否则false
     */
    public static <T> boolean contains(T t, @SuppressWarnings("unchecked") T... args) {
        List<T> lists = Arrays.asList(args);
        return contains(t, lists);
    }

    /**
     * 检查对象t是否包含在列表中 注意: 1.null会包含在null中, ""包含在""中 2.元素包含检查使用equals方法.
     * @param t     对象元素
     * @param lists 对象列表,列表为null,返回false
     * @param <T>   包含的对象
     * @return 如果对象包含在列表中则返回true, 否则false
     */
    public static <T> boolean contains(T t, List<T> lists) {
        return lists == null ? false : lists.contains(t);
    }

    /**
     * 字符串长度是否为null或""
     * @param str 字符串对象
     * @return 满足null或""返回true
     */
    public static boolean isEmpty(@Nullable Object str) {
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    /**
     * <p>
     * 检查字符串是是否有长度
     * 1.字符串不为null
     * 2.字符串长度大于0
     * </p>
     * @param str 字符串
     * @return 有长度返回true
     */
    public static boolean hasLength(String str) {
        return org.springframework.util.StringUtils.hasLength(str);
    }

    /**
     * 检查指定的字符串是否有文本。
     * @param str 字符串
     * @return 有文本返回true
     */
    public static boolean hasText(String str) {
        return org.springframework.util.StringUtils.hasText(str);
    }

    /**
     * 第1个参数如果为空返回第2个参数,否则返回第1个参数
     * @param value       值
     * @param nullDefault 为空时的默认值
     * @return 转换为字符串
     */
    public static String toString(Object value, String nullDefault) {
        return Objects.toString(value, nullDefault);
    }

    /**
     * 检查指定的对象是否为null
     * @param value 值
     * @return 是否为null
     */
    public boolean isNull(Object value) {
        return Objects.isNull(value);
    }

    /**
     * 检查指定的对象是否不为null
     * @param value 值
     * @return 是否部位null
     */
    public boolean nonNull(Object value) {
        return Objects.nonNull(value);
    }

    /**
     * 检查指定的对象必须不为空,否则抛出空指针异常
     * @param obj 对象
     * @param <T> 对象
     * @return 必须不能为空返回对象
     */
    public static <T> T requireNonNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    /**
     * 检查指定的对象必须不为空,否则抛出空指针异常,并提供异常消息
     * @param obj     对象
     * @param message 异常消息
     * @param <T>     对象
     * @return 必须不能为空的对象
     */
    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    /**
     * 将数组转换为某种分割字符串
     * @param list      数组列表
     * @param separator 分割符号
     * @return 返回字符串
     */
    public static String join(List<?> list, String separator) {
        if (list == null) {
            return null;
        }
        if (isEmpty(separator)) {
            separator = "";
        }

        return Joiner.on(separator).join(list);
    }

    /**
     * 将数组转换为某种分割字符串(默认逗号)
     * @param list 数组列表
     * @return 返回字符串
     */
    public static String join(List<?> list) {
        return join(list, ",");
    }

    /**
     * 将字符串拆分为数组
     * @param str       字符串
     * @param clazz     拆分后的类型
     * @param separator 分割符号
     * @param <T>       对象
     * @return 返回拆分后的数组
     */
    public static <T> List<T> split(String str, String separator, Class<T> clazz) {
        if (isEmpty(str)) {
            return new ArrayList<>();
        }
        if (isEmpty(separator)) {
            separator = "";
        }

        Iterable<String> split = Splitter.on(separator)
                .omitEmptyStrings()
                .trimResults()
                .split(str);
        List<T> resultList = new ArrayList<>();
        split.forEach(s -> {
            T convertObj = ifNullCreate(s, clazz);
            resultList.add(convertObj);
        });

        return resultList;
    }

    /**
     * 默认拆分转换为String数组
     * @param str       拆分字符串
     * @param separator 拆分字符
     * @return 数组对象
     */
    public static List<String> split(String str, String separator) {
        return split(str, separator, String.class);
    }

    /**
     * 默认拆分转换为String数组,分割符为逗号
     * @param str 拆分字符串
     * @return 数组对象
     */
    public static List<String> split(String str) {
        return split(str, ",");
    }


    @SuppressWarnings("unchecked")
    public static <T> T ifNullCreate(Object obj, Class<T> clazz) {
        T result = null;
        if (obj == null) {
            try {
                result = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (clazz.isInstance(obj)) {
                result = (T) obj;
            } else if (clazz.equals(Long.class) && obj.getClass().equals(String.class)) {
                Object o = Long.parseLong((String) obj);
                result = (T) o;
            } else if (clazz.equals(Integer.class) && obj.getClass().equals(String.class)) {
                Object o = Integer.parseInt((String) obj);
                result = (T) o;
            } else if (clazz.equals(Short.class) && obj.getClass().equals(String.class)) {
                Object o = Short.parseShort((String) obj);
                result = (T) o;
            } else if (clazz.equals(String.class) && (
                    obj.getClass().equals(Long.class)
                            || obj.getClass().equals(long.class)
                            || obj.getClass().equals(int.class)
                            || obj.getClass().equals(Integer.class)
                            || obj.getClass().equals(short.class)
                            || obj.getClass().equals(Short.class))) {
                Object o = String.valueOf(obj);
                result = (T) o;
            }
        }
        return result;
    }

    public static String md5(String text) {
        //加密后的字符串
        return DigestUtils.md5Hex(text);
    }

    /**
     * 类似slf4日志格式化
     * @param str
     * @param args
     * @return
     */
    public static String format(String str, Object... args) {
        for (int i = 0; i < args.length; i++) {
            str = str.replaceFirst("\\{\\}", String.valueOf(args[i]));
        }
        return str;
    }

    /**
     * 安全拼接字符串
     * @param strs 字符数组
     * @return 拼接后字符
     */
    public static String softLink(String... strs) {
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append(org.springframework.util.StringUtils.isEmpty(s) ? "" : s);
        }
        return sb.toString();
    }

    /**
     * 安全去首尾链接字符串
     * @param strs
     * @return 拼接后字符串
     */
    public static String softTrimLink(String... strs) {
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append(org.apache.commons.lang.StringUtils.trimToEmpty(s));
        }
        return sb.toString();
    }

    /**
     * 安全toString
     * @param obj 转换str对象
     * @return 对象字符串
     * @author zengxueqi
     * @date 2019/7/25
     */
    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 求两个整数的百分比%(保留两位小数)
     * @param num1 数1
     * @param num2 数2
     * @return 百分数
     * @author fj
     * @since 1.0.12
     */
    public static String toPercent(Integer num1, Integer num2) {
        if (num2 == 0) {
            return "0";
        }
        double v = new BigDecimal((double) num1 / num2).multiply(new BigDecimal("100"))
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return v + "%";
    }

    /**
     * 填充模板中的变量，变量的表示方式 ${xx} , ${yy}
     * @param template 带模板变量的字符串
     * @param param    包含变量的参数
     * @return
     * @author zengxueqi
     * @since 1.0.12
     */
    public static String templateReplace(String template, Map<String, String> param) {
        StringBuilder sb = new StringBuilder(template);
        for (Map.Entry<String, String> entry : param.entrySet()) {
            String keyword = "${" + entry.getKey() + "}";
            String value = entry.getValue();

            int startIndex = sb.indexOf(keyword);
            if (startIndex != -1) {
                sb.replace(startIndex, startIndex + keyword.length(), value);
            }

        }
        return sb.toString();
    }

    /**
     * 字符不为空转换为大写
     * @param str 待转化的字符串
     * @return
     * @author zengxueqi
     */
    public static String convertUpper(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.toUpperCase();
        }
        return str;
    }

}
