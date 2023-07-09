package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users/{userId}")
    public User user(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    @PutMapping("v1/user/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id , user);
    }

}
