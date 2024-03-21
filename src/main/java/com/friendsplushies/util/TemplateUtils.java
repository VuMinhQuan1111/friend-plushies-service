package com.friendsplushies.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Author: chautn on 4/26/2019 10:42 AM
 */
public class TemplateUtils {

  public static String replaceParams(String text, Map<String, String> params) {
    String result = text;
    if (text != null && text.length() > 0 && params != null && params.size() > 0) {
      for (Entry<String, String> kv : params.entrySet()) {
        result = result.replaceAll("\\$\\{" + kv.getKey() + "}", kv.getValue());
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    Email from = new Email("test@example.com");
    String subject = "Sending with SendGrid is Fun";
    Email to = new Email("test@example.com");
    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("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");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println("----" + response.getStatusCode());
      System.out.println(">>>>" + response.getBody());
      System.out.println("<<<<" + response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
  }
}
