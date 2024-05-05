package com.friendsplushies.service.impl;

import com.amazonaws.HttpMethod;
import com.friendsplushies.connector.StorageConnector;
import com.friendsplushies.model.response.FileUrlDTO;
import com.friendsplushies.service.FileService;
import com.friendsplushies.util.ResourceBundle;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {

  private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
  private final StorageConnector storageConnector;

  @Autowired
  ResourceBundle resourceBundle;


  @Autowired
  public FileServiceImpl(StorageConnector storageConnector) {
    this.storageConnector = storageConnector;
  }

  @Override
  public String createFile(MultipartFile file, String folderPath, Long productId) throws Exception {
    return storageConnector.createFile(file.getInputStream(), folderPath, file.getOriginalFilename(), file.getSize(), false, productId).getFilePath();
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
}

