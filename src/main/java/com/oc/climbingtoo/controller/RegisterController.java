package com.oc.climbingtoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


}
