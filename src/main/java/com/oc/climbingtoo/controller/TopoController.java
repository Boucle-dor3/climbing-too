package com.oc.climbingtoo.controller;


import com.oc.climbingtoo.controller.form.TopoForm;
import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TopoController {

    @Autowired
    private TopoRepository topoRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Topo> topos = topoRepository.findAll();
        model.addAttribute("topos", topos);
        return "home";
    }


    @GetMapping("/createtopoform")
    public String createTopoForm(Model model) {
        model.addAttribute("topoForm", new TopoForm());
        return "createtopoform";
    }

    @PostMapping("/createtopoform")
    public String topoSubmit(@ModelAttribute TopoForm topoForm) {
        topoRepository.save(topoForm.toTopo());
        return "redirect:/";
    }


    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Topo topo = new Topo();
        topo.setTitle("Ablon");
        topo.setDescription("La falaise est magnifique.");
        topoRepository.save(topo);
    }


}
