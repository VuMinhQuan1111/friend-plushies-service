package com.crms.repository.custom.impl;

import com.crms.repository.AccessTokenRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author chautn on 9/22/2017
 */
@Repository
@Transactional
public class AccessTokenRepositoryImpl implements AccessTokenRepository {

  public final Logger logger = LoggerFactory.getLogger(AccessTokenRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void removeAccessToken(List<String> username) {
    Query query = entityManager.createNativeQuery("DELETE FROM oauth_access_token WHERE user_name IN :username");
    query.setParameter("username", username);
    try {
      query.executeUpdate();
    } catch (Exception e) {
      logger.warn("Remove token error", e);
    }
  }
}
