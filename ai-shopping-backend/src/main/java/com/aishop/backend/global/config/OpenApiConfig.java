package com.aishop.backend.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI 문서 설정
 * API 문서의 제목, 설명, 버전 정보를 한 곳에서 관리
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Shopping Recommendation API")
                        .description("AI 기반 맞춤형 쇼핑 추천 및 이상 행동 탐지 시스템 API 문서")
                        .version("v1.0.0"));
    }
}
