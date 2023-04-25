package com.excel.controller;

import com.excel.enums.FileType;
import com.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/userController/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "upload-and-download", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAndDownload(@RequestPart(value = "file") MultipartFile multipartFile) {
        if (!Objects.requireNonNull(multipartFile.getContentType()).equalsIgnoreCase(FileType.ContentType.getContentType())) {
            throw new RuntimeException("Kindly upload only excel file");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=".concat("Error.xlsx"))
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(userService.uploadAndDownload(multipartFile)));
    }


    @PostMapping(value = "uploadExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadExcel(@RequestPart(value = "file") MultipartFile multipartFile) {
        if (!Objects.requireNonNull(multipartFile.getContentType()).equalsIgnoreCase(FileType.ContentType.getContentType())) {
            throw new RuntimeException("Kindly upload only excel file");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.uploadExcel(multipartFile));
    }

    @GetMapping("exportExcel")
    public ResponseEntity<?> exportExcel() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=".concat("Error.xlsx"))
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(userService.exportExcel()));
    }

    @GetMapping("fetch-all")
    public ResponseEntity<?> fetchAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.fetchAll());
    }
}
