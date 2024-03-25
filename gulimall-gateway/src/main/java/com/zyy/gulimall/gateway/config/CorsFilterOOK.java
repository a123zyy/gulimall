//package com.zyy.gulimall.gateway.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@WebFilter(urlPatterns = "/*", filterName = "CorsFilter")
//public class CorsFilterOOK implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpServletRequest request = (HttpServletRequest) req;
//        System.out.println(">>>>>>>>>>>>>>>>>来了》》》》》》》》》》》》》》》》"+request.getHeader("Origin"));
//        if(request.getHeader("Origin") == null){
//            response.setHeader("Access-Control-Allow-Origin", "*");
//        }else{
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        }
////        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
//        response.setHeader("Access-Control-Allow-Headers", "*");
////        response.setHeader("Access-Control-Expose-Headers", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        //response.setHeader("Access-Control-Allow-Credentials", "true");
//        if ("OPTIONS".equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
