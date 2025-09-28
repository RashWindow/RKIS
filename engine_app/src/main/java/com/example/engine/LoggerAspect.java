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

    @Pointcut("execution(public * com.example.engine..*(..))")
    public void applicationMethods() {}

    @Before("applicationMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        String logMessage = String.format("Called method: %s with arguments: %s", 
                                        methodName, 
                                        java.util.Arrays.toString(args));
        logger.info(logMessage);
        
        System.out.println("ASPECT LOG - BEFORE: " + logMessage);
    }

    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String resultStr = result != null ? result.toString() : "null";

        if (resultStr.length() > 100) {
            resultStr = resultStr.substring(0, 100) + "...";
        }
        
        String logMessage = String.format("Method: %s returned: %s", methodName, resultStr);
        logger.info(logMessage);
        
        System.out.println("ASPECT LOG - AFTER RETURN: " + logMessage);
    }

    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String logMessage = String.format("Method: %s threw exception: %s", 
                                        methodName, 
                                        exception.getMessage());
        logger.error(logMessage);
        
        System.out.println("ASPECT LOG - EXCEPTION: " + logMessage);
        exception.printStackTrace();
    }
}