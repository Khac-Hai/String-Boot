package baitap.ss2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss2.models.User;
import baitap.ss2.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false) String search
    ) {
        List<User> users = userService.findAllUsers();
        if (search != null && !search.isEmpty()) {
            users = users.stream()
                    .filter(u -> u.getUsername().toLowerCase()
                            .contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(users); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        return ResponseEntity.ok(user);
    }

    // POST /users
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (user.getUsername() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        User created = userService.createUser(user);

        return ResponseEntity.status(201).body(created);
    }

    // PUT /users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,
                                        @RequestBody User user) {

        User updated = userService.updateUser(id, user);

        if (updated == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {

        User deleted = userService.deleteUserById(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        return ResponseEntity.noContent().build();
    }

}