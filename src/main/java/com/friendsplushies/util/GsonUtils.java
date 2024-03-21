package com.friendsplushies.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

/**
 * Author: chautn on 4/26/2019 10:42 AM
 */
public class GsonUtils {

  public static Gson getGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.serializeNulls();
    return builder.setPrettyPrinting().create();
  }

  public static Map toMap(Object object) {
    Gson gson = new Gson();
    return gson.fromJson(gson.toJson(object), Map.class);
  }

}
