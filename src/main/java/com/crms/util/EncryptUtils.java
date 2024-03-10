package com.crms.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author joe
 */
public class EncryptUtils {

    public static String hMacSHA256(String secretKey, String message) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] macData = sha256_HMAC.doFinal(message.getBytes());
        String hash = "";
        for (final byte element: macData) {
            hash += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
        }
        return hash;
    }

    /**
     * Map should be implemented by LinkedHashMap to keep the order key that is inputed
     * @param secretKey
     * @param data
     * @return
     * @throws Exception
     */
    public static String hMacSHA256(String secretKey, Map<String, String> data) throws Exception {
        String message = data.keySet().stream()
                .map(key -> key + "=" + data.get(key))
                .collect(Collectors.joining("&"));
        return hMacSHA256(secretKey, message);
    }
}
