// package com.friendsplushies.connector.impl;


// import com.amazonaws.HttpMethod;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.File;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Component;
// import org.apache.commons.io.FileUtils;
// import org.apache.commons.lang3.StringUtils;
// import com.friendsplushies.connector.SavingResult;
// import com.friendsplushies.connector.StorageConnector;
// import com.friendsplushies.constant.ApplicationConstant;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Profile("local")
// @Component
// public class LocalStorageConnector implements StorageConnector {
//     private static final Logger logger = LoggerFactory.getLogger(LocalStorageConnector.class);

//   /*
//   @Autowired
//   private Environment environment;
//    */

//   @Override
//   public SavingResult createFile(InputStream fileInputStream, String filePath, String fileName, Long fileSize, Boolean isPublic) throws IOException {
//     Path rootLocationPath = Paths.get(filePath);
//     if (!Files.exists(rootLocationPath)) {
//       Files.createDirectories(rootLocationPath);
//     //   setPermissionAndOwnerForPath(rootLocationPath);
//     }
//     String extension;
//     String name;

//     int idxOfDot = fileName.lastIndexOf('.');   //Get the last index of . to separate extension
//     extension = fileName.substring(idxOfDot + 1);
//     name = fileName.substring(0, idxOfDot);

//     Path path = Paths.get(rootLocationPath.toString() + File.separator + fileName);
//     int counter = 1;
//     while (Files.exists(path)) {
//       fileName = name + "(" + counter + ")." + extension;
//       path = Paths.get(rootLocationPath.toString() + File.separator + fileName);
//       counter++;
//     }
//     File file = new File(rootLocationPath.toString() + File.separator + fileName);
//     FileUtils.copyInputStreamToFile(fileInputStream, file);
//     // setPermissionAndOwnerForPath(path);
//     return new SavingResult(SavingResult.SavingType.LOCAL, file.getAbsolutePath(), file.getPath());
//   }

//   @Override
//   public void removeFile(String filePath) throws Exception {
//     Path rootLocationPath = Paths.get(filePath);
//     Files.deleteIfExists(rootLocationPath);
//   }

//   @Override
//   public InputStream downloadFile(String filePath) throws IOException {
//     File downloadFile = new File(filePath);
//     InputStream downloadFileStream = new FileInputStream(downloadFile);
//     return downloadFileStream;
//   }


//   @Override
//   public String getFileUrl(String filePath) {
//     if (StringUtils.isEmpty(filePath)) {
//       return null;
//     }
//     String avatarPath = filePath.replace(ApplicationConstant.ROOT_STORAGE_PATH, "/upload");
//     avatarPath = ApplicationConstant.DOMAIN_PATH + avatarPath;
//     return avatarPath;
//   }

//   @Override
//   public String fetchFileFromUrl(String fetchedUrl, String filePath) {
//     throw new UnsupportedOperationException();
//   }

//   @Override
//   public String getPresignedUrl(String key, HttpMethod method) {
//     throw new UnsupportedOperationException();
//   }
// }
