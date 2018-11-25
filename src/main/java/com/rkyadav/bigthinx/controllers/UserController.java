package com.rkyadav.bigthinx.controllers;

import com.rkyadav.bigthinx.Error.ApiError;
import com.rkyadav.bigthinx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import com.rkyadav.bigthinx.repositories.UserRepository;

/**
 *
 * @author rkyadav
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     *
     */
    @PostMapping("/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {

        try {
            //If user already exist then return http conflict error otherwise create new user
            User isExistingUser = userRepository.findByName(user.getName());
            if (isExistingUser == null) {
                userRepository.save(user);
                return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
            } else {
                ApiError error = new ApiError();
                error.setStatus(HttpStatus.CONFLICT);
                error.setMessage("User Already Exist");
                error.setDebugMessage("Create user with different user name");
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {

        try {

            //If user exist then update user details else return not found error 
            User isExistingUser = userRepository.findByName(user.getName());
            if (isExistingUser != null) {

                //update user details
                isExistingUser.setDescription(user.getDescription());
                isExistingUser.setDateOfBirth(user.getDateOfBirth());
                isExistingUser.setAddress(user.getAddress());
                userRepository.save(isExistingUser);
                return new ResponseEntity<>("User Updated Successfully", HttpStatus.OK);
            } else {
                ApiError error = new ApiError();
                error.setStatus(HttpStatus.NOT_FOUND);
                error.setMessage("User does not exist");
                error.setDebugMessage("Create new user with user name");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> getUser(@PathVariable("userName") String userName) {

        try {

            //If user exist then return user details else return not found error 
            User isExistingUser = userRepository.findByName(userName);
            if (isExistingUser != null) {
                return new ResponseEntity<>(isExistingUser, HttpStatus.OK);
            } else {
                ApiError error = new ApiError();
                error.setStatus(HttpStatus.NOT_FOUND);
                error.setMessage("User does not Exist");
                error.setDebugMessage("Create new user with user name: " + userName);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<?>> getAllUsers() {
        try {
            //Return all database users
            Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
            return new ResponseEntity<>(userRepository.findAll(sortByCreatedAtDesc), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName) {
        try {

            //If user exist then delete user else return not found error
            User isExistingUser = userRepository.findByName(userName);
            if (isExistingUser != null) {
                userRepository.delete(isExistingUser);
                return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
            } else {
                ApiError error = new ApiError();
                error.setStatus(HttpStatus.NOT_FOUND);
                error.setMessage("User does not Exist");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
