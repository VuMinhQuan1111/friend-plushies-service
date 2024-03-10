package com.crms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

  public static final Pattern PATTERN_NUMBER = Pattern.compile("^\\d+$");

  public static final String SING_PHONE_NUMBER_FORMAT = "+65%s";
  public static final String SING_HOME_PHONE_NUMBER_FORMAT = "+65%s";

  /* FORMAT */
  public static final String SING_PHONE_CODE = "65";
  public static final String SING_HOME_PHONE_CODE = "65";
  public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


  public static String makeTrimAndLowerCase(String string) {
    return string.trim().toLowerCase();
  }

  /**
   * create md5 string
   */
  public static String md5(String text) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(text.getBytes());
      byte[] byteData = md.digest();
      StringBuilder sb = new StringBuilder();
      for (byte aByteData : byteData) {
        sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
  }

  /**
   * encode string base64
   */
  public static String base64Encode(String str) {
    Base64.Encoder encoder = Base64.getMimeEncoder();
    byte[] encoded = encoder.encode(str.getBytes());
    return new String(encoded);
  }

  /**
   * base 64 decode
   */
  public static String base64Decode(String str) {
    Base64.Decoder decoder = Base64.getMimeDecoder();
    byte[] decoded = decoder.decode(str.getBytes());
    return new String(decoded, StandardCharsets.UTF_8);
  }

  /**
   * get full stacktrace of exception
   */
  public static String getStackTrace(Throwable aThrowable) {
    Writer result = new StringWriter();
    PrintWriter printWriter = new PrintWriter(result);
    aThrowable.printStackTrace(printWriter);
    return result.toString();
  }

  /**
   * validate email
   */
  public static boolean validateEmail(String email) {
    String regexStr = "^[_A-Za-z0-9-]+(?:\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    Pattern pattern = Pattern.compile(regexStr);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * validate uuid
   */
  public static boolean validateUUID(String uuidString) {
    if (uuidString == null) {
      return false;
    }
    try {
      UUID fromStringUUID = UUID.fromString(uuidString);
      String toStringUUID = fromStringUUID.toString();
      return toStringUUID.equals(uuidString);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * create file url with file id
   */
  public static String getFileURL(String fileId, String domain, String fileApi) {
    return domain + fileApi + "/" + fileId;
  }


  public static String removeAllSpecialCharFromString(String s) {
    if (StringUtils.isEmpty(s)) {
      return StringUtils.EMPTY;
    }
    String result = s.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s+", " ");
    return result.trim();
  }


  public static String convertStreamToString(InputStream inputStream) {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(inputStream));
      String result = br.lines().collect(Collectors.joining("\n"));
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  public static String hideEmail(String email) {
    return email.replaceAll("(?<=.{2}).(?=[^@]*?.@)", "*");
  }

  public static String hidePhoneNumber(String phoneNumber) {
    return phoneNumber.replaceAll("(?<=.{4}).(?=.*.{2}$)", "*");
  }

  public static String toHex(String input) {
    StringBuffer buffer = new StringBuffer();
    int intValue;
    for (int x = 0; x < input.length(); x++) {
      int cursor = 0;
      intValue = input.charAt(x);
      String binaryChar = new String(Integer.toBinaryString(input.charAt(x)));
      for (int i = 0; i < binaryChar.length(); i++) {
        if (binaryChar.charAt(i) == '1') {
          cursor += 1;
        }
      }
      buffer.append(Integer.toHexString(intValue));
    }
    return buffer.toString();
  }

  public static String generateCode(int count) {
    StringBuilder builder = new StringBuilder();
    while (count-- != 0) {
      int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
      builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    return builder.toString();
  }

  public static String removeSlash(String path) {
    if (org.apache.commons.lang3.StringUtils.isNotEmpty(path)) {
      // remove first / if exist
      if (path.charAt(0) == '/') {
        path = path.replaceFirst("/", org.apache.commons.lang3.StringUtils.EMPTY);
      }
      // remove last / if exist
      if (path.charAt(path.length() - 1) == '/') {
        path = org.apache.commons.lang3.StringUtils.substring(path, 0, path.length() - 1);
      }
    }
    return path;
  }

  public static String convertVietnamText(String str) {
    str = str.toLowerCase();
    str = str.trim();
    str = str.replaceAll( " ", "-");
    str = str.replaceAll( "đ", "d");
    str = deAccent(str);
    str = str.replaceAll("[^a-zA-Z0-9]+","-");
    return str;
  }

  public static String deAccent(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(nfdNormalizedString).replaceAll("");
  }
}
