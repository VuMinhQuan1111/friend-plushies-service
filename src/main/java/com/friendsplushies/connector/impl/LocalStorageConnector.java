package com.friendsplushies.connector.impl;

import com.amazonaws.HttpMethod;
import com.friendsplushies.connector.SavingResult;
import com.friendsplushies.connector.StorageConnector;
import com.friendsplushies.constant.ApplicationConstant;
import com.friendsplushies.model.entity.Product;
import com.friendsplushies.repository.ProductRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trung on 3/10/17.
 */
@Profile("local")
@Component
public class LocalStorageConnector implements StorageConnector {

  @Autowired
  private ProductRepository productRepository;

  private static final Logger logger = LoggerFactory.getLogger(LocalStorageConnector.class);

  /*
  @Autowired
  private Environment environment;
   */

  @Override
  public SavingResult createFile(InputStream fileInputStream, String filePath, String fileName, Long fileSize, Boolean isPublic, Long productId) throws IOException {
    Product product = productRepository.getOne(productId);
    Path rootLocationPath = Paths.get(filePath);
    if (!Files.exists(rootLocationPath)) {
      Files.createDirectories(rootLocationPath);
//      setPermissionAndOwnerForPath(rootLocationPath);
    }
    String extension;
    String name;

    int idxOfDot = fileName.lastIndexOf('.');   //Get the last index of . to separate extension
    extension = fileName.substring(idxOfDot + 1);
    name = fileName.substring(0, idxOfDot);

    Path path = Paths.get(rootLocationPath.toString() + File.separator + fileName);
    int counter = 1;
    while (Files.exists(path)) {
      fileName = name + "(" + counter + ")." + extension;
      path = Paths.get(rootLocationPath.toString() + File.separator + fileName);
      counter++;
    }
    File file = new File(rootLocationPath.toString() + File.separator + fileName);
    product.setImageUrl(fileName);
    productRepository.save(product);
    FileUtils.copyInputStreamToFile(fileInputStream, file);
//    setPermissionAndOwnerForPath(path);
    return new SavingResult(SavingResult.SavingType.LOCAL, file.getAbsolutePath(), file.getPath());
  }

  @Override
  public void removeFile(String filePath) throws Exception {
    Path rootLocationPath = Paths.get(filePath);
    Files.deleteIfExists(rootLocationPath);
  }

  @Override
  public InputStream downloadFile(String filePath) throws IOException {
    File downloadFile = new File(filePath);
    InputStream downloadFileStream = new FileInputStream(downloadFile);
    return downloadFileStream;
  }


  @Override
  public String getFileUrl(String filePath) {
    if (StringUtils.isEmpty(filePath)) {
      return null;
    }
    Path extendPath = Paths.get(filePath);
    Path rootLocationPath = Paths.get(System.getProperty("user.home"), extendPath.toString());
    return rootLocationPath.toString();
  }

  //===== PRIVATE METHODS =====

  /**
   * set permission for file or folder
   *
   * doductrung
   */
  private void setPermissionAndOwnerForPath(Path p) {
    /*
    String groupName = environment.getProperty("storage.group");
    String ownerName = environment.getProperty("storage.owner");
    String permission = environment.getProperty("storage.permission");
    if (StringUtils.isEmpty(groupName)) {
      groupName = "www-data";
    }
    if (StringUtils.isEmpty(ownerName)) {
      ownerName = "root";
    }
    if (StringUtils.isEmpty(permission) || StringUtils.length(permission) > 3 || StringUtils.length(permission) < 3) {
      permission = "774";
    }
    UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
    try {
      GroupPrincipal group = lookupService.lookupPrincipalByGroupName(groupName);
      UserPrincipal owner = lookupService.lookupPrincipalByName(ownerName);
      Files.getFileAttributeView(p, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)
          .setGroup(group);
      Files.getFileAttributeView(p, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)
          .setOwner(owner);
    } catch (IOException e) {
      logger.error("An error occur when set owner for file or folder", e);
    }
    Set<PosixFilePermission> perms = new HashSet<>();
    perms.addAll(returnPermissionFromNumber(permission.charAt(0), 1));
    perms.addAll(returnPermissionFromNumber(permission.charAt(1), 2));
    perms.addAll(returnPermissionFromNumber(permission.charAt(2), 3));
    try {
      Files.setPosixFilePermissions(p, perms);
    } catch (IOException e) {
      logger.error("An error occur when set permission for file or folder", e);
    }
     */
  }

  /**
   * get list permission for group, owner, other base on number
   */
  private List<PosixFilePermission> returnPermissionFromNumber(char numberStr, Integer position) {
    List<PosixFilePermission> listPermission = new ArrayList<>();
    Integer number;
    try {
      number = Integer.valueOf(String.valueOf(numberStr));
    } catch (NumberFormatException ex) {
      logger.error("Parse number failed when returnPermissionFromNumber");
      return listPermission;
    }
    if (position.compareTo(1) == 0) {
      switch (number) {
        case 1: {
          listPermission.add(PosixFilePermission.OWNER_EXECUTE);
          break;
        }
        case 2: {
          listPermission.add(PosixFilePermission.OWNER_WRITE);
          break;
        }
        case 3: {
          listPermission.add(PosixFilePermission.OWNER_WRITE);
          listPermission.add(PosixFilePermission.OWNER_EXECUTE);
          break;
        }
        case 4: {
          listPermission.add(PosixFilePermission.OWNER_READ);
          break;
        }
        case 5: {
          listPermission.add(PosixFilePermission.OWNER_READ);
          listPermission.add(PosixFilePermission.OWNER_EXECUTE);
          break;
        }
        case 6: {
          listPermission.add(PosixFilePermission.OWNER_READ);
          listPermission.add(PosixFilePermission.OWNER_WRITE);
          break;
        }
        case 7: {
          listPermission.add(PosixFilePermission.OWNER_READ);
          listPermission.add(PosixFilePermission.OWNER_WRITE);
          listPermission.add(PosixFilePermission.OWNER_EXECUTE);
          break;
        }
      }
    } else if (position.compareTo(2) == 0) {
      switch (number) {
        case 1: {
          listPermission.add(PosixFilePermission.GROUP_EXECUTE);
          break;
        }
        case 2: {
          listPermission.add(PosixFilePermission.GROUP_WRITE);
          break;
        }
        case 3: {
          listPermission.add(PosixFilePermission.GROUP_WRITE);
          listPermission.add(PosixFilePermission.GROUP_EXECUTE);
          break;
        }
        case 4: {
          listPermission.add(PosixFilePermission.GROUP_READ);
          break;
        }
        case 5: {
          listPermission.add(PosixFilePermission.GROUP_READ);
          listPermission.add(PosixFilePermission.GROUP_EXECUTE);
          break;
        }
        case 6: {
          listPermission.add(PosixFilePermission.GROUP_READ);
          listPermission.add(PosixFilePermission.GROUP_WRITE);
          break;
        }
        case 7: {
          listPermission.add(PosixFilePermission.GROUP_READ);
          listPermission.add(PosixFilePermission.GROUP_WRITE);
          listPermission.add(PosixFilePermission.GROUP_EXECUTE);
          break;
        }
      }
    } else if (position.compareTo(3) == 0) {
      switch (number) {
        case 1: {
          listPermission.add(PosixFilePermission.OTHERS_EXECUTE);
          break;
        }
        case 2: {
          listPermission.add(PosixFilePermission.OTHERS_WRITE);
          break;
        }
        case 3: {
          listPermission.add(PosixFilePermission.OTHERS_WRITE);
          listPermission.add(PosixFilePermission.OTHERS_EXECUTE);
          break;
        }
        case 4: {
          listPermission.add(PosixFilePermission.OTHERS_READ);
          break;
        }
        case 5: {
          listPermission.add(PosixFilePermission.OTHERS_READ);
          listPermission.add(PosixFilePermission.GROUP_EXECUTE);
          break;
        }
        case 6: {
          listPermission.add(PosixFilePermission.OTHERS_READ);
          listPermission.add(PosixFilePermission.OTHERS_WRITE);
          break;
        }
        case 7: {
          listPermission.add(PosixFilePermission.OTHERS_READ);
          listPermission.add(PosixFilePermission.OTHERS_WRITE);
          listPermission.add(PosixFilePermission.OTHERS_EXECUTE);
          break;
        }
      }
    }

    return listPermission;
  }

  @Override
  public String fetchFileFromUrl(String fetchedUrl, String filePath) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getPresignedUrl(String key, HttpMethod method) {
    throw new UnsupportedOperationException();
  }
}
