package com.aishop.backend.domain.user.service;

import com.aishop.backend.domain.user.converter.UserConverter;
import com.aishop.backend.domain.user.dto.requestDTO.UserCreateRequest;
import com.aishop.backend.domain.user.dto.responseDTO.UserResponse;
import com.aishop.backend.domain.user.entity.User;
import com.aishop.backend.domain.user.repository.UserRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User 도메인의 비즈니스 로직을 담당하는 서비스 계층
 * Controller는 요청/응답 흐름을 담당하고,
 * Repository는 DB 접근을 담당하므로
 * 회원 생성, 회원 조회 같은 도메인 규칙은 Service에서 처리
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    /**
     * 신규 사용자 생성 로직
     * 이메일은 users 테이블의 unique 컬럼이므로
     * 저장 전 중복 여부를 검증하여 명확한 예외 처리
     *
     * @param request 회원 생성 요청 DTO
     * @return 생성된 사용자 응답 DTO
     */
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        validateDuplicateEmail(request.getEmail());

        User user = UserConverter.toEntity(request);
        User savedUser = userRepository.save(user);

        return UserConverter.toResponse(savedUser);
    }

    /**
     * 사용자 단건 조회 로직
     * 조회 결과가 없을 수 있으므로 Optional.orElseThrow를 사용하여
     * null 반환 대신 예외 흐름으로 처리
     *
     * @param userId 조회할 사용자 ID
     * @return 조회된 사용자 응답 DTO
     */
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        return UserConverter.toResponse(user);
    }

    /**
     * 이메일 중복 검증 로직
     * 서비스 메서드의 흐름을 명확히 하기 위해
     * 중복 검사 조건문을 별도 메서드로 분리
     *
     * @param email 검증할 이메일
     */
    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }
    }
}
