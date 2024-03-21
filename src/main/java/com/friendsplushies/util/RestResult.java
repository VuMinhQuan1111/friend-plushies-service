package com.friendsplushies.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author vu@investidea.tech
 */
public class RestResult<T> {

  public static final String STATUS_SUCCESS = "success";
  public static final String STATUS_ERROR = "error";

  private String status;

  private List<String> messages;

  private T data;

  private Map<String, String> metaData;

  /**
   * Constructor
   */
  public RestResult() {
    messages = new ArrayList<>();
    status = STATUS_SUCCESS;
  }

  /**
   * Create new RestResult object
   */
  public static <T> RestResult<T> create(Class<T> genericType) {
    return new RestResult<T>();
  }

  /**
   * Create new RestResult object with status success
   */
  public static <T> RestResult<T> ok(Class<T> type) {
    RestResult<T> result = new RestResult<T>();
    result.status = STATUS_SUCCESS;
    return result;
  }

  /**
   * Create new RestResult object with status success
   */
  public static <T> RestResult<T> ok(Class<T> type, String message, T data) {
    RestResult<T> result = new RestResult<T>();
    result.status = STATUS_SUCCESS;
    result.addMessage(message);
    result.data = data;
    return result;
  }

  /**
   * set object RestResult to success and add message
   */
  public void ok(String message) {
    this.status = STATUS_SUCCESS;
    addMessage(message);
  }

  /**
   * set object RestResult to success and add message
   */
  public void ok(String message, T data) {
    this.status = STATUS_SUCCESS;
    addMessage(message);
    this.data = data;
  }

  /**
   * Create new RestResult object with status error
   */
  public static <T> RestResult<T> fail(Class<T> type) {
    RestResult<T> result = new RestResult<T>();
    result.status = STATUS_ERROR;
    return result;
  }

  public void fail(String message) {
    this.status = STATUS_ERROR;
    addMessage(message);
  }

  public String getStatus() {
    return status;
  }

  public RestResult<T> setStatus(String status) {
    this.status = status;
    return this;
  }

  public List<String> getMessages() {
    return messages;
  }

  public RestResult<T> setMessages(List<String> messages) {
    if (messages != null) {
      if (this.messages == null) {
        this.messages = new ArrayList<>();
      }
      this.messages.addAll(messages);
    }
    return this;
  }

  public RestResult<T> addMessage(String message) {
    if (StringUtils.isNotBlank(message)) {
      if (this.messages == null) {
        this.messages = new ArrayList<>();
      }
      this.messages.add(message);
    }
    return this;
  }

  public RestResult<T> addMessages(String[] messages) {
    if (ArrayUtils.isNotEmpty(messages)) {
      if (this.messages == null) {
        this.messages = new ArrayList<>();
      }
      this.messages.addAll(Arrays.asList(messages));
    }
    return this;
  }

  public T getData() {
    return data;
  }

  public static <T> T getRestResultData(Object object, Class<T> clazz) {
    return new GsonBuilder().create().fromJson(new GsonBuilder().create().toJson(object), clazz);
  }

  public static <T> List<T> getRestResultListData(Object object, Class<T[]> clazz) {
    return Arrays.asList(new Gson().fromJson(new GsonBuilder().create().toJson(object), clazz));
  }

  public RestResult<T> setData(T data) {
    this.data = data;
    return this;
  }

  public RestResult<T> addError(String error) {
    this.status = STATUS_ERROR;
    this.addMessage(error);
    return this;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public void setMetaData(Map<String, String> metaData) {
    this.metaData = metaData;
  }
}
