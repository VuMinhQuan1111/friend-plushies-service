package com.friendsplushies.model.request;

import com.friendsplushies.model.entity.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

/**
 * @author TuAD ON 3/21/2024
 */

@Data
public class OrderRequest {
  private Long id;
  private Long userId;
  private String name;
  private BigDecimal price;
  private BigDecimal discount;
  private String userAddress;
  private String userName;
  private String userPhone;
  private BigDecimal shipFee;
  private Timestamp createdDate;
  private String createdBy;
  private List<Long> productIds;
}
