package com.oc.climbingtoo.controller;


import com.oc.climbingtoo.controller.form.TopoForm;
import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import com.oc.climbingtoo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    public String topoSubmit(@ModelAttribute TopoForm topoForm, @RequestParam("file") MultipartFile file) {
        Topo topo = topoForm.toTopo();
        try {
            topo.setPicture(storageService.store(file));
        } catch (Exception e) {
            throw e;
        }
        topoRepository.save(topo);
        return "redirect:/";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }


    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Topo topo = new Topo();
        topo.setTitle("Ablon");
        topo.setDescription("La falaise est magnifique.");
        topoRepository.save(topo);
    }


}
