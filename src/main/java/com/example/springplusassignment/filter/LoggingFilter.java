//package com.example.springplusassignment.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Slf4j(topic = "LoggingFilter")
////@Component // SpringSecurity는 Filter 기반으로 동작하기 때문에 방해가 될 수 있으므로 주석 처리
//@Order(1) //  Order를 통해서 filter의 순서를 지정
//public class LoggingFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 전처리
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String url = httpServletRequest.getRequestURI();
//        log.info(url);
//
//        chain.doFilter(request, response); // 다음 Filter 로 이동
//
//        // 후처리
//        log.info("비즈니스 로직 완료");
//    }
//}