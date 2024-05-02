package com.friendsplushies.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConstant {
  public static final String ACTIVE = "ACTIVE";
  public static final String DEACTIVE = "DEACTIVE";
  public static final String AUTO_GENERATE = "AUTO_GENERATE";
  String name;

  // Do not change value of these field
  // public static String ROOT_STORAGE_PATH;
  // public static String DOMAIN_PATH;
  // // public static String ACTIVE = "ACTIVE";

  // @Value("${storage.rootPath}")
  // public void setRootStoragePath(String rootStoragePath) {
  //   ROOT_STORAGE_PATH = rootStoragePath;
  // }

  // @Value("${storage.domainPath}")
  // public void setDomainPath(String domainPath) {
  //   DOMAIN_PATH = domainPath;
  // }
}
