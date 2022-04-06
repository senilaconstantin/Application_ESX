package com.example.siteESX.controller;

import com.example.siteESX.model.User;
import com.example.siteESX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User useru=userService.login(user.getMail(), user.getPassword());
        if(useru==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(useru);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserID(@PathVariable String id) {
        User user1 = userService.getUserID(id);
        if (user1 == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(userService.getUserID(id));
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody User user) {
        User user1 = userService.getUserID(user.getId());
        if (user1 == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Collection<User> us = userService.getAll();
        for (User u : us) {
            if (u.getMail().equals(user.getMail()) && !user.getId().equals(u.getId()))
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());

        Collection<User> us = userService.getAll();
        for (User u : us) {
            if (u.getMail().equals(user.getMail()))
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.addUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        User user = userService.getUserID(id);
        if (user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @PostMapping()
//    public ResponseEntity addArc(@RequestBody String idUser, @RequestBody String idAbonament){
//        User user = userService.getUserID(idUser);
//        if (user == null)
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        userService.addArc(idUser, idAbonament);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
