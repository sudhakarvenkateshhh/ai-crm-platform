package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.UserService;
import com.venkatesh.ai_crm_platform.models.Entities.User;
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
    public User create(@Valid @RequestBody User user){
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id,
                       @RequestBody User user){
        return userService.update(id,user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "Deleted Successfully";
    }
}