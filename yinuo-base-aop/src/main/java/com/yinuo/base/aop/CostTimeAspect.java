package com.yinuo.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author liang
 */
@Aspect
@Slf4j
public class CostTimeAspect {

    /**
     * Another method:@Pointcut("@annotation(CostTime)")
     */
    @Pointcut("@annotation(com.yinuo.base.aop.CostTime)")
    // @Pointcut("@within(CostTime) && execution(public * *(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } finally {
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            log.debug("{}-{} cost:{} ms", className, methodName, (System.currentTimeMillis() - start));
        }
    }
}