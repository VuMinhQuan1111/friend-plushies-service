package com.friendsplushies.model.request;

import com.friendsplushies.model.entity.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TuAD ON 3/21/2024
 */

@Getter
@Setter
public class OrderRequest {
  private Long id;
  private String userAddress;
  private String userName;
  private String userPhone;
  private String status;
  private Timestamp createdDate;
  private String createdBy;
  private List<Long> cartIds;
}
