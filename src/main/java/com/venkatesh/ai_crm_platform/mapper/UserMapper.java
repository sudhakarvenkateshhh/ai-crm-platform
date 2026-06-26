
package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.Dto.user.UserRequestDto;
import com.venkatesh.ai_crm_platform.Dto.user.UserResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.User;

public class UserMapper {

    private UserMapper() {
    }

    // DTO -> Entity
    public static User toEntity(UserRequestDto dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());

        return user;
    }

    // Entity -> DTO
    public static UserResponseDto toResponse(User user) {

        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());

        return dto;
    }
}