package com.oc.climbingtoo.controller;


import com.oc.climbingtoo.controller.form.TopoForm;
import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.AbstractDocument;
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

    @GetMapping("/create")
    public String createTopo() {
        Topo topo = new Topo();
        topo.setTitle("Ablon");
        topo.setDescription("Description");
        topoRepository.save(topo);
        return "created";
    }

    @GetMapping("/createtopoform")
    public String createTopoForm(Model model) {
        Topo topo = new Topo();
        model.addAttribute("topo", new Topo());
        return "createtopoform";
    }

    @PostMapping("/createtopoform")
    public String topoSubmit(@ModelAttribute TopoForm topoForm) {
        return "redirect:/";
    }
}