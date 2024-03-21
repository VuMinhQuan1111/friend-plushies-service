package com.friendsplushies.model.entity.listener;

import com.friendsplushies.config.oauth2.SUserDetails;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.PrePersist;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author vuld
 * @date 19/08/2020
 */
public class FEntityListener {

  @PrePersist
  public void onCreate(FEntity entity) {
    if (StringUtils.isEmpty(entity.getCreatedBy())) {
      entity.setCreatedBy(getActorId());
    }

    if (entity.getCreatedDate() == null) {
      entity.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    }

  }

  private String getActorId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof SUserDetails) {
      return ((SUserDetails) auth.getPrincipal()).getName();
    } else {
      return "ANONYMOUS";
    }
  }
}
