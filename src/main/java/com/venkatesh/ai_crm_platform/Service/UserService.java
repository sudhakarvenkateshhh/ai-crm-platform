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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ================= REGISTER =================

    public User register(RegisterRequestDto request) {

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

        return userRepository.save(user);
    }

    // ================= CREATE USER =================

    public UserResponseDto create(UserRequestDto request) {

        User user = UserMapper.toEntity(request);

        // Always encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    // ================= GET ALL USERS =================

    public List<UserResponseDto> getAll() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    // ================= GET USER =================

    public UserResponseDto getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        return UserMapper.toResponse(user);
    }

    // ================= UPDATE USER =================

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

    public void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User Not Found");
        }

        userRepository.deleteById(id);
    }
}