package life.majiang.community.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // EnableWebMvc 会拦截静态资源 配置addResourceHandlers 释放拦截

    @Autowired
    private SessionInteceptor sessionInteceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置静态资源不拦截
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInteceptor).addPathPatterns("/**").excludePathPatterns("/static/**");
    }
}