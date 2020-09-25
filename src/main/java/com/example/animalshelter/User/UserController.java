package com.example.animalshelter.User;

import com.example.animalshelter.User.User;
import com.example.animalshelter.exception.UserAlreadyExistException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity registerUserAccount(@Valid UserDto userDto) {
    try {
      userService.registerNewUserAccount(userDto);
    } catch (UserAlreadyExistException uaeEx) {
      return new ResponseEntity(uaeEx.getMessage(), HttpStatus.CONFLICT);
    }
    return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = new ArrayList<>();
    User user1 = new User("Jan", "password");
    users.add(user1);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
