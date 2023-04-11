package com.example.demo.config;

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 网络配置
 * 允许跨域配置
 *
 * @author 净
 * @date 2023/01/28
 */
@Configuration
class CorsConfig : WebMvcConfigurer {
    /**
     * 配置跨域参数
     *
     * @param registry 注册表
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/admin/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*")
    }
}