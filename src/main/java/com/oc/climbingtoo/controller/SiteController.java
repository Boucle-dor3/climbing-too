package com.oc.climbingtoo.controller;


import com.oc.climbingtoo.controller.dto.CommentDTO;
import com.oc.climbingtoo.controller.dto.SiteDTO;
import com.oc.climbingtoo.entity.*;
import com.oc.climbingtoo.enumeration.SiteType;
import com.oc.climbingtoo.exception.InvalidFileExtensionException;
import com.oc.climbingtoo.repositories.CommentDAO;
import com.oc.climbingtoo.repositories.CountryDAO;
import com.oc.climbingtoo.repositories.DepartmentDAO;
import com.oc.climbingtoo.service.SiteService;
import com.oc.climbingtoo.service.StorageService;
import com.querydsl.jpa.impl.JPAQuery;
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


import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;


@Controller
public class SiteController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private EntityManager entityManager;

    public SiteController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(required = false) Integer department) {
        model.addAttribute("selectedDepartment", department);
        model.addAttribute("sites", siteService.getAll(department));
        model.addAttribute("countries", countryDAO.findAll());
        model.addAttribute("departments", departmentDAO.findAll());
        return "home";
    }


    @GetMapping("/createsiteform")
    public String createSiteForm(Model model, @RequestParam(value="error", required=false) String error) {
        if ("invalid-extension".equals(error)) {
            model.addAttribute("error", "L'extension est invalide.");
        }
        model.addAttribute("siteDTO", new SiteDTO());
        return "forms/createsiteform";
    }

    @PostMapping("/createsiteform")
    public String siteSubmit(@ModelAttribute SiteDTO siteDTO, @RequestParam("file") MultipartFile file) {
        Site site = siteDTO.toConvertSite();
        try {
            site.setPicture(storageService.store(file));
        } catch (IOException e) {
            throw new RuntimeException("Cannot import image");
        } catch (InvalidFileExtensionException e) {
            return "redirect:/createsiteform?error=invalid-extension";
        }
        siteService.create(site);
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
        Country country = new Country();
        country.setCountryName("France");
        countryDAO.save(country);
        Department department = new Department();
        department.setDepartmentName("Haute Savoie");
        department.setCountry(country);
        departmentDAO.save(department);

        Site site = new Site();
        site.setSiteName("Ablon");
        site.setDescription("Ablon, c’est la falaise d’alpages, nichée à l’écart, dans un recoin sauvage et préservé en plein cœur du massif des Bornes. On est loin de la montagne à touristes, il n’y a plus aucun bruit de moteur... Là, sur le plateau des Glières, lieu célèbre pour la page d’histoire qui s’y est écrite au cours de la dernière guerre mondiale, où les résistants ont fui les occupants allemands, se trouve le Val d’Ablon, un joyau de falaise haut-savoyard encore inconnu il y a une vingtaine d’années. Sur ses fantastiques dalles grises très sculptées, où le rocher est beau partout, l’escalade présente une variété de styles avec prédominance de grandes envolées en 6b, 6c ou 7a, tout en continuité. On doit plus des deux tiers de ces fabuleuses lignes à Robert Durieux, équipeur motivé et amoureux des lieux depuis au moins trente ans. Autre atout de poids : le pied des voies s’ancre dans un alpage ombragé, moelleux et confortable, idéal pour un assurage serein ou des bambins remuants. Ablon, c’est le site majeur de Haute Savoie pour le cadre, la qualité des lignes et du rocher. Ambiance épicéas, oxygène, clarines et reblochon !");
        site.setRegion("Auvergne-Rhône-Alpes");
        site.setType(SiteType.BLOC);
        site.setDepartment(department);
        site.setRockClimbing("La falaise appartient à un vallon de calcaire urgonien, parvenu jusqu’à nous sous forme de murs verticaux ou légèrement déversants, compacts et finement ciselés d’une multitude de cannelures et de picots, qui sont d’ailleurs la marque de fabrique d’Ablon. Quasiment aucun voie n’y échappe, et vos doigts s’en souviendront. Le style dominant reste les voies de continuité, et vous trouverez quelques grandes longueurs avec des prises à profusion, mais pas toutes dans le bon sens ce qui complique la lecture. Plus de 230 voies, du 4b au 8b. Pour les grimpeurs de niveau 5+ à 6b, ce sont dans les secteurs \"Ligne du temps\" et \"Résistance et liberté\" les plus adaptés. Corde de 70 m suffisante. Quelques rares voies où une corde de 80 m est nécessaire. Voies assez bien équipées, prévoir un bon jeu de dégaines. Best of \"Ingrat Nadelgrat\", LE 7a de la falaise, secteur \"Terre Mythe\". \"Picothérapie\", deux longueurs en 6a, une des plus parcourues. \"La ligne du temps\", 7b, la première voie difficile de la falaise. \"Dublabla\", 7b puis 6b, grande longueur variée.");
        site.setAccessApproach("Se rendre à Thorens les Glières au Nord d’Annecy. Depuis Thorens, aller jusqu’au col des Glières. Une fois au col, redescendre de trois cent mètres pour prendre une route sur la droite, le chemin du Collet. Continuer jusqu’en bas de la descente et au niveau de la ferme, prendre à nouveau à droite. Suivre le chemin des Eaux-Noires jusqu’au parking. Un sentier part sur la droite dans la forêt au niveau de la barrière. Attention, il est strictement interdit d’utiliser votre véhicule au-delà de cette barrière. Continuer à pied. Prévoir 40 mn de marche, juste de quoi s’imprégner de l’ambiance en mouillant un peu le tee-shirt. Quand y aller? Période idéale : mai à novembre. En général, la neige sonne le début et la fin de la période de grimpe. En été, il est possible de grimper sur certaines voies à l’ombre des arbres. Sinon, le soleil arrive tôt et la falaise passe à l’ombre vers 15 h. Il peut même faire frais en fin de journée. Attention aux orages de montagne pouvant être très violents dans ce secteur.");
        site.setHostingRefueling("Sur le plateau des Glières : \n" +
                "Restaurant gîte Les Lanfian’nes - chez Chantal Bar, restaurant, gîte, et grande terrasse. 04.50.22.45.65 - www.lanfiannes-gite-glieres.fr. \n" +
                "Restaurant Gautard (Nathalie et Jean-Claude) Terrasse ensoleillée avec vue sur les montagnes pour une cuisine chaleureuse et familiale - 04.50.24.40.71 - www.restaurant-gautard-thorens-glieres.fr. \n" +
                "Restaurant Chez Constance. Restaurant-terrasse et spécialités savoyardes, ambiance refuge de montagne pour le gîte - 04.50.22.45.61 - www.les-glieres.fr.");
        siteService.create(site);

        Department department1 = new Department();
        department1.setDepartmentName("Tarn");
        department1.setCountry(country);
        departmentDAO.save(department1);

        Site site1 = new Site();
        site1.setSiteName("Sorèze");
        site1.setDescription("A la sortie du village de Sorèze, prendre direction la carrière (sur la droite). Passer devant la carrière (à gauche) puis sur le pont. Après le pont, prendre à gauche la route forestière de la Pistre. Remonter ce chemin jusqu'à retrouver le grillage de la carrière (une affiche de la grotte de Calel est fixée sur le grillage). Ensuite prendre à gauche à la croisée de 3 chemins : c'est une descente. A partir de là, il faut longer le grillage (et donc contourner la carrière qui se trouve à gauche). Juste avant le parking, quelques ornières et une pancarte du plateau du causse de Sorèze. Laisser la voiture et remonter le chemin pédestre à gauche vers le nord ouest en direction de la table d'orientation. Prendre à gauche de la table vers le bois et descendre le sentier qui part direct en pente vers le nord-ouest puis l'ouest.\n" +
                "Accès au 1er secteur (\"du haut\" voies faciles) en 3 minutes et au 2nd (\"du bas\" voies dures) en 6 minutes. Le nom et les cotations sont marquées au pied des voies.\n");
        site1.setRegion("Occitanie");
        site1.setType(SiteType.FALAISE);
        site1.setDepartment(department1);
        site1.setRockClimbing("La falaise appartient à un vallon de calcaire urgonien, parvenu jusqu’à nous sous forme de murs verticaux ou légèrement déversants, compacts et finement ciselés d’une multitude de cannelures et de picots, qui sont d’ailleurs la marque de fabrique d’Ablon. Quasiment aucun voie n’y échappe, et vos doigts s’en souviendront. Le style dominant reste les voies de continuité, et vous trouverez quelques grandes longueurs avec des prises à profusion, mais pas toutes dans le bon sens ce qui complique la lecture. Plus de 230 voies, du 4b au 8b. Pour les grimpeurs de niveau 5+ à 6b, ce sont dans les secteurs \"Ligne du temps\" et \"Résistance et liberté\" les plus adaptés. Corde de 70 m suffisante. Quelques rares voies où une corde de 80 m est nécessaire. Voies assez bien équipées, prévoir un bon jeu de dégaines. Best of \"Ingrat Nadelgrat\", LE 7a de la falaise, secteur \"Terre Mythe\". \"Picothérapie\", deux longueurs en 6a, une des plus parcourues. \"La ligne du temps\", 7b, la première voie difficile de la falaise. \"Dublabla\", 7b puis 6b, grande longueur variée.");
        site1.setAccessApproach("Se rendre à Thorens les Glières au Nord d’Annecy. Depuis Thorens, aller jusqu’au col des Glières. Une fois au col, redescendre de trois cent mètres pour prendre une route sur la droite, le chemin du Collet. Continuer jusqu’en bas de la descente et au niveau de la ferme, prendre à nouveau à droite. Suivre le chemin des Eaux-Noires jusqu’au parking. Un sentier part sur la droite dans la forêt au niveau de la barrière. Attention, il est strictement interdit d’utiliser votre véhicule au-delà de cette barrière. Continuer à pied. Prévoir 40 mn de marche, juste de quoi s’imprégner de l’ambiance en mouillant un peu le tee-shirt. Quand y aller? Période idéale : mai à novembre. En général, la neige sonne le début et la fin de la période de grimpe. En été, il est possible de grimper sur certaines voies à l’ombre des arbres. Sinon, le soleil arrive tôt et la falaise passe à l’ombre vers 15 h. Il peut même faire frais en fin de journée. Attention aux orages de montagne pouvant être très violents dans ce secteur.");
        site1.setHostingRefueling("Sur le plateau des Glières : \n" +
                "Restaurant gîte Les Lanfian’nes - chez Chantal Bar, restaurant, gîte, et grande terrasse. 04.50.22.45.65 - www.lanfiannes-gite-glieres.fr. \n" +
                "Restaurant Gautard (Nathalie et Jean-Claude) Terrasse ensoleillée avec vue sur les montagnes pour une cuisine chaleureuse et familiale - 04.50.24.40.71 - www.restaurant-gautard-thorens-glieres.fr. \n" +
                "Restaurant Chez Constance. Restaurant-terrasse et spécialités savoyardes, ambiance refuge de montagne pour le gîte - 04.50.22.45.61 - www.les-glieres.fr.");
        siteService.create(site1);

        Site site2 = new Site();
        site2.setSiteName("Gorges du Verdon");
        site2.setDescription("Ablon, c’est la falaise d’alpages, nichée à l’écart, dans un recoin sauvage et préservé en plein cœur du massif des Bornes. On est loin de la montagne à touristes, il n’y a plus aucun bruit de moteur... Là, sur le plateau des Glières, lieu célèbre pour la page d’histoire qui s’y est écrite au cours de la dernière guerre mondiale, où les résistants ont fui les occupants allemands, se trouve le Val d’Ablon, un joyau de falaise haut-savoyard encore inconnu il y a une vingtaine d’années. Sur ses fantastiques dalles grises très sculptées, où le rocher est beau partout, l’escalade présente une variété de styles avec prédominance de grandes envolées en 6b, 6c ou 7a, tout en continuité. On doit plus des deux tiers de ces fabuleuses lignes à Robert Durieux, équipeur motivé et amoureux des lieux depuis au moins trente ans. Autre atout de poids : le pied des voies s’ancre dans un alpage ombragé, moelleux et confortable, idéal pour un assurage serein ou des bambins remuants. Ablon, c’est le site majeur de Haute Savoie pour le cadre, la qualité des lignes et du rocher. Ambiance épicéas, oxygène, clarines et reblochon !");
        site2.setRegion("Auvergne-Rhône-Alpes");
        site2.setType(SiteType.FALAISE);
        site2.setDepartment(department);
        site2.setRockClimbing("La falaise appartient à un vallon de calcaire urgonien, parvenu jusqu’à nous sous forme de murs verticaux ou légèrement déversants, compacts et finement ciselés d’une multitude de cannelures et de picots, qui sont d’ailleurs la marque de fabrique d’Ablon. Quasiment aucun voie n’y échappe, et vos doigts s’en souviendront. Le style dominant reste les voies de continuité, et vous trouverez quelques grandes longueurs avec des prises à profusion, mais pas toutes dans le bon sens ce qui complique la lecture. Plus de 230 voies, du 4b au 8b. Pour les grimpeurs de niveau 5+ à 6b, ce sont dans les secteurs \"Ligne du temps\" et \"Résistance et liberté\" les plus adaptés. Corde de 70 m suffisante. Quelques rares voies où une corde de 80 m est nécessaire. Voies assez bien équipées, prévoir un bon jeu de dégaines. Best of \"Ingrat Nadelgrat\", LE 7a de la falaise, secteur \"Terre Mythe\". \"Picothérapie\", deux longueurs en 6a, une des plus parcourues. \"La ligne du temps\", 7b, la première voie difficile de la falaise. \"Dublabla\", 7b puis 6b, grande longueur variée.");
        site2.setAccessApproach("Se rendre à Thorens les Glières au Nord d’Annecy. Depuis Thorens, aller jusqu’au col des Glières. Une fois au col, redescendre de trois cent mètres pour prendre une route sur la droite, le chemin du Collet. Continuer jusqu’en bas de la descente et au niveau de la ferme, prendre à nouveau à droite. Suivre le chemin des Eaux-Noires jusqu’au parking. Un sentier part sur la droite dans la forêt au niveau de la barrière. Attention, il est strictement interdit d’utiliser votre véhicule au-delà de cette barrière. Continuer à pied. Prévoir 40 mn de marche, juste de quoi s’imprégner de l’ambiance en mouillant un peu le tee-shirt. Quand y aller? Période idéale : mai à novembre. En général, la neige sonne le début et la fin de la période de grimpe. En été, il est possible de grimper sur certaines voies à l’ombre des arbres. Sinon, le soleil arrive tôt et la falaise passe à l’ombre vers 15 h. Il peut même faire frais en fin de journée. Attention aux orages de montagne pouvant être très violents dans ce secteur.");
        site2.setHostingRefueling("Sur le plateau des Glières : \n" +
                "Restaurant gîte Les Lanfian’nes - chez Chantal Bar, restaurant, gîte, et grande terrasse. 04.50.22.45.65 - www.lanfiannes-gite-glieres.fr. \n" +
                "Restaurant Gautard (Nathalie et Jean-Claude) Terrasse ensoleillée avec vue sur les montagnes pour une cuisine chaleureuse et familiale - 04.50.24.40.71 - www.restaurant-gautard-thorens-glieres.fr. \n" +
                "Restaurant Chez Constance. Restaurant-terrasse et spécialités savoyardes, ambiance refuge de montagne pour le gîte - 04.50.22.45.61 - www.les-glieres.fr.");
        siteService.create(site2);

        Department department3 = new Department();
        department3.setDepartmentName("Tarn et garonne");
        department3.setCountry(country);
        departmentDAO.save(department3);


        Site site3 = new Site();
        site3.setSiteName("Gorges de l'Aveyron");
        site3.setDescription("Ablon, c’est la falaise d’alpages, nichée à l’écart, dans un recoin sauvage et préservé en plein cœur du massif des Bornes. On est loin de la montagne à touristes, il n’y a plus aucun bruit de moteur... Là, sur le plateau des Glières, lieu célèbre pour la page d’histoire qui s’y est écrite au cours de la dernière guerre mondiale, où les résistants ont fui les occupants allemands, se trouve le Val d’Ablon, un joyau de falaise haut-savoyard encore inconnu il y a une vingtaine d’années. Sur ses fantastiques dalles grises très sculptées, où le rocher est beau partout, l’escalade présente une variété de styles avec prédominance de grandes envolées en 6b, 6c ou 7a, tout en continuité. On doit plus des deux tiers de ces fabuleuses lignes à Robert Durieux, équipeur motivé et amoureux des lieux depuis au moins trente ans. Autre atout de poids : le pied des voies s’ancre dans un alpage ombragé, moelleux et confortable, idéal pour un assurage serein ou des bambins remuants. Ablon, c’est le site majeur de Haute Savoie pour le cadre, la qualité des lignes et du rocher. Ambiance épicéas, oxygène, clarines et reblochon !");
        site3.setRegion("Auvergne-Rhône-Alpes");
        site3.setType(SiteType.BLOC);
        site3.setDepartment(department3);
        site3.setRockClimbing("La falaise appartient à un vallon de calcaire urgonien, parvenu jusqu’à nous sous forme de murs verticaux ou légèrement déversants, compacts et finement ciselés d’une multitude de cannelures et de picots, qui sont d’ailleurs la marque de fabrique d’Ablon. Quasiment aucun voie n’y échappe, et vos doigts s’en souviendront. Le style dominant reste les voies de continuité, et vous trouverez quelques grandes longueurs avec des prises à profusion, mais pas toutes dans le bon sens ce qui complique la lecture. Plus de 230 voies, du 4b au 8b. Pour les grimpeurs de niveau 5+ à 6b, ce sont dans les secteurs \"Ligne du temps\" et \"Résistance et liberté\" les plus adaptés. Corde de 70 m suffisante. Quelques rares voies où une corde de 80 m est nécessaire. Voies assez bien équipées, prévoir un bon jeu de dégaines. Best of \"Ingrat Nadelgrat\", LE 7a de la falaise, secteur \"Terre Mythe\". \"Picothérapie\", deux longueurs en 6a, une des plus parcourues. \"La ligne du temps\", 7b, la première voie difficile de la falaise. \"Dublabla\", 7b puis 6b, grande longueur variée.");
        site3.setAccessApproach("Se rendre à Thorens les Glières au Nord d’Annecy. Depuis Thorens, aller jusqu’au col des Glières. Une fois au col, redescendre de trois cent mètres pour prendre une route sur la droite, le chemin du Collet. Continuer jusqu’en bas de la descente et au niveau de la ferme, prendre à nouveau à droite. Suivre le chemin des Eaux-Noires jusqu’au parking. Un sentier part sur la droite dans la forêt au niveau de la barrière. Attention, il est strictement interdit d’utiliser votre véhicule au-delà de cette barrière. Continuer à pied. Prévoir 40 mn de marche, juste de quoi s’imprégner de l’ambiance en mouillant un peu le tee-shirt. Quand y aller? Période idéale : mai à novembre. En général, la neige sonne le début et la fin de la période de grimpe. En été, il est possible de grimper sur certaines voies à l’ombre des arbres. Sinon, le soleil arrive tôt et la falaise passe à l’ombre vers 15 h. Il peut même faire frais en fin de journée. Attention aux orages de montagne pouvant être très violents dans ce secteur.");
        site3.setHostingRefueling("Sur le plateau des Glières : \n" +
                "Restaurant gîte Les Lanfian’nes - chez Chantal Bar, restaurant, gîte, et grande terrasse. 04.50.22.45.65 - www.lanfiannes-gite-glieres.fr. \n" +
                "Restaurant Gautard (Nathalie et Jean-Claude) Terrasse ensoleillée avec vue sur les montagnes pour une cuisine chaleureuse et familiale - 04.50.24.40.71 - www.restaurant-gautard-thorens-glieres.fr. \n" +
                "Restaurant Chez Constance. Restaurant-terrasse et spécialités savoyardes, ambiance refuge de montagne pour le gîte - 04.50.22.45.61 - www.les-glieres.fr.");
        siteService.create(site3);
    }

    @GetMapping("/sitepage/{idSite}")
    public String sitePage (@PathVariable("idSite") int idSite, Model model) {
        Site site = siteService.get(idSite);
        CommentDTO commentDTO = new CommentDTO();
        List<Comment> commentsParent = commentDAO.findParentsBySiteId(idSite);
        model.addAttribute("site", site);
        model.addAttribute("commentDTO", commentDTO);
        model.addAttribute("commentsParent", commentsParent);
        return "sitepage";
    }


    @GetMapping("/search")
    public ResponseEntity<List<Site>> searchAdvanced (@RequestParam(required = false) String department) {
        JPAQuery<Site> query = new JPAQuery<Site>(this.entityManager);
        QSite site = QSite.site;

        List<Site> sites = query.select(site).from(site).where(site.type.in(site.type).and(site.siteName.in(site.siteName))).fetch();
        System.out.println(sites);
        return  ResponseEntity.ok(sites);
    }

}
