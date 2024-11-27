package com.ucsal.physicalSpaceManagement.user;

import com.ucsal.physicalSpaceManagement.user.Enums.UserType;
import com.ucsal.physicalSpaceManagement.user.dto.UserDTO;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userService.signup(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> signin(@RequestBody UserDTO userDTO) {
        try {
            UserDTO authenticatedUser = userService.signin(userDTO.getEmail(), userDTO.getPassword());
            return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam Optional<UserType> userType) {
        List<UserDTO> users = userService.getAllUsers(userType);
        return ResponseEntity.ok(users);
    }
}
