package springboottools.MDC;

import cn.hutool.core.lang.UUID;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LogInterceptor implements HandlerInterceptor , WebMvcConfigurer {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果有上层调用就用上层的ID
        String traceId = request.getHeader(MDCConstants.TRACE_ID);
        if (traceId == null) {
            traceId = ":" + UUID.randomUUID().toString();
        }
 
        MDC.put(MDCConstants.TRACE_ID, traceId);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //调用结束后删除
        MDC.remove(MDCConstants.TRACE_ID);
    }

    @Bean
    public LogInterceptor getLogInterceptor(){
        return new LogInterceptor();
    }
    //   把自定义的拦截器添加到mvc 配置中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getLogInterceptor()).addPathPatterns("/**");
    }
}