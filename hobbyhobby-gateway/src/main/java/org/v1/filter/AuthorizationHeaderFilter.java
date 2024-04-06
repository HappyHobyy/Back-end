package org.v1.filter;

import lombok.extern.slf4j.Slf4j;
import org.v1.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    private final JwtUtil jwtUtil;

    public AuthorizationHeaderFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(AuthorizationHeaderFilter.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse(); // ServerHttpResponse 객체 가져오기

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(response, "No authorization header", HttpStatus.UNAUTHORIZED);
            try {
                String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(jwtUtil.isAccessTokenValid(authorizationHeader)) {
                    final String userId = jwtUtil.extractAccessUsername(authorizationHeader);
                    final String userRole = jwtUtil.extractAccessUserRole(authorizationHeader);
                    updateRequest(exchange, userId, userRole);
                    return chain.filter(exchange);
                } else {
                    return onError(response, "Invalid access token", HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                // 예외 처리 후 클라이언트에게 응답을 보냅니다.
                String errorMessage = "JWT 오류 발생: " + e.getMessage();
                response.setStatusCode(HttpStatus.UNAUTHORIZED); // 응답 상태 코드 설정
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON); // 응답 헤더 설정
                DataBuffer db = response.bufferFactory().wrap(errorMessage.getBytes()); // 응답 데이터 생성
                return response.writeWith(Mono.just(db)); // 응답 데이터 쓰기
            }
        };
    }


    private Mono<Void> onError(ServerHttpResponse response, String err, HttpStatus httpStatus) {
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorMessage = "{\"error\": \"" + err + "\"}";
        DataBuffer buffer = response.bufferFactory().wrap(errorMessage.getBytes());
        return response.writeWith(Mono.just(buffer));
    }


    private void updateRequest(ServerWebExchange exchange, String userId, String userRole) {
        exchange.getRequest().mutate()
                .header("userId", userId)
                .header("userRole", userRole)
                .build();
    }

    public static class Config {
    }
}