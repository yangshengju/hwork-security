package com.hwork.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hwork.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangs
 */
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/authInfo")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> queryUserList(@RequestParam("username") String userName) {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;

    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(objectError->logger.info(((FieldError)objectError).getField()+" : "+objectError.getDefaultMessage()));
        }
        logger.info(user.toString());
        user.setUserId("3");
        return user;
    }

    @PutMapping("/{userId}")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
                errors.getAllErrors().stream().forEach(objectError->logger.info(((FieldError)objectError).getField()+" : "+objectError.getDefaultMessage()));
        }
        logger.info(user.toString());
//        user.setUserId("3");
        return user;
    }

    @DeleteMapping("/{userId}")
    public Boolean deleteUser(@Valid @PathVariable String userId, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(objectError->logger.info(((FieldError)objectError).getField()+" : "+objectError.getDefaultMessage()));
        }
//        user.setUserId("3");
        return true;
    }



    @GetMapping("/{id}")
    @JsonView(User.UserDetailView.class)
    public User getUserDetail(@PathVariable("id") String userId) {
//        throw new UserNotExistException(userId,"not exist exception!");
        logger.info("userId from front : "+userId);
        User user = new User();
        user.setUserName("tom");
        return user;
    }
}
