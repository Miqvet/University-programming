package utilities;

import org.apache.commons.codec.digest.DigestUtils;

public class HashCreator {
    public static String hashingToSHA256(String code){
        String sha256 = DigestUtils.sha256Hex(code);
        return sha256;
    }
}
