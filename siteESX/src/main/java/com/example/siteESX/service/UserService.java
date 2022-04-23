package com.example.siteESX.service;

import com.example.siteESX.model.Abonament;
import com.example.siteESX.model.User;
import com.example.siteESX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Collection<User> getAll() {
        return userRepository.getAllUsers();
    }

    public User getUserID(String id) {
        return userRepository.getUserID(id);
    }

    public User login(String mail, String password) {
        return userRepository.login(mail, password);
    }

    public void update(User user) {
        userRepository.update(user.getId(), user.getFirstName(), user.getLastName(), user.getMail(), user.getPassword(), user.getAdress());
    }

    public void addUser(User user) {
        userRepository.addUser(user.getId(), user.getFirstName(), user.getLastName(), user.getMail(), user.getPassword(), user.getAdress());
    }

    public void deleteUser(String id) {

        userRepository.deleteUser(id);
    }

    public void addArc(String idUser, String idAbonament) {
        userRepository.addArc(idUser, idAbonament);
//        userRepository.addArcI(idUser,idAbonament);
    }
    public void deleteArc(String idUser, String idAbonament) {
        userRepository.deleteArc(idUser, idAbonament);
//        userRepository.deleteArcI(idUser,idAbonament);
    }

    public Abonament getAbonament(String idUser, String idAbonament){
        return userRepository.getAbonament(idUser,idAbonament);
    }


}
