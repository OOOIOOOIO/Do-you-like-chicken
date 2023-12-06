package com.sh.chicken.global.util.aws.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sh.chicken.global.exception.CustomException;
import com.sh.chicken.global.exception.CustomErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    private final AmazonS3Client amazonS3Client;


    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file){

        String fileUrl = "None";
        try {
            String fileName = "chicken/" + file.getOriginalFilename();
            fileUrl= "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;
            ObjectMetadata metadata= new ObjectMetadata();

            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            return fileUrl;

        } catch (IOException e) {

            log.info("[error] : " + e.getMessage());
            throw new CustomException(CustomErrorCode.FailToUploadFileToS3Exception); // 만들기
        }

    }

}
