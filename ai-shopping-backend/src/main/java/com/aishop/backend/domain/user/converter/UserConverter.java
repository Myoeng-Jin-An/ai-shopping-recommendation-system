package com.aishop.backend.domain.user.converter;

import com.aishop.backend.domain.user.dto.requestDTO.UserCreateRequest;
import com.aishop.backend.domain.user.dto.responseDTO.UserResponse;
import com.aishop.backend.domain.user.entity.User;
import com.aishop.backend.domain.user.entity.UserRole;


public class UserConverter {

    private UserConverter(){
    }

    public static User toEntity(UserCreateRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(UserRole.USER)
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
