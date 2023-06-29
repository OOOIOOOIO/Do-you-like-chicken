package com.sh.chicken.global.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect // advice + pointcut = advisor
public class LogTraceAspect {

    @Around("@annotation(com.sh.chicken.global.aop.log.LogTrace)")
    public void doLogTrace(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();

        log.info("[트랜잭션 시작]");
        stopWatch.start();
        joinPoint.proceed();
        stopWatch.stop();
        log.info("[트랜잭션 종료]");

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        log.info("실행 메서드: {}, 실행시간 = {}ms", methodName, totalTimeMillis);

    }
}
