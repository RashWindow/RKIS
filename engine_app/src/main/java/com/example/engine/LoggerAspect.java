package com.example.engine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(* com.example.engine.EngineApp.*(..))")
    public void applicationMethods() {}

    @Before("applicationMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Called method: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        logger.info("Method: {} returned: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method: {} threw exception: {}", joinPoint.getSignature().getName(), exception.getMessage(), exception);
    }
}