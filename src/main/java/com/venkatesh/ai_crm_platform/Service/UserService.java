package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.dto.auth.RegisterRequestDto;
import com.venkatesh.ai_crm_platform.dto.user.UserRequestDto;
import com.venkatesh.ai_crm_platform.dto.user.UserResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.UserMapper;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import com.venkatesh.ai_crm_platform.models.Enum.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.specification.UserSpecification;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ================= REGISTER =================

    public UserResponseDto register(RegisterRequestDto request){

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        // Default role after registration
        user.setRole(Role.SALES);

        user.setActive(true);

        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    // ================= CREATE USER =================
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto create(UserRequestDto request) {

        User user = UserMapper.toEntity(request);

        // Always encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    // ================= GET ALL USERS =================
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<UserResponseDto> getAll(

            int page,

            int size,

            String sortBy,

            String direction,

            String keyword,

            Role role,

            Boolean active

    ){

        Sort sort = direction.equalsIgnoreCase("desc")

                ? Sort.by(sortBy).descending()

                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Specification<User> specification =

                Specification.where(
                                UserSpecification.search(keyword)
                        )
                        .and(
                                UserSpecification.role(role)
                        )
                        .and(
                                UserSpecification.active(active)
                        );

        Page<User> userPage =
                userRepository.findAll(specification,pageable);

        List<UserResponseDto> userDtos =

                userPage.getContent()

                        .stream()

                        .map(UserMapper::toResponse)

                        .toList();

        return new PageResponse<>(

                userDtos,

                userPage.getNumber(),

                userPage.getSize(),

                userPage.getTotalElements(),

                userPage.getTotalPages(),

                userPage.isLast()

        );

    }
    // ================= GET USER =================

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        return UserMapper.toResponse(user);
    }

    // ================= UPDATE USER =================

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto update(Long id,
                                  UserRequestDto request) {

        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());

        existing.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setRole(request.getRole());
        existing.setActive(request.getActive());

        User updatedUser = userRepository.save(existing);

        return UserMapper.toResponse(updatedUser);
    }

    // ================= DELETE USER =================
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User Not Found");
        }

        userRepository.deleteById(id);
    }
}