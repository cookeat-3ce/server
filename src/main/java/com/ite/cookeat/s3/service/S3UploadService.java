package com.ite.cookeat.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3UploadService {

  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String awsBucket;


  public String saveFile(MultipartFile multipartFile) throws IOException {
    String originalFilename = multipartFile.getOriginalFilename();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(multipartFile.getSize());
    metadata.setContentType(multipartFile.getContentType());

    amazonS3.putObject(awsBucket, originalFilename, multipartFile.getInputStream(), metadata);
    return amazonS3.getUrl(awsBucket, originalFilename).toString();
  }

  public UrlResource downloadFile(String originalFilename) {
    UrlResource urlResource = new UrlResource(amazonS3.getUrl(awsBucket, originalFilename));
    return urlResource;
  }

  public void deleteFile(String originalFilename) {
    amazonS3.deleteObject(awsBucket, originalFilename);
  }

}