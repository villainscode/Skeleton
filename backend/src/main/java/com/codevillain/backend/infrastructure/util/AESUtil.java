package com.codevillain.backend.infrastructure.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {


    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private static SecretKeySpec createKey(String key) {
        byte[] keyBytes = new byte[16];
        byte[] parameterKeyBytes = key.getBytes();
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKeySpec = createKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encrypted, String key) throws Exception {
        SecretKeySpec secretKeySpec = createKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}

