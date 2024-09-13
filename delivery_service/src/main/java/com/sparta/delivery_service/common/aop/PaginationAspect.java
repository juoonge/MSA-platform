package com.sparta.delivery_service.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaginationAspect {

    @Around("execution(* com.sparta.delivery_service.application.service..*(..)) && args(pageable,..)")
    public Object applyPagination(ProceedingJoinPoint joinPoint, Pageable pageable) throws Throwable {
        // 페이지 크기 제한 (10, 30, 50 이외의 값이 오면 기본값 10으로 설정)
        int pageSize = pageable.getPageSize();
        if (pageSize != 10 && pageSize != 30 && pageSize != 50) {
            pageable = PageRequest.of(pageable.getPageNumber(), 10, pageable.getSort());
        }

        // 동적 정렬 로직 추가 (수정일이 null이면 생성일 기준으로 정렬)
        Sort sort = pageable.getSort().and(
            Sort.by(Sort.Order.desc("updatedAt").nullsLast())
                .and(Sort.by(Sort.Order.desc("createdAt")))
        );
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // 실제 메서드 실행
        Object result = joinPoint.proceed(new Object[]{pageable});

        // 결과가 Page<?>인 경우, 콘텐츠가 아닌 Page 전체를 반환
        if (result instanceof Page<?>) {
            return result; // Page 객체를 그대로 반환
        }

        return result;
    }

}





