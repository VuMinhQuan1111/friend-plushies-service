package com.crms.util.cruds.mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author vuld
 * @version 1.1
 * @description abstract mapper, convert request to entity, entity to response and so fort
 * @date 14/08/2020
 */
public interface IMapper<T, R, E> {

  List<R> toResponses(List<E> entities);

  R toResponse(E entity);

  R toResponseFromObject(Object entity);

  E toEntity(T request);

  default Timestamp toTimestamp(Long timestamp) {
    return timestamp == null ? null : new Timestamp(timestamp);
  }

  default Long fromTimestamp(Timestamp timestamp) {
    return timestamp == null ? null : timestamp.getTime();
  }

}
