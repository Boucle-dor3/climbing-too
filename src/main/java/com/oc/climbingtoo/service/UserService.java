package com.oc.climbingtoo.service;

import com.oc.climbingtoo.entity.User;
import com.oc.climbingtoo.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("userService")
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userDAO.findByConfirmationToken(confirmationToken);
    }

    public void saveUser(User user) {
        userDAO.save(user);
    }
}
