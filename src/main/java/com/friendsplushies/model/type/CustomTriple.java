package com.friendsplushies.model.type;

import com.friendsplushies.model.enumeration.DataType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.MutableTriple;

@Getter
@Setter
public class CustomTriple<L, M, R> extends MutableTriple<L, M, R> {
  private DataType dataType;
  private String expression;

  public static <L, M, R> CustomTriple<L, M, R> of(L left, M middle, R right) {
    return new CustomTriple(left, middle, right);
  }

  public CustomTriple() {
    super();
  }

  public CustomTriple(L left, M middle, R right) {
    super(left, middle, right);
  }
}
