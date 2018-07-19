package com.oc.climbingtoo.controller;

import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import com.oc.climbingtoo.service.StorageService;
import com.oc.climbingtoo.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@Controller
public class TopoController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private TopoService topoService;

    @GetMapping("/topolist")
    public String topoList(Model model) {
        List<Topo> topos = topoService.getAll();
        model.addAttribute("topos", topos)   ;
        return "topolist";
    }

    @GetMapping("/topopage/{idTopo}")
    public String topoPage(@PathVariable("idTopo") int idTopo, Model model) {
        Topo topo = topoService.get(idTopo);
        model.addAttribute("topo", topo);
        return "topopage";
    }

}
