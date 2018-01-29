package com.solutionsdelivery.User.controller;

import com.solutionsdelivery.User.dao.ChangePassword;
import com.solutionsdelivery.User.dao.LoginDetails;
import com.solutionsdelivery.User.dao.User;
import com.solutionsdelivery.User.dto.UserResponse;
import com.solutionsdelivery.User.service.UserProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/remita/user/")
public class UserController {

    private final UserProcessRequestService userProcessRequestService;

    @Autowired
    public UserController(UserProcessRequestService userProcessRequestService) {
        this.userProcessRequestService = userProcessRequestService;
    }


    @RequestMapping(value = "createUser/{createdBy}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody User user, @PathVariable("createdBy") String createdBy) {

        UserResponse userResponse = new UserResponse();

        try {
            userResponse = userProcessRequestService.createUser(user, createdBy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "removeUser/{removedBy}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> removeUser(@RequestBody User user, @PathVariable("removedBy") String removedBy) {

        UserResponse userResponse = new UserResponse();

        try {
            userResponse = userProcessRequestService.removeUser(user, removedBy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> removeUser(@RequestBody ChangePassword changePassword) {

        UserResponse userResponse = new UserResponse();

        try {
            userResponse = userProcessRequestService.updatePassword(changePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "validateLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> validateLogin(@RequestBody LoginDetails loginDetails) {

        UserResponse userResponse = new UserResponse();

        try {
            userResponse = userProcessRequestService.userLogin(loginDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "getUsers", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUsers() {

        UserResponse userResponse = new UserResponse();

        try {
            userResponse = userProcessRequestService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

}
