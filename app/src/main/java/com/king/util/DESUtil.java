package com.king.util;

/**
 * Created by 57677 on 2018/11/4.
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DESUtil {
    //加密数据入口

    public static String key = "44658735";

    public static String encry(String message)
            throws Exception {
        byte[] bytes = encrypt(message, DESUtil.key);
        return bytesToHexString(bytes).toUpperCase();
    }

    public static String encryptString(String message, String key)
            throws Exception {
        byte[] bytes = encrypt(message, key);
        return bytesToHexString(bytes).toUpperCase();
    }

    private static byte[] encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

//    public static void main(String[] args){
//        try {
//            System.out.println(encryptString("123456+-*/","44658735"));
//            System.out.print(encry("123456+-*/"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
