package com.friendsplushies.service.impl;

import com.amazonaws.HttpMethod;
import java.util.ArrayList;
// import java.util.ResourceBundle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.collections4.CollectionUtils;

import com.friendsplushies.connector.StorageConnector;
import com.friendsplushies.model.response.FileUrlDTO;
import com.friendsplushies.service.FileService;
import com.friendsplushies.util.ResourceBundle;

import org.springframework.stereotype.Service;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FileServiceImpl implements FileService {
    public static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

  @Autowired
  ResourceBundle resourceBundle;

  @Autowired
  StorageConnector storageConnector;

  @Override
  public String createFile(MultipartFile file, String folderPath) throws Exception {
    return storageConnector.createFile(file.getInputStream(), folderPath, file.getOriginalFilename(), file.getSize(), false).getFilePath();
  }

  @Override
  public String getFile(String fileName, String folderPath) {
    return storageConnector.getFileUrl(folderPath + File.separator + fileName);
  }

  @Override
  public List<FileUrlDTO> getFileUrls(List<String> filePaths) {
    if (CollectionUtils.isEmpty(filePaths)) {
      return null;
    }
    List<FileUrlDTO> result = new ArrayList<>();
    for (String filePath : filePaths) {
      FileUrlDTO fileUrlDTO = new FileUrlDTO(filePath, storageConnector.getFileUrl(filePath));
      result.add(fileUrlDTO);
    }
    return result;
  }

  @Override
  public void removeFiles(List<String> filePaths) throws Exception {
    if (CollectionUtils.isEmpty(filePaths)) {
      return;
    }
    for (String filePath :  filePaths) {
      storageConnector.removeFile(filePath);
    }
  }

  @Override
  public String fetchFileFromUrl(String fetchedUrl, String filePath) {
    return storageConnector.fetchFileFromUrl(fetchedUrl, filePath);
  }

  @Override
  public String getPresignedUrl(String key, HttpMethod method) {
    return storageConnector.getPresignedUrl(key, method);
  }
}
