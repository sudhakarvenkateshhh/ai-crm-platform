package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.UserService;
import com.venkatesh.ai_crm_platform.dto.user.UserRequestDto;
import com.venkatesh.ai_crm_platform.dto.user.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserRequestDto request) {

        return userService.create(request);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {

        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {

        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request) {

        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        userService.delete(id);
        return "Deleted Successfully";
    }
}