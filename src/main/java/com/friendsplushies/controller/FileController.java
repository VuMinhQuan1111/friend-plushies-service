package com.friendsplushies.controller;

import com.amazonaws.HttpMethod;
import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.model.response.FileUrlDTO;
import com.friendsplushies.model.response.RestResult;
import com.friendsplushies.service.FileService;
import com.friendsplushies.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ServicePath.FILE)
public class FileController {

    @Autowired
    private ResourceBundle resourceBundle;

    @Autowired
    private FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResult<String>> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "filePath") String folderPath,
            @RequestParam(value = "productId") Long productId
            ) throws Exception {
        String fileName = fileService.createFile(file, folderPath, productId);
        RestResult<String> restResult = new RestResult<>();
        restResult.data(fileName);
        restResult.status(RestResult.STATUS_SUCCESS);
        restResult.message("Upload file successfully");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @PostMapping(value = ServicePath.URLS)
    public ResponseEntity<RestResult<List<FileUrlDTO>>> getFileUrls(@RequestBody List<String> filePaths) {

        List<FileUrlDTO> fileUrlMap = fileService.getFileUrls(filePaths);
        RestResult<List<FileUrlDTO>> restResult = new RestResult<>();
        restResult.data(fileUrlMap);
        restResult.status(RestResult.STATUS_SUCCESS);
        restResult.message("Upload file successfully");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RestResult<String>> getFile(
            @RequestParam(value = "name") String fileName,
            @RequestParam(value = "folderPath") String folderPath) throws Exception {
        String path = fileService.getFile(fileName, folderPath);
        RestResult<String> result = new RestResult<>();
        result.data(path);
        result.status(RestResult.STATUS_SUCCESS);
        result.message(resourceBundle.getMessage("Get file successfully"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RestResult> deleteFile(
            @RequestParam(value = "filePaths") List<String> filePaths) throws Exception {
        fileService.removeFiles(filePaths);
        RestResult result = new RestResult();
        result.status(RestResult.STATUS_SUCCESS);
        result.message(resourceBundle.getMessage("Delete files successfully"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
