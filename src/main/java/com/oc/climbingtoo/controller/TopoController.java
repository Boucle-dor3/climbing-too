package com.oc.climbingtoo.controller;


import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TopoController {

    @Autowired
    TopoRepository topoRepository;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Topo> topos = topoRepository.findAll();
        model.addAttribute("appName", appName);
        model.addAttribute("topos", topos);
        return "home";
    }

    @GetMapping("/create")
    public String createTopo() {
        Topo topo = new Topo();
        topo.setTitle("Ablon");
        topo.setDescription("Description");
        topoRepository.save(topo);
        return "created";
    }

    @GetMapping("/createtopoform")
    public String createTopoForm() {
        return "createtopoform";
    }
}