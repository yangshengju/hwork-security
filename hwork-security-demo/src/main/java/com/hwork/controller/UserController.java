package com.hwork.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hwork.core.properties.SecurityProperties;
import com.hwork.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangs
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/authInfo")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping("/authentication")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        String authHeader=request.getHeader("Authorization");
        String jwtToken=StringUtils.substringAfter(authHeader,"Bearer ");
        Claims claims=null;
        try {
            claims=Jwts.parser().setSigningKey(securityProperties.getOauth2().getSigningKey().getBytes("UTF-8")).parseClaimsJws(jwtToken).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("claims:"+claims);
        return authentication;
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
