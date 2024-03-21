package com.friendsplushies.model.request;

import com.friendsplushies.model.entity.Product;
import java.util.List;
import lombok.Data;

/**
 * @author TuAD ON 3/21/2024
 */

@Data
public class OrderRequest {
  private Long orderId;
  private List<Product> products;
}
