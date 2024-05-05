package com.friendsplushies.service;

import com.amazonaws.HttpMethod;
import com.friendsplushies.model.exception.FileException;
import com.friendsplushies.model.response.FileResponse;
import com.friendsplushies.model.response.FileUrlDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by thanhtrung on 10/3/19
 */
public interface FileService {

  String createFile(MultipartFile file, String folderPath, Long productId) throws Exception;

  String getFile(String fileName, String folderPath) throws Exception;

  List<FileUrlDTO> getFileUrls(List<String> filePaths);

  void removeFiles(List<String> filePaths) throws Exception;
}
