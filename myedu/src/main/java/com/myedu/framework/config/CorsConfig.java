package com.myedu.framework.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/5/16
 * Time: 9:06
 * Description:
 */
@Configuration
public class CorsConfig {
    /**
      * <p>Description：配置允许跨域访问</p>
                 * @return：org.springframework.web.cors.CorsConfiguration
      * @author：lsp
      * @date：020/5/16
                 */
     private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("token");
        return corsConfiguration;
     }

     /**
      * <p>Description：跨域过滤器</p>
      * @return：CorsFilter
      * @author：lsp
      * @date：020/5/16
      */
     @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}
