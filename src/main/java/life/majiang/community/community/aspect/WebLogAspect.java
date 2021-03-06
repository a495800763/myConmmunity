package life.majiang.community.community.aspect;

import life.majiang.community.community.util.DateTimeUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 使用切面实现的日志类
 */
@Aspect
@Component
public class WebLogAspect {
    private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * life.majiang.community.community.controller..*.*(..))")
    public void conreollerLog() {
    }

    @Before("conreollerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttribute).getRequest();

        logger.info("==========URL:" + request.getRequestURL().toString());
        logger.info("==========HTTP_MOTHOD:" + request.getMethod());
        logger.info("==========IP:" + request.getRemoteAddr());
    }

//    @Around("conreollerLog()")
//    public void LoggerTime(ProceedingJoinPoint proceedingJoinPoint) {
//        try {
//            LocalDateTime start = LocalDateTime.now();
//            proceedingJoinPoint.proceed();
//            LocalDateTime end = LocalDateTime.now();
//            logger.info(DateTimeUtil.getTimeDuration(start, end));
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
}
