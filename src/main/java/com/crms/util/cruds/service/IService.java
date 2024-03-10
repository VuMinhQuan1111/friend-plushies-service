package com.crms.util.cruds.service;

import com.crms.model.request.SearchRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author vuld
 * @version 1.1
 * @description interface service, contain CRUDS methods
 * @date 14/08/2020
 */
public interface IService<T, R, E> {

  List<R> search(SearchRequest searchRequest);

  Long count(SearchRequest searchRequest);

  R create(T request);

  R getOne(Long id);

  R update(T request) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

  List<R> update(List<T> requests) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

  List<R> create(List<T> requests) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

  boolean delete(Long id);

}
