package info.setmy.microservice.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {
    }

    // Log before the method execution
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering in Method :  " + joinPoint.getSignature().getName());
        log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Arguments :  " + java.util.Arrays.toString(joinPoint.getArgs()));
    }

    // Log after returning from the method
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method Return : " + joinPoint.getSignature().getName());
        log.info("Return Value : " + result);
    }

    // Log if method throws an exception
    @AfterThrowing(pointcut = "controllerMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Method Exception : " + joinPoint.getSignature().getName());
        log.error("Exception : " + error);
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            log.error("Error in method: " + joinPoint.getSignature().getName());
            throw t;
        }
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time Taken by " + joinPoint.getSignature().getName() + " is " + timeTaken + "ms");
        return result;
    }
}
