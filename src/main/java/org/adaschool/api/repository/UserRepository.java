package org.adaschool.api.repository;

import org.adaschool.api.mapper.UserMapper;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private List<User> list = new ArrayList<User>();

    public void createUsers(){
        list.add(new User("1", "Angel", "Sic", "newangeled@gmail.com", "123456789"));
        list.add(new User("2", "Edu", "Mora", "edu.mora@gmail.com", "123456789"));
    }

    public UserRepository(){
        createUsers();
    }

    public List<User> getAllUsers(){
        return list.stream().toList();
    }

    public Optional<User> getUserById(String id){
        return Optional.ofNullable(list.stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null));
    }

    public User addUser(User user){
        list.add(user);
        return user;
    }

    public User updateUser(User user, String idUser){
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(idUser)){
                index = i;
            }
        }
        list.set(index, user);
        return user;
    }

    public void deleteUser(String idUser){
        list.removeIf(user -> user.getId().equals(idUser));
    }

}
