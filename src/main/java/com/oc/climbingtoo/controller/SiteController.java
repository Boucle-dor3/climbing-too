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
        site.setSiteName("Ablon");
        site.setDescription("Ablon, c’est la falaise d’alpages, nichée à l’écart, dans un recoin sauvage et préservé en plein cœur du massif des Bornes. On est loin de la montagne à touristes, il n’y a plus aucun bruit de moteur... Là, sur le plateau des Glières, lieu célèbre pour la page d’histoire qui s’y est écrite au cours de la dernière guerre mondiale, où les résistants ont fui les occupants allemands, se trouve le Val d’Ablon, un joyau de falaise haut-savoyard encore inconnu il y a une vingtaine d’années. Sur ses fantastiques dalles grises très sculptées, où le rocher est beau partout, l’escalade présente une variété de styles avec prédominance de grandes envolées en 6b, 6c ou 7a, tout en continuité. On doit plus des deux tiers de ces fabuleuses lignes à Robert Durieux, équipeur motivé et amoureux des lieux depuis au moins trente ans. Autre atout de poids : le pied des voies s’ancre dans un alpage ombragé, moelleux et confortable, idéal pour un assurage serein ou des bambins remuants. Ablon, c’est le site majeur de Haute Savoie pour le cadre, la qualité des lignes et du rocher. Ambiance épicéas, oxygène, clarines et reblochon !");
        site.setRegion("Auvergne-Rhône-Alpes");
        site.setDepartment("Haute-Savoie");
        siteRepository.save(site);
    }

    @GetMapping("/sitepage/{idSite}")
    public String sitePage (@PathVariable("idSite") int idSite, Model model) {
        Site site = siteRepository.findById(idSite);
        model.addAttribute("site", site);
        return "sitepage";
    }



}
