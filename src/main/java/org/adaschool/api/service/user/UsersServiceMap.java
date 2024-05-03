package org.adaschool.api.service.user;

import org.adaschool.api.repository.UserRepository;
import org.adaschool.api.repository.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(String id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> all() {
        return userRepository.getAllUsers();
    }

    @Override
    public User save(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteUser(id);
    }

    @Override
    public User update(User user, String userId) {
        return userRepository.updateUser(user, userId);
    }
}
