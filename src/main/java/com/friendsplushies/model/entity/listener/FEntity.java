package com.friendsplushies.model.entity.listener;

import java.sql.Timestamp;

/**
 * @author vuld
 * @date 19/08/2020
 */
public interface FEntity {

  void setCreatedBy(String createdBy);

  void setCreatedDate(Timestamp createdDate);

  String getCreatedBy();

  Timestamp getCreatedDate();
}
