package com.wds.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class CommonParameterUtil {
    public static HashMap<String, String> getCommonParameter() {
        try {
            //map集合存公共参数
            HashMap<String, String> paramemap = new HashMap<>();
            //系统当前的Unix时间戳
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            //随机数
            String nonce = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            //秘钥
            String key = "K;9)Bq|ScMF1h=Vp5uA-G87d(_fi[aP,.w^{vQ:W";
            //存入数组
            String[] arrayOfstring = new String[3];
            arrayOfstring[0] = key;
            arrayOfstring[1] = timestamp;
            arrayOfstring[2] = nonce;
            //排序
            Arrays.sort(arrayOfstring);
            //拼接
            StringBuffer sb = new StringBuffer();
            for (String s : arrayOfstring) {
                sb.append(s);
            }
            //转回字符串
            String keystr = sb.toString();
            //计算字符串的sha1散列值
            MessageDigest digest = null;
            digest = MessageDigest.getInstance("SHA-1");
            digest.update(keystr.getBytes());
            byte[] bytes = digest.digest();
            StringBuffer sf = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String s = Integer.toHexString(bytes[i] & 0xff);
                if (s.length() < 2) {
                    sf.append(0);
                }
                sf.append(s);
            }
            String singnature = sf.toString();
            //存入集合
            paramemap.put("signature", singnature);
            paramemap.put("timestamp", timestamp);
            paramemap.put("nonce", nonce);
            paramemap.put("from", "android");
            paramemap.put("lang", "zh");
            return paramemap;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
