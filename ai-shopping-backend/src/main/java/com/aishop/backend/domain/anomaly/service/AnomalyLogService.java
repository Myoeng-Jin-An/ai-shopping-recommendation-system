package com.aishop.backend.domain.anomaly.service;

import com.aishop.backend.domain.anomaly.converter.AnomalyLogConverter;
import com.aishop.backend.domain.anomaly.dto.requestDTO.AnomalyLogCreateRequest;
import com.aishop.backend.domain.anomaly.dto.responseDTO.AnomalyLogResponse;
import com.aishop.backend.domain.anomaly.entity.AnomalyLog;
import com.aishop.backend.domain.anomaly.repository.AnomalyLogRepository;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.product.repository.ProductRepository;
import com.aishop.backend.domain.user.entity.User;
import com.aishop.backend.domain.user.repository.UserRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.AnomalyException;
import com.aishop.backend.global.error.exception.ProductException;
import com.aishop.backend.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 이상 행동 로그 Service
 * 이상 행동 로그 생성 및 조회 비즈니스 로직 처리 역할
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnomalyLogService {

    private final AnomalyLogRepository anomalyLogRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public AnomalyLogResponse createAnomalyLog(AnomalyLogCreateRequest request) {
        // 이상 행동 사용자 조회 로직
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        // 이상 행동 상품 조회 로직
        Product product = null;
        if (request.getProductId() != null) {
            product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
        }

        // 이상 행동 로그 Entity 변환 로직
        AnomalyLog anomalyLog = AnomalyLogConverter.toEntity(
                user,
                product,
                request.getReason(),
                request.getScore()
        );

        // 이상 행동 로그 저장 로직
        AnomalyLog savedAnomalyLog = anomalyLogRepository.save(anomalyLog);

        // 이상 행동 로그 응답 변환 로직
        return AnomalyLogConverter.toResponse(savedAnomalyLog);
    }

    public List<AnomalyLogResponse> getAnomalyLogsByUser(Long userId) {
        // 사용자 존재 여부 검증 로직
        if (!userRepository.existsById(userId)) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }

        // 사용자 기준 이상 행동 로그 목록 조회 로직
        return anomalyLogRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(AnomalyLogConverter::toResponse)
                .toList();
    }
}