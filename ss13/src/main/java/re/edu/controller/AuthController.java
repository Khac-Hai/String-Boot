package re.edu.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @GetMapping
    public String test() {
        return "Auth API public OK";
    }
}
