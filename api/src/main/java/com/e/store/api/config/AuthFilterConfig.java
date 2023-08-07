package com.e.store.api.config;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

import com.e.store.api.viewmodel.AuthValidateVm;
import com.e.store.api.viewmodel.ResVm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;

@Component
public class AuthFilterConfig extends AbstractGatewayFilterFactory<AuthFilterConfig.Config> {
    private static final Logger LOG = LoggerFactory.getLogger(AuthFilterConfig.class);
    private final WebClient.Builder webClientBuilder;

    public AuthFilterConfig (WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public GatewayFilter apply (Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            LOG.info("*****************************************************************************");
            LOG.info("Receive new request: " + path);

            if(ExcludeUrlConfig.isSecure(path)) {
                String bearerToken = request.getHeaders().get("authorization").get(0);
                if (bearerToken == null) {
                    LOG.error("Can not access to secure end point with null token");
                    return onError(exchange, "Can not access to secure end point " +
                                                                                 "with null token", "Token is null",
                                   HttpStatus.UNAUTHORIZED);
                }
                return webClientBuilder.build().get()
                    .uri("http://localhost:9091/api/v1/auth/validate")
                    .header("Authorization", bearerToken)
                    .retrieve().bodyToMono(AuthValidateVm.class)
                    .map(res -> {
                        exchange.getRequest().mutate().header("username", res.username());
                        exchange.getRequest().mutate().header("authority", res.authority());
                        LOG.info("Success validate user. Forward to %s".formatted(exchange.getRequest()));
                        return exchange;
                    }).flatMap(chain::filter).onErrorResume(err -> {
                        LOG.error("Error when validating account");
                        HttpStatusCode errCode = null;
                        String         errMsg  = "";
                        if ( err instanceof WebClientResponseException exception ) {
                            errCode = exception.getStatusCode();
                            errMsg = exception.getMessage();
                        } else {
                            errCode = HttpStatus.BAD_GATEWAY;
                            errMsg = HttpStatus.BAD_GATEWAY.getReasonPhrase();
                        }
                        return onError(exchange, errMsg, "JWT Authentication Failed", errCode);
                    });
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, String errDetails, HttpStatusCode httpStatus) {
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        try {
            response.getHeaders().add("Content-Type", "application/json");
            ResVm  data     = new ResVm(httpStatus, err, errDetails, null, new Date());
            byte[] byteData = objectMapper.writeValueAsBytes(data);
            return response.writeWith(Mono.just(byteData).map(dataBufferFactory::wrap));

        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }
        return response.setComplete();
    }


    public static class Config{
        public Config () {
            // TODO document why this constructor is empty
        }
    }
}
