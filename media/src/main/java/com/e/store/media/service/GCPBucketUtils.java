package com.e.store.media.service;

import com.e.store.media.exception.BadRequestException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class GCPBucketUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(GCPBucketUtils.class);
  private static final List<String> SUPPORTED_EXTENSION = Arrays.asList(".jpeg", ".jpg", ".png");

  @Value("${ggcloud.bucket.name}")
  private String BUCKET_NAME;

  @Value("${ggcloud.project-id}")
  private String PROJECT_ID;

  public String uploadFile(MultipartFile multipartFile, String fileName, String contentType) {
    try {
      String fileExe = getFileExtension(multipartFile.getOriginalFilename());
      String finalFileName = fileName + fileExe;

      Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
      BlobId blobId = BlobId.of(BUCKET_NAME, finalFileName);
      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
      Blob blob = storage.create(blobInfo, multipartFile.getBytes());
      if (blob != null) {
        LOGGER.info("File successfully uploaded to Storage");
        return new StringBuilder()
            .append("https://storage.googleapis.com/")
            .append(BUCKET_NAME)
            .append("/")
            .append(finalFileName)
            .toString();
      }
    } catch (IOException e) {
      LOGGER.error("Can't get data from file " + fileName);
      return "";
    }
    return "";
  }

  private String getFileExtension(String fileName) {
    if (fileName != null && fileName.contains(".")) {
      for (var e : SUPPORTED_EXTENSION) {
        if (fileName.endsWith(e)) {
          return e;
        }
      }
    }
    throw new BadRequestException("File extension not supported " + fileName);
  }
}
