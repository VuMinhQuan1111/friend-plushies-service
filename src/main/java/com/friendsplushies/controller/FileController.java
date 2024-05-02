// package com.friendsplushies.controller;
// import com.amazonaws.HttpMethod;
// import com.friendsplushies.constant.ServicePath;
// import com.friendsplushies.model.response.FileUrlDTO;
// import com.friendsplushies.service.FileService;
// import com.friendsplushies.util.RestResult;

// import java.util.List;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// @RestController
// @RequestMapping(ServicePath.FILE)
// public class FileController extends BaseController {
    
//     public static final Logger logger = LoggerFactory.getLogger(FileController.class);

//     @Autowired
//     FileService fileService;

    

//     @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//   public ResponseEntity<RestResult<String>> uploadFile(
//       @RequestParam(value = "file") MultipartFile file,
//       @RequestParam(value = "folderPath") String folderPath) throws Exception {

//     String fileName = fileService.createFile(file, folderPath);
//     RestResult<String> restResult = new RestResult<>();
//     restResult.setData(fileName);
//     restResult.setStatus(RestResult.STATUS_SUCCESS);
//     restResult.addMessage("Upload file successfully");
//     return new ResponseEntity<>(restResult, HttpStatus.OK);
//   }

//   @GetMapping(value = ServicePath.URLS)
//   public ResponseEntity<RestResult<List<FileUrlDTO>>> getFileUrls(@RequestParam(value = "paths") List<String> filePaths) {

//     List<FileUrlDTO> fileUrlMap = fileService.getFileUrls(filePaths);
//     RestResult<List<FileUrlDTO>> restResult = new RestResult<>();
//     restResult.setData(fileUrlMap);
//     restResult.setStatus(RestResult.STATUS_SUCCESS);
//     // restResult.setMessage("Upload file successfully");
//     return new ResponseEntity<>(restResult, HttpStatus.OK);
//   }

//   @GetMapping
//   public ResponseEntity<RestResult<String>> getFile(
//       @RequestParam(value = "name") String fileName,
//       @RequestParam(value = "folderPath") String folderPath) throws Exception {
//     String path = fileService.getFile(fileName, folderPath);
//     RestResult<String> result = new RestResult<>();
//     result.setData(path);
//     result.setStatus(RestResult.STATUS_SUCCESS);
//     result.addMessage(resourceBundle.getMessage("Get file successfully"));
//     return new ResponseEntity<>(result, HttpStatus.OK);
//   }

//   @DeleteMapping
//   public ResponseEntity<RestResult> deleteFile(
//       @RequestParam(value = "filePaths") List<String> filePaths) throws Exception {
//     fileService.removeFiles(filePaths);
//     RestResult result = new RestResult();
//     result.setStatus(RestResult.STATUS_SUCCESS);
//     result.addMessage(resourceBundle.getMessage("Delete files successfully"));
//     return new ResponseEntity<>(result, HttpStatus.OK);
//   }

// //   @PostMapping("/fetch-file")
// //   public ResponseEntity<RestResult<String>> fetchFileFromUrlToS3(@RequestBody FetchFileFromUrlToS3Request request) throws Exception {
// //     RestResult<String> restResult = new RestResult<>();
// //     restResult.setData(fileService.fetchFileFromUrl(request.getFetchedUrl(), request.getFilePath()));
// //     restResult.setStatus(RestResult.STATUS_SUCCESS);
// //     restResult.setMessage("Fetch file from url successfully");
// //     return new ResponseEntity<>(restResult, HttpStatus.OK);
// //   }

//   @GetMapping("/get-presigned-url")
//   public ResponseEntity<RestResult<String>> getPresignedUrl(
//       @RequestParam String key,
//       @RequestParam HttpMethod method
//   ) throws Exception {
//     RestResult<String> restResult = new RestResult<>();
//     restResult.setData(fileService.getPresignedUrl(key, method));
//     restResult.setStatus(RestResult.STATUS_SUCCESS);
//     restResult.addMessage("Fetch file from url successfully");
//     return new ResponseEntity<>(restResult, HttpStatus.OK);
//   }
// }
