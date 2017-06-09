package by.tc.like_it.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class ServiceUtil {
    public static String EncryptPassword(String password) {
        String EncryptedPassword = DigestUtils.md5Hex(password);

        return EncryptedPassword;
    }
}
