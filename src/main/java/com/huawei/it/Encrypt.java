package com.huawei.it;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Encrypt {
    public static byte[] encryptWithJCE(byte[] key, byte[] data) throws Exception {
        if (null != data && data.length != 0) {
            int len = data.length / 16 * 16 + 16;
            byte[] bSrcData = new byte[len];
            System.arraycopy(data, 0, bSrcData, 0, data.length);

            for (int k = data.length; k < len; ++k) {
                bSrcData[k] = 0;
            }

            Key secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(1, secretKey);
            return cipher.doFinal(bSrcData);
        } else {
            return null;
        }
    }

}
