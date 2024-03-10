package com.crms.model.request;

import com.crms.model.enumeration.ConditionType;
import com.crms.model.type.CustomTriple;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.MutablePair;

/**
 * Author: chautn on 4/9/2019 3:17 PM
 */
@Getter
@Setter
public class SearchRequest {

  private Integer limit;
  private Integer offset;
  private List<MutablePair<String, String>> orders;
  private List<CustomTriple<String, String, Object>> conditions;
  private List<MutablePair<String, String>> joins;
  private ConditionType conditionType;
  private List<SearchRequest> subConditions;
}
