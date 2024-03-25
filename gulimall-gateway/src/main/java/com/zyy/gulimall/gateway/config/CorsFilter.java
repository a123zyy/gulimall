package com.zyy.gulimall.gateway.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author zyy
// */
@Component
public class CorsFilter implements WebFilter {

    private static final String ALL = "*";
    private static final String MAX_AGE = "3600L";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 非跨域请求，直接放行
        ServerHttpRequest request = exchange.getRequest();
        if (!CorsUtils.isCorsRequest(request)) {
            return  chain.filter(exchange);
        }
        // 设置跨域响应头
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        if (request.getHeaders().getOrigin() == null) {
            headers.add("Access-Control-Allow-Origin", "*");
        } else {
            headers.add("Access-Control-Allow-Origin", request.getHeaders().getOrigin());
        }
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,token,X-Requested-With");
        headers.add("Access-Control-Allow-Credentials","true");
        headers.add("Access-Control-Max-Age", MAX_AGE);
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }
        return  chain.filter(exchange);
    }

}
