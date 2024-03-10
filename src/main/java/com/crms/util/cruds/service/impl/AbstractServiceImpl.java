package com.crms.util.cruds.service.impl;

import com.crms.constant.Operator;
import com.crms.model.enumeration.ConditionType;
import com.crms.model.request.SearchRequest;
import com.crms.model.type.CustomTriple;
import com.crms.util.MappingUtil;
import com.crms.util.cruds.mapper.IMapper;
import com.crms.util.cruds.repository.IRepository;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vuld
 * @version 1.1
 * @description This abstract class implement all common method which are defined in IService
 * @date 14/08/2020
 */
public abstract class AbstractServiceImpl<T, R, E> {

  public static final Logger logger = LoggerFactory.getLogger(AbstractServiceImpl.class);
  Class<E> entityClass;
  IRepository repository;
  IMapper<T, R, E> mapper;

  public AbstractServiceImpl() {

  }

  @Autowired
  public AbstractServiceImpl(IRepository repository, IMapper<T, R, E> mapper, Class<E> entityClass) {
    this.entityClass = entityClass;
    this.repository = repository;
    this.mapper = mapper;
  }

//  /**
//   * @param parentIds
//   * @param parentIdName
//   * @param childIdName
//   * @return key: parentId value: total children of this parentId
//   */
//  public Map<Long, Long> fetchTotalChildren(Set<Long> parentIds, String parentIdName, String childIdName) {
//    List<TotalChild> totalChildren = this.repository.fetchTotalChild(parentIds, parentIdName, childIdName);
//    if (CollectionUtils.isEmpty(totalChildren)) {
//      return new HashMap<>();
//    }
//    return totalChildren.stream().collect(Collectors.toMap(TotalChild::getParentId, data -> data.getTotalChild()));
//  }

  public List<R> search(SearchRequest searchRequest) {
    List<E> list = repository.searchRequest(searchRequest);
    if (CollectionUtils.isNotEmpty(list)) {
      return mapper.toResponses(list);
    }
    return new ArrayList<>();
  }

  public Long count(SearchRequest searchRequest) {
    return repository.countRequest(searchRequest);
  }

  @Transactional
  public R create(T request) {
    E entity = mapper.toEntity(request);
    repository.save(entity);
    return mapper.toResponse(entity);
  }

  public R getOne(Long id) {
    Object entity = repository.findOne(id);
    return mapper.toResponse(entityClass.cast(entity));
  }

  /**
   * get current entity, convert current request to entity type, using MappingUtil to map change from request to entity
   *
   * @throws IllegalAccessException
   */
  @Transactional
  public R update(T request) throws IllegalAccessException, InvocationTargetException {
    Method getId;
    try {
      getId = request.getClass().getMethod("get" + entityClass.getSimpleName() + "Id");
    } catch (NoSuchMethodException e) {
      logger.warn("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
      throw new IllegalArgumentException("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
    }
    Long id = (Long) getId.invoke(request);
    Object entity = repository.findOne(id);
    if (entity == null) {
      throw new IllegalArgumentException("Not found " + entityClass.getSimpleName() + " with id : " + id);
    }
    E beforeEntity = entityClass.cast(entity);
    E requestEntity = mapper.toEntity(request);
    MappingUtil.setParameter(entityClass, requestEntity, beforeEntity);
    repository.save(beforeEntity);
    return mapper.toResponse(beforeEntity);
  }

  @Transactional
  public boolean delete(Long id) {
    Object entity = repository.findOne(id);
    if (entity == null) {
      throw new IllegalArgumentException("Not found " + entityClass.getSimpleName() + " with id : " + id);
    }
    repository.remove(entityClass.cast(entity));
    return true;
  }

  @Transactional
  public List<R> update(List<T> requests) throws IllegalAccessException, InvocationTargetException {
    if (CollectionUtils.isEmpty(requests)) {
      throw new IllegalArgumentException("Empty request");
    }
    List<Long> ids = new ArrayList<>();
    try {
      for (T request : requests) {
        Method getId = request.getClass().getMethod("get" + entityClass.getSimpleName() + "Id");
        ids.add((Long) getId.invoke(request));
      }
    } catch (NoSuchMethodException e) {
      logger.warn("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
      throw new IllegalArgumentException("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
    }
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.setConditionType(ConditionType.AND);
    CustomTriple<String, String, Object> customTriple = new CustomTriple<>();
    customTriple.setLeft(String.format("%sId", StringUtils.lowerCase(entityClass.getSimpleName())));
    customTriple.setMiddle(Operator.IN.value());
    customTriple.setRight(ids);
    searchRequest.setConditions(Arrays.asList(customTriple));
    List list = repository.searchRequest(searchRequest);
    if (CollectionUtils.isEmpty(list)) {
      throw new IllegalArgumentException("Not found " + entityClass.getSimpleName());
    }
    for (Object entity : list) {
      E beforeEntity = entityClass.cast(entity);
      try {
        Method getIdEntity = entity.getClass().getMethod("get" + entityClass.getSimpleName() + "Id");
        Long entityId = (Long) getIdEntity.invoke(entity);
        T request = requests.stream().filter(x -> {
          try {
            Method getIdRequest = x.getClass().getMethod("get" + entityClass.getSimpleName() + "Id");
            return entityId.equals((Long) getIdRequest.invoke(x));
          } catch (NoSuchMethodException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          } catch (InvocationTargetException e) {
            e.printStackTrace();
          }
          return false;
        }).findFirst().orElse(null);
        if (request != null) {
          E requestEntity = mapper.toEntity(request);
          MappingUtil.setParameter(entityClass, requestEntity, beforeEntity);
        }
      } catch (NoSuchMethodException e) {
        logger.warn("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
        throw new IllegalArgumentException("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
      }
    }
    repository.saveList(list);
    return mapper.toResponses(list);
  }

  @Transactional
  public List<R> create(List<T> requests) throws IllegalAccessException, InvocationTargetException {
    if (CollectionUtils.isEmpty(requests)) {
      throw new IllegalArgumentException("Empty request");
    }
    List<E> list = new ArrayList<>();
    requests.forEach(request -> {
      E requestEntity = mapper.toEntity(request);
      list.add(requestEntity);
    });
    repository.saveList(list);
    return mapper.toResponses(list);
  }
}
