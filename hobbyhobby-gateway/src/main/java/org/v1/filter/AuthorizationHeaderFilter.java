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
            ServerHttpResponse response = exchange.getResponse();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(response, "No authorization header", HttpStatus.UNAUTHORIZED);
            try {
                String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(jwtUtil.isAccessTokenValid(authorizationHeader)) {
                    final String userId = jwtUtil.extractAccessUserId(authorizationHeader);
                    final String nickname = jwtUtil.extractAccessNickname(authorizationHeader);
                    final String userRole = jwtUtil.extractAccessUserRole(authorizationHeader);
                    log.info("userId: " + userId);
                    updateRequest(exchange, userId,nickname, userRole);
                    return chain.filter(exchange);
                } else {
                    return onError(response, "Invalid access token", HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                String errorMessage = "JWT 오류 발생: " + e.getMessage();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                DataBuffer db = response.bufferFactory().wrap(errorMessage.getBytes());
                return response.writeWith(Mono.just(db));
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


    private void updateRequest(ServerWebExchange exchange, String userId,String nickname, String userRole) {
        exchange.getRequest().mutate()
                .header("userId", String.valueOf(userId))
                .header("nickname", nickname)
                .header("userRole", userRole)
                .build();
    }

    public static class Config {
    }
}