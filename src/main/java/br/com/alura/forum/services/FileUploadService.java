package br.com.alura.forum.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @autor adriano rabello 31/01/2021 7:08 PM
 **/

@Service
public class FileUploadService {

    public void uploadFile(MultipartFile file) throws IOException {

        file.transferTo(new File("/home/adrianorabello/uploads/" + file.getOriginalFilename()));

    }
}
