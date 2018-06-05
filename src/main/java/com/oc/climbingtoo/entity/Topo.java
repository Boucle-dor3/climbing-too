package com.oc.climbingtoo.entity;

import com.oc.climbingtoo.enumeration.TopoType;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Logger;

import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.criteria.Path;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Entity
public class Topo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @Service
    public class StorageService {



        Logger log = LoggerFactory.getLogger(this.getClass().getName());
        private final Path rootLocation = (Path) Paths.get("upload-dir");

        public void store(MultipartFile file){
            try {
                Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
        }

        public ServerProperties.Tomcat.Resource loadFile(String filename) {
            try {
                Path file = rootLocation.resolve(filename);
                Resource resource;
                resource = new UrlResource(file.toUri());
                if(resource.exists() || resource.isReadable()) {
                    return resource;
                }else{
                    throw new RuntimeException("FAIL!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("FAIL!");
            }
        }

        public void deleteAll() {
            FileSystemUtils.deleteRecursively(rootLocation.toFile());
        }

        public void init() {
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize storage!");
            }
        }
    }
}



