package io.agora.chatdemo.general.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    /**
     * MD5 encryption
     * @param str
     * @return
     */
    public static String encrypt2MD5(String str) {
        if(TextUtils.isEmpty(str)) {
            return "";
        }
        String hexStr = "";
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
            //use Zero-pad the resulting 16-byte array
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10){
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            hexStr = hex.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hexStr;
    }
}
