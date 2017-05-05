package com.huawei.it;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Decrypt {
    public static String decryptWithJCE(byte[] key, String data) throws Exception {
        if (null != data && !"".equals(data)) {
            Key secretKey = new SecretKeySpec(key, "AES");

            byte[] content = BASE64Encription.decode(data);
            if (content.length % 16 != 0) {
                return null;
            } else {
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(2, secretKey);
                byte[] bSrcDecode = cipher.doFinal(content);

                int i;
                for (i = bSrcDecode.length - 1; i >= 0 && bSrcDecode[i] == 0; --i) {
                    ;
                }

                byte[] bDecode = new byte[i + 1];
                System.arraycopy(bSrcDecode, 0, bDecode, 0, i + 1);
                return new String(bDecode, StandardCharsets.UTF_8);
            }
        } else {
            return null;
        }
    }

}
