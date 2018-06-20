package com.oc.climbingtoo.controller;


import com.oc.climbingtoo.controller.form.SiteForm;
import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.exception.InvalidFileExtensionException;
import com.oc.climbingtoo.repository.SiteRepository;
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


import java.io.IOException;
import java.util.List;


@Controller
public class SiteController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private SiteRepository siteRepository;

    public SiteController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Site> site = siteRepository.findAll();
        model.addAttribute("sites", site)   ;
        return "home";
    }


    @GetMapping("/createtopoform")
    public String createTopoForm(Model model, @RequestParam(value="error", required=false) String error) {
        if ("invalid-extension".equals(error)) {
            model.addAttribute("error", "L'extension est invalide.");
        }
        model.addAttribute("topoForm", new SiteForm());
        return "createtopoform";
    }

    @PostMapping("/createtopoform")
    public String topoSubmit(@ModelAttribute SiteForm siteForm, @RequestParam("file") MultipartFile file) {
        Site site = siteForm.toSite();
        try {
            site.setPicture(storageService.store(file));
        } catch (IOException e) {
            throw new RuntimeException("Cannot import image");
        } catch (InvalidFileExtensionException e) {
            return "redirect:/createtopoform?error=invalid-extension";
        }
        siteRepository.save(site);
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
        Site site = new Site();
        site.setTitle("Ablon");
        site.setDescription("La falaise est magnifique.");
        siteRepository.save(site);
    }

    @GetMapping("/sitepage")
    public String sitePage () {
        return "sitepage";
    }

}
