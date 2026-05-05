package re.edu.controller;

import re.edu.model.dto.request.UserCreateDTO;
import re.edu.model.dto.request.UserLoginDTO;
import re.edu.model.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping
    public String test() {
        return "Auth API public OK";
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO req) {
        User rep = authService.createUser(req);
        return new ResponseEntity<>(rep, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO req) {
        return ResponseEntity.ok(authService.loginByUserNameAndPassword(req));
    }
}
