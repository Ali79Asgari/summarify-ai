package com.example.summarifyai.logger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    @Pointcut("execution(* com.example.summarifyai.service..*(..)) || execution(* com.example.summarifyai.controller..*(..))")
    public void serviceAndControllerMethods() {
    }

    @Before("serviceAndControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        log.info("Before {}.{} with arguments: {}", className, methodName, args);
    }

    @AfterReturning("serviceAndControllerMethods()")
    public void logAfterReturning(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        log.info("After {}.{} returned with arguments: {}", className, methodName, args);
    }

    @AfterThrowing(pointcut = "serviceAndControllerMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        log.error("Exception in {}.{}: {}", className, methodName, ex.getMessage(), ex);
    }
}
