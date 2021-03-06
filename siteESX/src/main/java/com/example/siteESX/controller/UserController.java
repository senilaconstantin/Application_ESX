package com.example.siteESX.controller;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.AddAbonament;
import com.example.siteESX.model.User;
import com.example.siteESX.model.UserAb;
import com.example.siteESX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    @PostMapping("/login/admin")
    public ResponseEntity<User> loginAdmin(@RequestBody User user) {
        User useru=userService.loginAdmin(user.getMail(), user.getPassword());
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

    @GetMapping("/getAll/{id}")
    public HttpEntity<? extends Object> getUsers(@PathVariable String id) {
        User user1 = userService.getUserID(id);
        if (user1 == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        Collection<User> users=userService.getAll();
        users.remove(user1);

        return ResponseEntity.ok(users);
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

    @PostMapping("/ab")
    public ResponseEntity addUser(@RequestBody UserAb user) {
        user.setId(UUID.randomUUID().toString());

        Collection<User> us = userService.getAll();
        for (User u : us) {
            if (u.getMail().equals(user.getMail()))
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.addUserAb(user);
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

    @PostMapping("/addArc")
    public ResponseEntity addArc(@RequestBody AddAbonament arcA){
        User user = userService.getUserID(arcA.getIdUser());
        if (user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        userService.addArc(arcA.getIdUser(), arcA.getIdAbonament());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteArc/{idUser}")
    public ResponseEntity deleteArc(@PathVariable String idUser){
        User user = userService.getUserID(idUser);
        if (user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        userService.deleteArc(idUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAb/{idUser}")
    public ResponseEntity<Abonament> getAbonament(@PathVariable String idUser){
        User user = userService.getUserID(idUser);
        if (user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        System.out.println("ajuns aici");
        return ResponseEntity.ok(userService.getAbonament(idUser));
    }
}
