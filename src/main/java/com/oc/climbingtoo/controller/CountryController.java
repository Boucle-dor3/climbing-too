package com.oc.climbingtoo.controller;

import com.oc.climbingtoo.repositories.CountryDAO;
import com.oc.climbingtoo.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CountryController {

    private final StorageService storageService;

    private final CountryDAO countryDAO;

}
