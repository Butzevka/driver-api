package com.butzevka.driverapi.service;

import com.butzevka.driverapi.exception.FileStorageException;
import com.butzevka.driverapi.exception.MyFileNotFoundException;
import com.butzevka.driverapi.model.DBFile;
import com.butzevka.driverapi.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class DBFileStorageService {

    @Autowired
    private FileRepository fileRepository;

    public DBFile storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Nazwa pliku zawiera nieprawidłowość " + fileName);
            }
            DBFile DBFileToUpload = new DBFile(fileName, file.getContentType(), file.getBytes());
            return fileRepository.save(DBFileToUpload);
        } catch (IOException e) {
            throw new FileStorageException("Nie udało się napisać pliku "+fileName+", spróbuj ponownie!");
        }

    }

    public DBFile downloadFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("Nie znaleziono pliku o id "+id));
    }

}
