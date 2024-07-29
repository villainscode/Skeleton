package com.codevillain.backend.test;
import com.codevillain.backend.infrastructure.util.AESUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtilTest {
    public static void main(String[] args) {
        try {
            String key = "9fdaas8392f4611e"; // 16바이트 키
            String data = "testAES"; // 예시 데이터

            // 암호화
            String encryptedData = AESUtil.encrypt(data, key);
            System.out.println("Encrypted Data: " + encryptedData);

            // 복호화
            String decryptedData = AESUtil.decrypt(encryptedData, key);
            System.out.println("Decrypted Data: " + decryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
