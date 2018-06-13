package com.oc.climbingtoo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.oc.climbingtoo.exception.InvalidFileExtensionException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {

    private final Path rootLocation = Paths.get("upload-dir");

    public String store(MultipartFile file) throws IOException, InvalidFileExtensionException {
        String newFileName = this.generateRandomString()+ "."+ FilenameUtils.getExtension(file.getOriginalFilename());
        if (this.isValidExtension(file)) {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(newFileName));
            return newFileName;
        } else {
            throw new InvalidFileExtensionException();
        }
    }

    private Boolean isValidExtension(MultipartFile file) {
        String fileExtension[] = {"jpeg", "png", "jpg", "jp2"};
        for (String element : fileExtension) {
            if (element.equals(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                return true;
            }
        }
        return false;
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
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

    private String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

}
