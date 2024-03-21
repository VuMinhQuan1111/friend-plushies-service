package com.friendsplushies.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneUtils {

  public static final Logger logger = LoggerFactory.getLogger(PhoneUtils.class);

  public static Set<String> fixCorrectPhone(Set<String> phoneSet) {
    List<String> phones = new ArrayList<>(phoneSet);
    Set<String> result = new HashSet<>();
    for (int i = 0; i < phones.size(); i++) {
      String tempPhone = phones.get(i).replaceAll("(@\"[\\r\\n]*.*)", "")
          .replace(" ", "")
          .replace("  ", "")
          .replace("(", "")
          .replace(")", "")
          .replace("-", "")
          .replace("^", "")
          .trim();
      if (StringUtils.isEmpty(tempPhone)) {
        continue;
      }
      String newPhone = fixPhoneNumber(tempPhone);
      if (StringUtils.isEmpty(newPhone)) {
        logger.warn("Does not type of phone number" + phones.get(i));
        continue;
      }
      result.add(newPhone);
    }
    return result;
  }

  public static String mergePhone(Set<String> phoneSet) {
    List<String> phones = new ArrayList<>(phoneSet);
    String refPhone = "";
    refPhone += String.join("; ", phones);
    return refPhone;
  }


  public static String getFirstPhone(Set<String> phoneSet) {
    List<String> phones = new ArrayList<>(phoneSet);
    String result = "";
    if (CollectionUtils.isNotEmpty(phones)) {
      for (int i = 0; i < phones.size(); i++) {
        if (phones.get(i).length() == 8) {
          result = phones.get(i);
        }
      }
    }
    return result;
  }

  public static String getFirstMobilePhone(Set<String> phoneSet) {
    List<String> phones = new ArrayList<>(phoneSet);
    String result = "";
    if (CollectionUtils.isNotEmpty(phones)) {
      for (int i = 0; i < phones.size(); i++) {
        if (phones.get(i).length() == 10) {
          result = phones.get(i);
        }
      }
    }
    return result;
  }

  public static List<String> fixPhoneCL(List<String> phones) {
    List<String> result = new ArrayList<>();
    for (String phone : phones) {
      phone = phone.replaceAll("(@\"[\\r\\n]*.*)", "")
          .replace(" ", "")
          .replace("  ", "")
          .replace("(", "")
          .replace(")", "")
          .replace("-", "")
          .replace("^", "")
          .trim();
      String fixed = fixPhoneNumber(phone);
      result.add(fixed);
    }
    return result;
  }

  private static String fixPhoneNumber(String tempPhone) {
    if (StringUtils.isEmpty(tempPhone)) {
      return "";
    }
    String newPhone = tempPhone;
    if (tempPhone.length() == 10) {
      if ((tempPhone.startsWith("4") || tempPhone.startsWith("2")) && tempPhone.endsWith("0")) {
        newPhone = '0' + tempPhone.substring(0, tempPhone.length() - 1);
      }
      if (newPhone.startsWith("02")) {
        newPhone = newPhone.substring(2);
      }
//      if (newPhone.startsWith("04")) {
//        newPhone = tempPhone;
//      }
    } else if (tempPhone.length() == 9) {
      if (tempPhone.charAt(0) == '2') {
        newPhone = tempPhone.substring(1);
      } else if (tempPhone.charAt(0) == '4') {
        newPhone = '0' + tempPhone;
      }
    } else if (tempPhone.length() == 8) {
      newPhone = tempPhone;
    } else {
      logger.warn("Can not handle phone number" + newPhone);
      return "";
    }
    return newPhone;
  }

  public static MutablePair<String, Integer> getFirstMobilePhoneCL(List<String> phones) {
    for (int i = 0; i < phones.size(); i++) {
      if (phones.get(i).length() == 10) {
        return new MutablePair<>(phones.get(i), i);
      }
    }
    return null;
  }

  public static MutablePair<String, Integer> getFirstPhoneCL(List<String> phones) {
    for (int i = 0; i < phones.size(); i++) {
      if (phones.get(i).length() == 8) {
        return new MutablePair<>(phones.get(i), i);
      }
    }
    return null;
  }

  public static Map<String, String> getPhoneType(List<String> phones) {
    Map<String, String> result = new HashMap<>();
    for (String tempPhone : phones) {
      if (StringUtils.isEmpty(tempPhone)) {
        continue;
      }
      String newPhone = tempPhone;
      //  handle phone string with length 10
      if (tempPhone.length() == 10) {
        //  handle case when number 0 falsely put to the end of phone string
        if ((tempPhone.startsWith("4") || tempPhone.startsWith("2")) && tempPhone.endsWith("0")) {
          newPhone = '0' + tempPhone.substring(0, tempPhone.length() - 1);
        }
        //  handle case landline phone with area code, remove area code
        if (newPhone.startsWith("02")) {
          result.put(newPhone.substring(2), "PHONE");
        }

        //  handle case mobile phone
        if (newPhone.startsWith("04")) {
          result.put(newPhone, "MPHONE");
        }
      } else if (tempPhone.length() == 9) {     //  handle phone string with length 9 (number 0 is missing)
        //  handle case landline phone
        if (tempPhone.startsWith("2")) {
          newPhone = tempPhone.substring(1);
          result.put(newPhone, "PHONE");
        } else if (tempPhone.startsWith("4")) { //  handle case mobile phone
          newPhone = '0' + tempPhone;
          result.put(newPhone, "MPHONE");
        }
      } else if (tempPhone.length() == 8) {     //  handle phone string with length 8 then it is landline phone
        result.put(tempPhone, "PHONE");
      }

      if (!result.containsKey(tempPhone)) {
        logger.warn("Set default PHONE for phone : " + tempPhone);
        result.put(tempPhone, "PHONE");
      }
    }
    return result;
  }
}



