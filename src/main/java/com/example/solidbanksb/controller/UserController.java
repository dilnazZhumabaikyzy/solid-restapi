package com.example.solidbanksb.controller;

import com.example.solidbanksb.ResponseMessage;
import com.example.solidbanksb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseMessage> getUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.builder().message("All users from database retrieved").userList(userService.getUsers()).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseMessage.builder().message(String.format("Couldn't retrieve users. Error: %s", e.getMessage())).build());
        }
    }

}
