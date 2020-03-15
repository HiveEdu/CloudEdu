package com.myedu.framework.config;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/3/15
 * Time: 13:43
 * Description:
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取代码生成相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "experience")
public class Experience {
    /** 体验时间 */
    public static Integer time;

    public static Integer getTime() {
        return time;
    }

    public static void setTime(Integer time) {
        Experience.time = time;
    }
}
