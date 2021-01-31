package br.com.alura.forum.controllers;


import br.com.alura.forum.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @autor adriano rabello 31/01/2021 7:08 PM
 **/

@RestController
@RequestMapping(value = "/upload")
public class UploadFileController {


    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {


        fileUploadService.uploadFile(file);
    }
}
