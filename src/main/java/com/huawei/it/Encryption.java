package com.huawei.it;

import java.io.Console;

public class Encryption {
    //This is the key to encrypt or decrypt,
    //You should change it.
    //The length of key should be 16.
    private static String key="keyShould16Chars";
    
    public static void main(String[] args) throws Exception {
        System.out.println("------等待输入,输入密码后按回车:-------");
        Console console = System.console();
        char[] password = console.readPassword();
        String pwd = String.valueOf(password);
        String encrypt = encrypt(new String[]{pwd});

        System.out.println("-------成功-------");
        System.out.print("输入:");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < password.length; i++) {
            builder.append("*");
        }
        System.out.println(builder.toString());
        System.out.println("加密密码:" + encrypt);
        System.out.println("--------------------------");
    }

    public static String encrypt(String[] args) throws Exception {
        String content = "";

        switch (args.length) {
            case 2:
                key = args[0];
                content = args[1];
                break;
            case 1:
                content = args[0];
                break;
            default:
                break;
        }

        byte[] encryptedContent = Encrypt.encryptWithJCE(key.getBytes(), content.getBytes());
        return BASE64Encription.encode(encryptedContent, encryptedContent.length);
    }
}

