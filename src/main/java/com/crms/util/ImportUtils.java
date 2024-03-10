package com.crms.util;

import com.crms.common.BadRequestException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ImportUtils {

  public static final Logger logger = LoggerFactory.getLogger(ImportUtils.class);

  public static List<List<String>> fetchDataFromExcel(int maxIndex, InputStream fileInputStream)
      throws IOException {
    ZipSecureFile.setMinInflateRatio(0);
    Workbook workbook = new XSSFWorkbook(fileInputStream);
    Sheet sheet = workbook.getSheetAt(0);
    List<List<String>> data = new ArrayList<>();
    for (Row row : sheet) {
      List<String> cellValues = new ArrayList<>();
      for (int j = 0; j < maxIndex; j++) {
        Cell cell = row.getCell(j);
        if (cell != null) {
          switch (cell.getCellTypeEnum()) {
            case STRING:
              cellValues.add(cell.getRichStringCellValue().getString());
              break;
            case NUMERIC:
              if (DateUtil.isCellDateFormatted(cell)) {
                cellValues.add(cell.getDateCellValue() + "");
              } else {
                cellValues.add(cell.getNumericCellValue() + "");
              }
              break;
            case BOOLEAN:
              cellValues.add(cell.getBooleanCellValue() + "");
              break;
            case FORMULA:
              cellValues.add(cell.getCellFormula() + "");
              break;
            default:
              cellValues.add(" ");
          }
        } else {
          cellValues.add(" ");
        }
      }
      if (CollectionUtils.isNotEmpty(cellValues)) {
        data.add(cellValues);
      }
    }

    // remove column name
    data.remove(0);
    return data;
  }

  public static boolean compareString(String left, String right) {
    left = StringUtils.isNotEmpty(left) ? left.trim().toLowerCase() : null;
    right = StringUtils.isNotEmpty(right) ? right.trim().toLowerCase() : null;
    return org.apache.commons.lang3.StringUtils.compare(left, right) == 0;
  }

  public static Timestamp setTimestamp(String value) {
    if (StringUtils.isNotBlank(value)) {
      try {
        Date date1 = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy").parse(value);
        return new Timestamp(date1.getTime());
      } catch (ParseException e) {
        logger.error("Cannot convert to Timestamp " + value);
        return null;
      }
    }
    return null;
  }

  public static Integer setValue(String value) {
    Integer result = 0;
    if (StringUtils.isNotBlank(value) && !value.equalsIgnoreCase("-")) {
      value = value.replaceAll(",", "");
      try {
        if (value.contains(".")) {
          return (int) Double.parseDouble(value);
        }
        return Integer.valueOf(value);
      } catch (Exception e) {
        logger.error("Cannot convert value to Integer " + value);
      }
    }
    return result;
  }


  public static BigDecimal setPrice(String value) {
    if (StringUtils.isNotBlank(value)) {
      value = value.toLowerCase();
//      value = value.replaceAll("\\$", "");
//      value = value.replaceAll("₫", "");
      value = value.replaceAll("\\p{Zs}", "");
      if (NumberUtils.isNumber(value)) {
        return new BigDecimal(value.trim());
      } else {
        logger.error("Cannot convert value to BigDecimal " + value);
      }
    }
    return BigDecimal.ZERO;
  }

  public static void validate(MultipartFile file) {
    String originalName = file.getOriginalFilename();
    if (originalName == null) {
      throw new BadRequestException("file upload không đúng định dạng");
    }
    int lastIndexOfDot = originalName.lastIndexOf(".");
    if (lastIndexOfDot < 0) {
      throw new BadRequestException("file upload không đúng định dạng");
    }
  }

  public static Integer setCustomer(String value) {
    Integer result = 0;
    if (StringUtils.isNotBlank(value)) {
      try {
        return Integer.parseInt(value);

      } catch (Exception e) {
        logger.error("Can not convert to Integer: " + value);
      }
    }
    return result;
  }
}



