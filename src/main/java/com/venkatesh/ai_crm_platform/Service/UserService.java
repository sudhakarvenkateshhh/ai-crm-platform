package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Dto.auth.RegisterRequestDto;
import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
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

    // Create User
    public User create(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Get User By Id
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User Not Found"));
    }

    public User register(RegisterRequestDto request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        // Default values
        user.setRole(Role.SALES);
        user.setActive(true);

        return userRepository.save(user);
    }

    // Update User
    public User update(Long id, User user) {

        User existing = getById(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPhoneNumber(user.getPhoneNumber());
        existing.setRole(user.getRole());
        existing.setActive(user.getActive());

        // Update password only if provided
        if (user.getPassword() != null &&
                !user.getPassword().isBlank()) {

            existing.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        return userRepository.save(existing);
    }

    // Delete User
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}