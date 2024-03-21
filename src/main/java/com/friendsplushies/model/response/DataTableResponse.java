package com.friendsplushies.model.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chautn on 9/14/2017
 */
@Getter
@Setter
public class DataTableResponse<T> {

  private Long recordsTotal;

  private Long recordsFiltered;

  private Integer draw;

  private List<T> data;

  public DataTableResponse(Long recordsTotal, Long recordsFiltered, Integer draw, List<T> data) {
    this.recordsTotal = recordsTotal;
    this.recordsFiltered = recordsFiltered;
    this.draw = draw;
    this.data = data;
  }

  public DataTableResponse() {}
}
