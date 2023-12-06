package com.sh.chicken.admin.controller;

import com.sh.chicken.admin.application.chicken.ChickenMenuUploadService;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/admin/upload")
@RequiredArgsConstructor
public class ChickenFileUploadController {

    private final ChickenMenuUploadService chickenMenuUploadService;

    @LogTrace
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("menuName") String menuName,
                                             @RequestParam("brandName") String brandName,
                                             @RequestPart(required = false) MultipartFile file) {

        log.info(menuName);
        log.info(file.getOriginalFilename());
        chickenMenuUploadService.uploadFile(menuName, brandName, file);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }


}
