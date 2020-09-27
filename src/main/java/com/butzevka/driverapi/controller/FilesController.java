package com.butzevka.driverapi.controller;

import com.butzevka.driverapi.dto.FileDto;
import com.butzevka.driverapi.dto.ResponseMessageDto;
import com.butzevka.driverapi.service.FilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FilesController {

    private final FilesStorageService filesStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessageDto> uploadFiles(@RequestParam("files")MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                filesStorageService.store(file);
                fileNames.add(file.getOriginalFilename());
            });
            message = "Pliki załadowano pomyślnie: " + fileNames;
            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.OK);
        } catch (Exception e) {
            message = "Nie udało się załadować plików";
            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileDto>> getAllFiles() {
        List<FileDto> files = filesStorageService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString())
                    .build().toString();
            return new FileDto(fileName, url);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        Resource file = filesStorageService.loadAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}
