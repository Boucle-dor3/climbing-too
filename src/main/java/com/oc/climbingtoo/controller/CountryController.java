package com.oc.climbingtoo.controller;

import com.oc.climbingtoo.repository.CountryRepository;
import com.oc.climbingtoo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CountryController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CountryRepository countryRepository;

}
