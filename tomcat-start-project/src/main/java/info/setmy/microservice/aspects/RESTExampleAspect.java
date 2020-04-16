package info.setmy.microservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Aspect
public class RESTExampleAspect {

    final Logger log = LogManager.getLogger(RESTExampleAspect.class);

    @Pointcut("within(info.setmy.microservice..*Rest)")
    public void restPointcut() {
    }

    @Pointcut("within(info.setmy.microservice..*Controller)")
    public void controllerPointcut() {
    }

    @AfterThrowing(pointcut = "restPointcut()", throwing = "e")
    public void doAfterThrowingInRest(final JoinPoint joinPoint, final Throwable e) {
        doAfterThrowing(joinPoint);
    }

    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "e")
    public void doAfterThrowingInController(final JoinPoint joinPoint, final Throwable e) {
        doAfterThrowing(joinPoint);
    }

    private void doAfterThrowing(final JoinPoint joinPoint) {
        final String methodName = joinPoint.getSignature().toShortString();
        log.info("AOP got exception in {}", methodName);
    }

    @Around("restPointcut()")
    public Object doAroundRest(final ProceedingJoinPoint joinPoint) throws Throwable {
        return doAround(joinPoint);
    }

    @Around("controllerPointcut()")
    public Object doAroundController(final ProceedingJoinPoint joinPoint) throws Throwable {
        return doAround(joinPoint);
    }

    private Object doAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final String methodName = joinPoint.getSignature().toShortString();

        log.info("AOP before {}", methodName);
        final Object result = joinPoint.proceed();
        log.info("AOP after {}", methodName);
        return result;
    }
}
