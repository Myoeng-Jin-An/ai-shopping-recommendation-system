package com.aishop.backend.domain.behavior.service;

import com.aishop.backend.domain.behavior.converter.BehaviorLogConverter;
import com.aishop.backend.domain.behavior.dto.requestDTO.BehaviorLogCreateRequest;
import com.aishop.backend.domain.behavior.dto.responseDTO.BehaviorLogResponse;
import com.aishop.backend.domain.behavior.entity.Session;
import com.aishop.backend.domain.behavior.entity.UserBehaviorLog;
import com.aishop.backend.domain.behavior.repository.SessionRepository;
import com.aishop.backend.domain.behavior.repository.UserBehaviorLogRepository;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.product.repository.ProductRepository;
import com.aishop.backend.domain.user.entity.User;
import com.aishop.backend.domain.user.repository.UserRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.BehaviorException;
import com.aishop.backend.global.error.exception.ProductException;
import com.aishop.backend.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 행동 로그 Service
 * 행동 로그 생성 및 조회 비즈니스 로직 처리 역할
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BehaviorLogService {

    private final UserBehaviorLogRepository behaviorLogRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public BehaviorLogResponse createBehaviorLog(BehaviorLogCreateRequest request) {
        // 사용자 조회 로직
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        // 상품 조회 로직
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        // 세션 조회 로직
        Session session = null;
        if (request.getSessionId() != null) {
            session = sessionRepository.findById(request.getSessionId())
                    .orElseThrow(() -> new BehaviorException(ErrorCode.SESSION_NOT_FOUND));
        }

        // 행동 로그 Entity 변환 로직
        UserBehaviorLog behaviorLog = BehaviorLogConverter.toEntity(
                user,
                product,
                request.getActionType(),
                request.getDuration(),
                session,
                request.getIpAddress()
        );

        // 행동 로그 저장 로직
        UserBehaviorLog savedBehaviorLog = behaviorLogRepository.save(behaviorLog);

        // 행동 로그 응답 변환 로직
        return BehaviorLogConverter.toResponse(savedBehaviorLog);
    }

    public List<BehaviorLogResponse> getBehaviorLogsByUser(Long userId) {
        // 사용자 존재 여부 검증 로직
        if (!userRepository.existsById(userId)) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }

        // 사용자 기준 행동 로그 목록 조회 로직
        return behaviorLogRepository.findByUserId(userId).stream()
                .map(BehaviorLogConverter::toResponse)
                .toList();
    }

    public List<BehaviorLogResponse> getBehaviorLogsBySession(String sessionId) {
        // 세션 존재 여부 검증 로직
        if (!sessionRepository.existsById(sessionId)) {
            throw new BehaviorException(ErrorCode.SESSION_NOT_FOUND);
        }

        // 세션 기준 행동 로그 목록 조회 로직
        return behaviorLogRepository.findBySessionId(sessionId).stream()
                .map(BehaviorLogConverter::toResponse)
                .toList();
    }
}
