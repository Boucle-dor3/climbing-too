package com.oc.climbingtoo.entity;

import com.oc.climbingtoo.enumeration.TopoType;
import lombok.Getter;
import lombok.Setter;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




@Entity
public class Topo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String picture;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private TopoType type;


}



