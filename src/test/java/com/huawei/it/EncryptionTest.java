package com.huawei.it;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class EncryptionTest {
    @Test
    public void should_encrypt_data_with_default_key() throws Exception {
        String encryptData = Encryption.encrypt(new String[]{"test123456"});

        assertNotNull(encryptData);
        System.out.println(encryptData);

    }

    @Test
    public void should_encrypt_data_with_given_key() throws Exception {
        String key = "keyShould16Chars";
        String content = "root";
        String encryptData = Encryption.encrypt(new String[]{key,content});

        assertNotNull(encryptData);
        System.out.println(encryptData);
    }

    @Test
    public void should_decrypt_data() throws Exception {

        String key = "keyShould16Chars";
        String content = "test123456";
        String encryptData = Encryption.encrypt(new String[]{content});
        String decryptWithJCE = Decrypt.decryptWithJCE(key.getBytes(), encryptData);

        assertThat(content, is(decryptWithJCE));

    }
}