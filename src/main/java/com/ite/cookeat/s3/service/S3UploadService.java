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

/**
 * S3 이미지 업로드 Service
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.24
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.24    박유진       최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class S3UploadService {

  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String awsBucket;

  /**
   * 파일을 지정된 S3 버킷에 저장
   *
   * @param multipartFile 업로드할 파일
   * @return 업로드된 파일의 URL
   * @throws IOException 파일 업로드 중 입출력 오류가 발생할 경우
   */
  public String saveFile(MultipartFile multipartFile) throws IOException {
    String originalFilename = multipartFile.getOriginalFilename();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(multipartFile.getSize());
    metadata.setContentType(multipartFile.getContentType());

    amazonS3.putObject(awsBucket, originalFilename, multipartFile.getInputStream(), metadata);
    return amazonS3.getUrl(awsBucket, originalFilename).toString();
  }

  /**
   * S3에서 파일 다운로드
   *
   * @param originalFilename 다운로드할 파일의 이름
   * @return 파일의 URL을 포함한 UrlResource 객체
   */
  public UrlResource downloadFile(String originalFilename) {
    UrlResource urlResource = new UrlResource(amazonS3.getUrl(awsBucket, originalFilename));
    return urlResource;
  }

  /**
   * S3 버킷에서 파일을 삭제
   *
   * @param originalFilename 삭제할 파일의 이름
   */
  public void deleteFile(String originalFilename) {
    amazonS3.deleteObject(awsBucket, originalFilename);
  }

}