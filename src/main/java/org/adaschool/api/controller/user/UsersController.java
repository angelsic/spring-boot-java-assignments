package org.adaschool.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.mapper.UserMapper;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
@Tag(name = "users resource")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "Post new User")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        User user1 = UserMapper.mapToUser(user);
        usersService.save(user1);
        URI createdUserUri = URI.create("/v1/users/" + user1.getId());
        return ResponseEntity.created(createdUserUri).body(user1);
    }

    @Operation(summary = "Get All Users")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(usersService.all());
    }

    @Operation(summary = "Get User By Id")
    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") String id) {
        Optional<User> userOptional = usersService.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException(id);
        }else{
            return ResponseEntity.ok(userOptional);
        }

    }

    @Operation(summary = "Update User By Id")
    @PutMapping(path = "{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        if(usersService.findById(id).isEmpty()){
            throw new UserNotFoundException(id);
        }else{
            User userupd = usersService.update(user, id);
            return ResponseEntity.ok(userupd);
        }
    }

    @Operation(summary = "Delete User by Id")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        if(usersService.findById(id).isEmpty()){
            throw new UserNotFoundException(id);
        }else{
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
