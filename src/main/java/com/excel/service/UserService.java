package com.excel.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public interface UserService {

    ByteArrayInputStream uploadAndDownload(MultipartFile file);

    ResponseEntity uploadExcel(MultipartFile file);

    ByteArrayInputStream exportExcel();

    ResponseEntity fetchAll();
}
