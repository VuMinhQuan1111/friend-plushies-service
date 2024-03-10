package com.crms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerUtils {

    public static final Logger logger = LoggerFactory.getLogger(CustomerUtils.class);
    public static String verifyPhone(String phone) {
        String refPhone = "";
        String mobilePhone = "";
        String zero = "0";
        String result = phone;
        if (phone.trim().matches("^[0-9]*$")) {
            if (phone.trim().length() == 10) {
                if (phone.charAt(1) == '2') {
                    refPhone = phone.substring(2);
                    result = refPhone;
                    return result;
                }
                if (phone.charAt(1) == '4') {
                    result = mobilePhone;
                    return result;
                }
            } else if (phone.trim().length() == 9) {
                mobilePhone = zero.concat(phone);
                result = mobilePhone;
                return result;
            } else if (phone.trim().length() == 8) {
                result = refPhone;
                return result;
            }
        } else {
            logger.warn("Does not type of phone number " + phone);
        }
        return result;
    }
}
