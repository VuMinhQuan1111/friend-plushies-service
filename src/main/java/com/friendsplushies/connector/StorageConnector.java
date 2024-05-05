package com.friendsplushies.connector;

import com.amazonaws.HttpMethod;
import com.friendsplushies.model.exception.FileException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author VuLD
 * @date Dec 27, 2016
 */
@Component
public interface StorageConnector {

  /**
   * upload file to local
   *
   * @param file
   * @throws Exception
   */
  SavingResult createFile(InputStream file, String filePath, String fileName, Long fileSize, Boolean isPublic, Long productId) throws Exception;

  void removeFile(String filePath) throws Exception;

  InputStream downloadFile(String filePath) throws IOException;

  String getFileUrl(String filePath);

  String fetchFileFromUrl(String fetchedUrl, String filePath);

  String getPresignedUrl(String key, HttpMethod method);
}
