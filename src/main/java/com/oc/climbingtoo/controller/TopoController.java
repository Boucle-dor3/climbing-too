package com.oc.climbingtoo.controller;

import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import com.oc.climbingtoo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;


@Controller
public class TopoController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private TopoRepository topoRepository;

    public TopoController(StorageService storageService) {
        this.storageService = storageService;
    }



    @GetMapping("/topopage")
    public String topoPage(Model model) {
        List<Topo> topo = topoRepository.findAll();
        model.addAttribute("topos", topo)   ;
        return "topopage";
    }



}
