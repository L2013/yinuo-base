package com.yinuo.base.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
public class CatchLogAspect {

    /**
     * The syntax of pointcut : https://blog.csdn.net/zhengchao1991/article/details/53391244
     */
    // @Pointcut("@within(com.yinuo.base.aop.CatchAndLog) && execution(public * *(..))")
    @Pointcut("@annotation(com.yinuo.base.aop.CatchAndLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        logRequest(joinPoint);

        Object response = null;
        try {
            response = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            logResponse(startTime, response);
        }

        return response;
    }

    private void logResponse(long startTime, Object response) {
        try {
            log.debug("RESPONSE: " + JSON.toJSONString(response));
            log.debug("COST: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Exception e) {
            //swallow it
            log.error("logResponse error: " + e);
        }
    }

    private void logRequest(ProceedingJoinPoint joinPoint) {
        try {
            if (!log.isDebugEnabled()) {
                return;
            }
            log.debug("START PROCESSING: " + joinPoint.getSignature().toShortString());
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                log.debug("REQUEST ARG: " + JSON.toJSONString(arg, SerializerFeature.IgnoreErrorGetter));
            }
        } catch (Exception e) {
            //swallow it
            log.error("logRequest error: " + e);
        }
    }

}
