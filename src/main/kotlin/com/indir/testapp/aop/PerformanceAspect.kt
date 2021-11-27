package com.indir.testapp.aop

import com.indir.testapp.helper.Logger
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
@Slf4j
class PerformanceAspect {

    @Around("@annotation(com.indir.testapp.annotation.LogExecutionTime)")
    @Throws(Throwable::class)
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        var proceed: Any?
        val className = joinPoint.target.javaClass.simpleName
        val methodName = joinPoint.signature.name
        try {
            proceed = joinPoint.proceed()
            Logger.log.info("[{}].[{}] execution success in {}ms", className, methodName, System.currentTimeMillis() - start)

        } catch (throwable: Throwable) {
            Logger.log.error("[{}].[{}] execution failed", className, methodName)
            throw throwable
        }
        return proceed
    }
}