package com.friendsplushies.util.cruds.controller;

import com.friendsplushies.constant.ServicePath;
import com.friendsplushies.controller.BaseController;
import com.friendsplushies.controller.UserController;
import com.friendsplushies.model.request.SearchRequest;
import com.friendsplushies.util.RestResult;
import com.friendsplushies.util.cruds.service.IService;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author vuld
 * @version 1.1
 * @description this abstract controller implement all CRUDS endpoint
 * @date 14/08/2020
 */
public abstract class AbstractController<T, R, E> extends BaseController {

  public static final Logger logger = LoggerFactory.getLogger(UserController.class);

  protected IService<T, R, E> service;

  public AbstractController() {
  }

  @Autowired
  public AbstractController(IService<T, R, E> service) {
    this.service = service;
  }

  @PostMapping()
  public ResponseEntity create(@RequestBody T request) {
    RestResult result = new RestResult<>();
    R response = service.create(request);
    result.ok("create success");
    result.setData(response);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping(value = ServicePath.ID)
  public ResponseEntity getOne(@PathVariable("id") Long id) {
    RestResult result = new RestResult<>();
    R response = service.getOne(id);
    result.ok("get success");
    result.setData(response);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PutMapping()
  public ResponseEntity update(@RequestBody T request) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    RestResult result = new RestResult<>();
    R response = service.update(request);
    result.ok("Update success");
    result.setData(response);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @DeleteMapping(value = ServicePath.ID)
  public ResponseEntity delete(@PathVariable(value = "id") Long id) {
    RestResult result = new RestResult<>();
    Boolean response = service.delete(id);
    result.ok("delete success");
    result.setData(response);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(value = ServicePath.SEARCH)
  public ResponseEntity search(@RequestBody SearchRequest request) {
    RestResult result = new RestResult<>();
    List<R> response = service.search(request);
    Long total = service.count(request);
    Map<String, String> metadata = new HashMap<>();
    metadata.put("total", String.valueOf(total));
    result.ok("search success");
    result.setData(response);
    result.setMetaData(metadata);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PutMapping(value = ServicePath.LIST)
  public ResponseEntity updateList(@RequestBody List<T> requests) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    RestResult result = new RestResult<>();
    List<R> response = service.update(requests);
    result.ok("Update success");
    result.setData(response);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(value = ServicePath.CREATE + ServicePath.LIST)
  public ResponseEntity createList(@RequestBody List<T> requests) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    RestResult result = new RestResult<>();
    List<R> response = service.create(requests);
    result.ok("Update success");
    result.setData(response);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
