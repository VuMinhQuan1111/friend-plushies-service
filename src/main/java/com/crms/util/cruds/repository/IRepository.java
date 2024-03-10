package com.crms.util.cruds.repository;

import com.crms.model.request.SearchRequest;
import java.util.List;
import java.util.Set;

/**
 * @author vuld
 * @version 1.1
 * @description interface repository, contain CRUDS methods
 * @date 14/08/2020
 */
public interface IRepository<E> {

//  List<TotalChild> fetchTotalChild(Set<Long> parentIds, String parentIdName, String childIdName);

  List<E> searchRequest(SearchRequest searchRequest);

  Long countRequest(SearchRequest searchRequest);

  E findOne(Object id);

  E save(E entity);

  List<E> saveList(List<E> entities);

  boolean remove(E entity);
}

