package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.UserService;
import com.venkatesh.ai_crm_platform.dto.user.UserRequestDto;
import com.venkatesh.ai_crm_platform.dto.user.UserResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.venkatesh.ai_crm_platform.models.Enum.Role;
import com.venkatesh.ai_crm_platform.response.PageResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponseDto> create(
            @Valid @RequestBody UserRequestDto request){

        return ResponseBuilder.success(
                "User created successfully",
                userService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<UserResponseDto>> getAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction,

            @RequestParam(required = false)
            String keyword,

            @RequestParam(required = false)
            Role role,

            @RequestParam(required = false)
            Boolean active

    ){

        return ResponseBuilder.success(

                "Users fetched successfully",

                userService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword,

                        role,

                        active

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "User fetched successfully",
                userService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request){

        return ResponseBuilder.success(
                "User updated successfully",
                userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        userService.delete(id);

        return ResponseBuilder.success(
                "User deleted successfully",
                null);
    }
}