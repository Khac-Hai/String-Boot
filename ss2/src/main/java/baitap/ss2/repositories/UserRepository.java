package baitap.ss2.repositories;

import org.springframework.stereotype.Repository;
import baitap.ss2.models.User;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final List<User> users = Arrays.asList(
            new User(1, "hai", "hai@gmail.com", "ADMIN"),
            new User(2, "han", "han@gmail.com", "USER"),
            new User(3, "quang", "quang@gmail.com", "USER")
    );

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(User user) {
        user.setId((users.size() + 1));
        users.add(user);
        return user;
    }

    @Override
    public User update(int id, User newUser) {
        User existing = findById(id);
        if (existing == null) return null;

        existing.setUsername(newUser.getUsername());
        existing.setEmail(newUser.getEmail());
        existing.setRole(newUser.getRole());

        return existing;
    }

    @Override
    public User deleteById(int id) {
        User existing = findById(id);
        if (existing == null) return null;

        users.remove(existing);
        return existing;
    }
}