package com.friendsplushies.service;
import java.util.List;
import com.amazonaws.HttpMethod;
import com.friendsplushies.model.response.FileUrlDTO;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String createFile(MultipartFile file, String folderPath) throws Exception;

  String getFile(String fileName, String folderPath) throws Exception;

  List<FileUrlDTO> getFileUrls(List<String> filePaths);

  void removeFiles(List<String> filePaths) throws Exception;

  String fetchFileFromUrl(String fetchedUrl, String filePath);

  String getPresignedUrl(String key, HttpMethod method);
}
