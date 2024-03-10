package com.crms.controller;

import com.crms.common.BadRequestException;
import com.crms.util.RestResult;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author vuld
 */
public class BaseController implements ErrorController {

  public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @ExceptionHandler({BadRequestException.class})
  @ResponseBody
  public ResponseEntity handleBadRequestException(BadRequestException exception) {
    logger.error(exception.getMessage(), exception);
    RestResult<String> result = new RestResult<>();
    result.setStatus(RestResult.STATUS_ERROR);
    result.addMessage(exception.getMessage());
    result.addMessages(exception.getMessages());
    result.setData(exception.getCode());
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({OAuth2Exception.class})
  @ResponseBody
  public ResponseEntity handleOAuth2Exception(OAuth2Exception exception) {
    logger.error(exception.getMessage(), exception);
    RestResult<String> result = new RestResult<>();
    result.setStatus(RestResult.STATUS_ERROR);
    result.addMessage(exception.getMessage());
    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Wrong INPUT data : missing required data, bad format data (rest controller level)
   * Return http status 400 - BAD REQUEST
   *
   * @author vu@investidea.tech
   */
  @ExceptionHandler({ServletException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
  @ResponseBody
  public ResponseEntity<RestResult<Void>> handleServletException(Exception exception, HttpServletRequest request) {
    logger.error(exception.getMessage(), exception);
    return new ResponseEntity<>(RestResult.fail(Void.class).addError(exception.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Wrong INPUT data : missing required data, bad format data (service level)
   * Return http status 400 - BAD REQUEST
   *
   * @author vu@investidea.tech
   */
  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseBody
  public ResponseEntity<RestResult<Void>> handleIllegalException(IllegalArgumentException exception, HttpServletRequest request) {
    logger.error(exception.getMessage(), exception);
    return new ResponseEntity<>(RestResult.fail(Void.class).addError(exception.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Unexpected runtime Exception
   * Return http status 500 - INTERNAL_SERVER_ERROR
   *
   * @author vu@investidea.tech
   */
  @ExceptionHandler({RuntimeException.class})
  @ResponseBody
  public ResponseEntity<RestResult<Void>> handleRuntimeException(RuntimeException exception, HttpServletRequest request) {
    return handleException(exception, request);
  }

  /**
   * Final exception handler
   * Return http status 500 - INTERNAL_SERVER_ERROR
   *
   * @author vu@investidea.tech
   */
  @ExceptionHandler({Exception.class})
  @ResponseBody
  public ResponseEntity<RestResult<Void>> handleException(Exception exception, HttpServletRequest request) {
    logger.info(exception.getMessage(), exception);
    List<String> errors = new ArrayList<>();
    errors.add("Unexpected errors occured");
    RestResult<Void> errorResponse = RestResult.create(Void.class)
        .setStatus(RestResult.STATUS_ERROR)
        .setMessages(getRecMsgErrors(exception, errors));
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private List<String> getRecMsgErrors(Throwable e, List<String> errors) {
    if (errors == null) {
      errors = new LinkedList<String>();
    }
    errors.add(e.getMessage());
    if (e.getCause() != null) {
      getRecMsgErrors(e.getCause(), errors);
    }
    return errors;
  }
}
