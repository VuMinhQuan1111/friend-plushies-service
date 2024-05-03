package com.friendsplushies.util.cruds.repository.impl;

import static com.friendsplushies.constant.Operator.IN;
import static com.friendsplushies.constant.Operator.LIKE;
import static com.friendsplushies.model.enumeration.ConditionType.AND;

import com.friendsplushies.constant.Join;
import com.friendsplushies.constant.Operator;
import com.friendsplushies.constant.Order;
import com.friendsplushies.constant.TableAlias;
import com.friendsplushies.model.enumeration.ConditionType;
import com.friendsplushies.model.enumeration.DataType;
import com.friendsplushies.model.request.SearchRequest;
import com.friendsplushies.model.type.CustomTriple;
import com.friendsplushies.util.MappingUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vuld
 * @version 1.1 :q!@description This abstract class implement all common method which are defined in IRepository
 * @date 14/08/2020
 */
public abstract class AbstractRepositoryImpl<E> {

  public final Logger logger = LoggerFactory.getLogger(AbstractRepositoryImpl.class);

  @PersistenceContext
  public EntityManager entityManager;

  public Class<E> entityClass;

  public AbstractRepositoryImpl(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

//  public List<TotalChild> fetchTotalChild(Set<Long> parentIds, String parentIdName, String childIdName) {
//    String sql = "SELECT new com.crms.model.entity.TotalChild(dt." + parentIdName + ", count(dt." + childIdName + ")) "
//        + " FROM " + entityClass.getSimpleName() + " dt WHERE dt." + parentIdName + " IN :parentIds GROUP BY dt." + parentIdName;
//    Query query = query(sql);
//    query.setParameter("parentIds", parentIds);
//    return query.getResultList();
//  }

  /**
   * abstract search by request
   */
  public List<E> searchRequest(SearchRequest searchRequest) {
    try {
      return createQuery(searchRequest, false).getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  /**
   * abstract count by request
   */
  public Long countRequest(SearchRequest searchRequest) {
    try {
      return (Long) createQuery(searchRequest, true).getSingleResult();
    } catch (NoResultException e) {
      return 0L;
    }
  }

  /**
   * abstract get one by id
   */
  public E findOne(Object id) {
//    logger.info("Abstract Repository is called");
    return entityManager.find(this.entityClass, id);
  }

  /**
   * abstract remove one by id
   */
  public boolean remove(E entity) {
    entityManager.remove(entity);
    return true;
  }

  /**
   * abstract save all
   */
  public List<E> saveList(List<E> entities) {
    if (CollectionUtils.isEmpty(entities)) {
      return new ArrayList<>();
    }
    for (E entity : entities) {
      saveItem(entity);
    }
    return entities;
  }

  private E saveItem(E entity) {
    Method getId;
    try {
      getId = entity.getClass().getMethod("get" + entityClass.getSimpleName() + "Id");
    } catch (NoSuchMethodException e) {
      logger.warn("Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
      throw new IllegalArgumentException(
          "Class " + entityClass.getSimpleName() + " dont have get*Class*Id() method");
    }
    Long id = null;
    try {
      id = (Long) getId.invoke(entity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    if (id != null) {
      entityManager.persist(entity);
      return entity;
    } else {
      return entityManager.merge(entity);
    }
  }

  private Query createQuery(SearchRequest searchRequest, Boolean isCounting) {
    StringBuilder sql = new StringBuilder();
    if (isCounting) {
      sql.append("SELECT count(o) FROM ");
    } else {
      sql.append("SELECT o FROM ");
    }
    sql.append(this.entityClass.getSimpleName() + " o ");
    if (CollectionUtils.isNotEmpty(searchRequest.getJoins())) {
      sql.append(this.appendJoin(searchRequest.getJoins()));
    }

    if (CollectionUtils.isNotEmpty(searchRequest.getConditions()) ||
        CollectionUtils.isNotEmpty(searchRequest.getSubConditions())) {
      sql.append(" WHERE ");
      sql.append(this.appendCondition(searchRequest));
    }
    if (!isCounting) {
      sql.append(this.appendOrder(searchRequest.getOrders()));
    }
    Query query = query(sql.toString());
    this.setParams(searchRequest, query);
    this.setOffsetLimit(isCounting, searchRequest.getOffset(), searchRequest.getLimit(), query);
    return query;
  }

  public String appendJoin(List<MutablePair<String, String>> joins) {
    StringBuilder sql = new StringBuilder();
    if (CollectionUtils.isNotEmpty(joins)) {
      joins.forEach(condition -> {
        Join prefix = Join.type(condition.getLeft());
        String property = prefix.value() + TableAlias.OBJECT.alias() + "." + condition.getRight() + " " + condition.getRight();
        sql.append(property);
      // validate operator from Order and throw exception
      });
    }
    return sql.toString();
  }

  public String appendOrder(List<MutablePair<String, String>> orders) {
    StringBuilder sql = new StringBuilder();
    if (CollectionUtils.isNotEmpty(orders)) {
      sql.append(" ORDER BY ");
      if (CollectionUtils.isNotEmpty(orders)) {
        orders.forEach(condition -> {
          String property = TableAlias.OBJECT.alias() + "." + condition.getLeft();
          Order order = Order.value(condition.getRight().trim().toLowerCase());
          // validate operator from Order and throw exception
          if (order == null) {
            throw new IllegalArgumentException(
                "Order not support for type : " + condition.getRight());
          }
          sql.append(property);
          sql.append(order.value());
          sql.append(" , ");
        });
      }
      return sql.substring(0, sql.length() - 2);
    } else {
      sql.append(" ORDER BY o.createdDate desc ");
      return sql.toString();
    }
  }

  public String appendCondition(SearchRequest searchRequest) {
    List<CustomTriple<String, String, Object>> conditions = searchRequest.getConditions();
    ConditionType conditionType = searchRequest.getConditionType() == null
        ? AND : searchRequest.getConditionType();
    List<String> wheres = new ArrayList<>();

    if (CollectionUtils.isNotEmpty(conditions)) {
      // prepare numberFrom of duplicate property to increase parameters
      Map<String, Integer> propertyList = new HashMap<>();
      conditions.forEach(condition -> {
        String left = condition.getLeft();
        Integer no = propertyList.get(left);
        propertyList.put(left, no == null ? 1 : ++no);
      });

      // append condition
      List<String> conditionQueries = new ArrayList<>();
      conditions.forEach(condition -> {
        StringBuilder sql = new StringBuilder();
        // IMPROVE JOIN, add prefix of join columns
        String property;
        if (condition.getLeft().contains(".")) {
          String[] split = condition.getLeft().split("\\.");
          property = condition.getLeft();
        } else {
          property = TableAlias.OBJECT.alias() + "." + condition.getLeft();
        }

        if (StringUtils.isNotEmpty(condition.getExpression())) {
          conditionQueries.add(String.format("%s %s", property, condition.getExpression()));
          return;
        }

        Operator operator = Operator.value(condition.getMiddle().trim().toLowerCase());
        // validate operator from Operator and throw exception
        if (operator == null) {
          throw new IllegalArgumentException(
              "Condition not support for type : " + condition.getMiddle());
        }
        // no need to add lower if not like condition (improve performance)
        if (operator == LIKE) {
          sql.append("lower(").append(property).append(")");
        } else {
          sql.append(property);
        }
        sql.append(operator.value());

        if (condition.getRight() == null) {
          sql.append(" null ");
        } else {
          Integer no = propertyList.get(condition.getLeft());
          String parameter = this.formatParamName(condition.getLeft()) + no;
          propertyList.put(condition.getLeft(), --no);

          if (operator == LIKE) {
            sql.append("lower(" + ":").append(parameter).append(")");
          } else if (operator == IN) {
            sql.append("(:").append(parameter).append(")");
          } else {
            sql.append(":").append(parameter);
          }
        }
        conditionQueries.add(sql.toString());
      });
      if (CollectionUtils.isNotEmpty(conditionQueries)) {
        wheres.add(String.format("( %s )",
            String.join(String.format(" %s ",
                conditionType), conditionQueries)));
      }
    }

    if (CollectionUtils.isNotEmpty(searchRequest.getSubConditions())) {
      searchRequest.getSubConditions().forEach(condition -> wheres.add(appendCondition(condition)));
    }

    return String.format("( %s )", String.join(String.format(" %s ",
        conditionType), wheres));
  }

  public void setParams(SearchRequest searchRequest, Query query) {
    List<CustomTriple<String, String, Object>> conditions = searchRequest.getConditions();
    if (CollectionUtils.isNotEmpty(conditions)) {
      // prepare numberFrom of duplicate property to increase parameters
      Map<String, Integer> propertyList = new HashMap<>();
      conditions.forEach(condition -> {
        Integer no = propertyList.get(condition.getLeft());
        propertyList.put(condition.getLeft(), no == null ? 1 : ++no);
      });

      // set parameter
      conditions.forEach(condition -> {
        Object value = condition.getRight();
        if (value == null) {
          return;
        }
        Integer no = propertyList.get(condition.getLeft());
        String parameter = this.formatParamName(condition.getLeft()) + no;
        propertyList.put(condition.getLeft(), --no);

        Operator operator = Operator.value(condition.getMiddle().trim().toLowerCase());
        // SET condition by operator
        try {
          if (operator == LIKE) {
            query.setParameter(parameter, "%" + value + "%");
          } else if (operator == IN) {
            // IMPROVE JOIN, add prefix of join columns
            // PREPARE type of param
            Class type = this.getField(condition.getLeft(), condition.getDataType());

            // convert value to approriate type
            List items = (List) value;
            List values = new ArrayList<>();
            for (Object item : items) {
              // check if type is Enum or else set as usual
              if (ArrayUtils.isNotEmpty(type.getEnumConstants())) {
                Arrays.stream(type.getEnumConstants()).forEach(x -> {
                  if (item != null && x.toString().equalsIgnoreCase((String) item)) {
                    values.add(x);
                  }
                });
              } else {
                values.add(this.mappingParameter(type, item));
              }
            }
            query.setParameter(parameter, CollectionUtils.isNotEmpty(values) ? values : null);
          } else {
            // define type of value
            Class type = this.getField(condition.getLeft(), condition.getDataType());
            // set parameter
            query.setParameter(parameter, this.mappingParameter(type, value));
          }
        } catch (NoSuchFieldException e) {
          throw new IllegalArgumentException("No property found for condition : " + parameter);
        }
      });
    }
    if (CollectionUtils.isNotEmpty(searchRequest.getSubConditions())) {
      searchRequest.getSubConditions().forEach(condition -> setParams(condition, query));
    }
  }

  private Object mappingParameter(Class type, Object value) {
    if (type.equals(Long.class)) {
      return MappingUtil.convertToLong(value);
    } else if (type.equals(Double.class)) {
      return MappingUtil.convertToDouble(value);
    } else if (type.equals(Timestamp.class)) {
      return MappingUtil.convertToTimestamp(value);
    } else if (type.equals(Date.class)) {
      return MappingUtil.toDate(value.toString());
    } else {
      return value == null ? null : type.cast(value);
    }
  }

  /**
   * if have datatype, return type of datatype. if property have dot : check type of join class. if property havenot dot : return type of current entity class
   */
  private Class getField(String property, DataType dataType) throws NoSuchFieldException {
    Field field;
    Class type = null;
    if (dataType != null) {
      switch (dataType) {
        case LONG:
          type = Long.class;
          break;
        case DOUBLE:
          type = Double.class;
          break;
        case STRING:
          type = String.class;
          break;
        case DATE:
          type = Date.class;
          break;
        case TIMESTAMP:
          type = Timestamp.class;
          break;
      }
    } else if (property.contains(".")) {
      String[] split = property.split("\\.");
      Field joinElements = entityClass.getDeclaredField(split[0]);
      // if join element is list
      if (joinElements.getType().equals(List.class)) {
        ParameterizedType listType = (ParameterizedType) joinElements.getGenericType();
        Class<?> joinClass = (Class<?>) listType.getActualTypeArguments()[0];
        field = joinClass.getDeclaredField(split[1]);
        type = field.getType();
      } else {
        field = joinElements.getType().getDeclaredField(split[1]);
        type = field.getType();
      }
    } else {
      field = entityClass.getDeclaredField(property);
      type = field.getType();
    }
    return type;
  }

  public void setOffsetLimit(Boolean isCounting, Integer offset, Integer limit, Query query) {
    if (!isCounting && offset != null) {
      query.setFirstResult(offset);
    }

    if (!isCounting && limit != null) {
      query.setMaxResults(limit);
    }
  }

  public Query query(String sql) {
    return entityManager.createQuery(sql);
  }

  public Query query(String sql, Class clazz) {
    return entityManager.createQuery(sql, clazz);
  }

  public Query nativeQuery(String sql) {
    return entityManager.createNativeQuery(sql);
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  // ====== PRIVATE METHOD ========
  private String formatParamName(String name) {
    return name.replaceAll("\\.", "");
  }


}
