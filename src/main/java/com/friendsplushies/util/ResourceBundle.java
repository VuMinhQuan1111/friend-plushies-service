// package com.friendsplushies.util;
// import java.util.Locale;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.context.NoSuchMessageException;
// import org.springframework.context.i18n.LocaleContextHolder;
// import org.springframework.context.support.ResourceBundleMessageSource;

// public class ResourceBundle {
//     private static final Logger LOG = LoggerFactory.getLogger(ResourceBundle.class);

//     private ResourceBundleMessageSource messageSource;

//     public ResourceBundle(ResourceBundleMessageSource messageSource) {
//         this.messageSource = messageSource;
//     }

//     public String getMessage(String messageKey, Locale locale, Object... parameters) {
//         String msg = null;
//         try {
//             msg = messageSource.getMessage(messageKey, parameters, messageKey, locale);
//         } catch (NoSuchMessageException e) {
//             LOG.error("no message found", e);
//             msg = messageKey;
//         }

//         return msg;
//     }

//     public String getMessage(String message, Object... parameters) {
//         Locale locale = LocaleContextHolder.getLocale();
//         return getMessage(message, locale, parameters);
//     }
// }
