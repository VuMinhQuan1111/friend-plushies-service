package com.friendsplushies.util;

import com.friendsplushies.annotation.IgnorePreparingRequest;
import com.friendsplushies.constant.Operator;
import com.friendsplushies.model.type.CustomTriple;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by VuLD on 4/22/2019.
 */
public class MappingUtil {

  public static List<CustomTriple<String, String, Object>> prepareRequestV2(Class entityClass,
      Object entity) throws IllegalAccessException {
    List<CustomTriple<String, String, Object>> condition = new ArrayList<>();
    for (Field f : entityClass.getDeclaredFields()) {
      if (f.isAnnotationPresent(IgnorePreparingRequest.class)) {
        continue;
      }
      f.setAccessible(true);
      Object object = f.get(entity);
      if (object != null) {
        CustomTriple<String, String, Object> triple = new CustomTriple<>(f.getName(),
            Operator.EQUAL.value(), object);
        condition.add(triple);
      }
    }
    return condition;
  }

  public static List<Triple<String, String, Object>> prepareRequest(Class entityClass,
      Object entity, String prefix) throws IllegalAccessException {
    List<Triple<String, String, Object>> condition = new ArrayList<>();
    for (Field f : entityClass.getDeclaredFields()) {
      if (f.isAnnotationPresent(IgnorePreparingRequest.class)) {
        continue;
      }
      f.setAccessible(true);
      Object object = f.get(entity);
      if (object != null) {
        Triple<String, String, Object> triple = new MutableTriple<>(prefix + "." + f.getName(),
            Operator.EQUAL.value(), object);
        condition.add(triple);
      }
    }
    return condition;
  }

  public static void setParameter(Class entityClass, Object fromEntity, Object toEntity)
      throws IllegalAccessException {
    for (Field f : entityClass.getDeclaredFields()) {
      f.setAccessible(true);
      Object object = f.get(fromEntity);
      if (object != null && !Modifier.isFinal(f.getModifiers())) {
        if (f.getType().equals(Set.class)) {
          ((Set) f.get(toEntity)).clear();
          ((Set) f.get(toEntity)).addAll((Set) f.get(fromEntity));
        } else {
          f.set(toEntity, f.get(fromEntity));
        }
      }
    }
  }

  public static Long convertToLong(Object o) {
    if (o == null) {
      return null;
    }
    String stringToConvert = String.valueOf(o);
    return Long.parseLong(stringToConvert);
  }

  public static Double convertToDouble(Object o) {
    if (o == null) {
      return null;
    }
    String stringToConvert = String.valueOf(o);
    return Double.parseDouble(stringToConvert);
  }

  public static Timestamp convertToTimestamp(Object o) {
    if (o == null) {
      return null;
    }
    String stringToConvert = String.valueOf(o);
    return new Timestamp(Long.parseLong(stringToConvert));
  }

  public static Date toDate(String string) {
    if (StringUtils.isNotEmpty(string)) {
      try {
        return new SimpleDateFormat("dd/MM/yyyy").parse(string);
      } catch (ParseException e) {
        return null;
      }
    }
    return null;
  }

  public static String fromDate(Date date) {
    if (date != null) {
      return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    return null;
  }
}
