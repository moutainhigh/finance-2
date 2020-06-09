package com.july.company.monitor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author zengxueqi
 * @since 2019/07/12 13:13 <br>
 */
@Component
public class SpringHelper implements ApplicationContextAware, EnvironmentAware {

    /**
     * application 上下文
     */
    private static ApplicationContext applicationContext;
    /**
     * 环境上下文
     */
    private static Environment environment;
    /**
     * 测试环境标识
     */
    private static final String DEV_PROFILE = "dev";
    private static final String TEST_PROFILE = "test";

    /**
     * 获取bean
     * @param clazz 对象
     * @param <T>   bean的类型
     * @return 实例化对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取bean
     * @param name 对象名称
     * @return 实例化后对象
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 判断开发环境
     * @return 是否为测试环境
     */
    public static boolean isDev() {
        Environment env = environment;
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile : activeProfiles) {
            if (profile.equalsIgnoreCase(DEV_PROFILE)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTest() {
        Environment env = environment;
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile : activeProfiles) {
            if (profile.equalsIgnoreCase(TEST_PROFILE)) {
                return true;
            }
        }
        return false;
    }

    public static String getProfile() {
        Environment env = environment;
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile : activeProfiles) {
            return profile;
        }
        return "";
    }

    public static boolean isDev(ApplicationContext applicationContext) {
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            if (profile.equalsIgnoreCase(DEV_PROFILE)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringHelper.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringHelper.environment = environment;
    }

}
