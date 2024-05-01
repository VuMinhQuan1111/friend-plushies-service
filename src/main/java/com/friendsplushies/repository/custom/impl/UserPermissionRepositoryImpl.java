package com.friendsplushies.repository.custom.impl;

import com.friendsplushies.model.entity.User;
import com.friendsplushies.model.entity.UserPermission;
import com.friendsplushies.model.request.UserPermissionSearchRequest;
import com.friendsplushies.repository.custom.UserPermissionRepositoryCustom;
import com.friendsplushies.util.cruds.repository.impl.AbstractRepositoryImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * @author vuld
 */
public class UserPermissionRepositoryImpl extends AbstractRepositoryImpl<UserPermission> implements UserPermissionRepositoryCustom {

  public UserPermissionRepositoryImpl() {
    super(UserPermission.class);
  }

  @Override
  public List<User> searchUserPermissions(UserPermissionSearchRequest request) {
    try {
      return createQuery(request, false).getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public Long countUserPermissions(UserPermissionSearchRequest request) {
    try {
      return (Long) createQuery(request, true).getSingleResult();
    } catch (NoResultException e) {
      return 0L;
    }
  }

  private Query createQuery(UserPermissionSearchRequest request, Boolean isCounting) {
    StringBuilder sql = new StringBuilder();
    if (isCounting) {
      sql.append("SELECT count(distinct user.userId) FROM User user ");
    } else {
      sql.append("SELECT distinct(user) FROM User user ");
    }

    sql.append(" INNER JOIN user.userPermissions permission WHERE 1=1 ");

    if (request.getUserId() != null) {
      sql.append(" AND user.userId = :userId ");
    }
    if (StringUtils.isNotEmpty(request.getUsername())) {
      sql.append(" AND LOWER(user.username) LIKE  LOWER(CONCAT('%', CONCAT(:username, '%'))) ");
    }
    if (StringUtils.isNotEmpty(request.getPhone())) {
      sql.append(" AND LOWER(user.phone) LIKE LOWER(CONCAT('%', CONCAT(:phone, '%'))) ");
    }
    if (StringUtils.isNotEmpty(request.getEmail())) {
      sql.append(" AND LOWER(user.email) LIKE LOWER(CONCAT('%', CONCAT(:email, '%'))) ");
    }
    if (StringUtils.isNotEmpty(request.getName())) {
      sql.append(" AND LOWER(user.name) LIKE LOWER(CONCAT('%', CONCAT(:name, '%'))) ");
    }
    if (StringUtils.isNotEmpty(request.getType())) {
      sql.append(" AND user.type = :type ");
    }
    if (ArrayUtils.isNotEmpty(request.getTypes())) {
      sql.append(" AND user.type IN :types ");
    }
    if (StringUtils.isNotEmpty(request.getPermission())) {
      sql.append(" AND permission.permissionName = :permission ");
    }
    if (request.getFromCreatedDate() != null) {
      sql.append(" AND permission.createdDate >= :fromCreatedDate ");
    }

    if (request.getToCreatedDate() != null) {
      sql.append(" AND permission.createdDate <= :toCreatedDate ");
    }

    if (!isCounting) {
      sql.append(" ORDER BY user.userId asc ");
    }
    Query query = query(sql.toString());

    if (request.getUserId() != null) {
      query.setParameter("userId", request.getUserId());
    }
    if (StringUtils.isNotEmpty(request.getUsername())) {
      query.setParameter("username", request.getUsername());
    }
    if (StringUtils.isNotEmpty(request.getPhone())) {
      query.setParameter("phone", request.getPhone());
    }
    if (StringUtils.isNotEmpty(request.getEmail())) {
      query.setParameter("email", request.getEmail());
    }
    if (StringUtils.isNotEmpty(request.getName())) {
      query.setParameter("name", request.getName());
    }
    if (StringUtils.isNotEmpty(request.getType())) {
      query.setParameter("type", request.getType());
    }
    if (ArrayUtils.isNotEmpty(request.getTypes())) {
      query.setParameter("types", request.getTypes());
    }
    if (StringUtils.isNotEmpty(request.getPermission())) {
      query.setParameter("permission", request.getPermission());
    }
    if (request.getFromCreatedDate() != null) {
      query.setParameter("fromCreatedDate", request.getFromCreatedDate());
    }

    if (request.getToCreatedDate() != null) {
      query.setParameter("toCreatedDate", request.getToCreatedDate());
    }

    if (!isCounting) {
      query.setFirstResult(request.getOffset());
      query.setMaxResults(request.getLimit());
    }
    return query;
  }
}
