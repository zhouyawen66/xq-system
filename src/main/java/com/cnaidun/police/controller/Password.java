package com.cnaidun.police.controller;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.mysql.jdbc.StringUtils.getBytes;

/**
 * @ClassName Password
 * @Description TODO
 * @Author Administrator
 * @Date 2020/1/8 11:03
 * @Version 1.0
 */
public class Password {

    private static final String password = "33";


    public static void test() {
        long startTime = System.currentTimeMillis();
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf2.format(date);
        System.out.println("format:"+format);
        String password = "123456";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 10;  // 加密次数：2
        String alogrithmName = "md5";   // 加密算法
        String encodePassword = new SimpleHash(alogrithmName, password, salt, times).toString();
        long endTime = System.currentTimeMillis();
        String time = (endTime - startTime)+"ms";
        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s, 运算时间是ms：%s ", password, salt, times, encodePassword,time);
    }
    //20cb6c8abc063c085e8a6cb34dbaf139

    public static void relieve() {
        String salt="9QkpLC02zvkT1tc4LGamNQ==";
        String password = "123456";
        int times = 10;  // 加密次数：2
        String alogrithmName = "md5";   // 加密算法
        String relievePassword = new SimpleHash(alogrithmName, password, salt, times).toString();
        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s", password, salt, times, relievePassword);
    }

    public static void jdkThreeDES() {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESEde");
//			keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.转换KEY
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESEde");
            Key convertKey = factory.generateSecret(deSedeKeySpec);

            //3.加密
            Cipher cipher = Cipher.getInstance("DESEde/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertKey);
            byte[] result = cipher.doFinal(password.getBytes());
            System.out.println("加密后：" + Hex.encodeHexString(result));

            //4.解密
            cipher.init(Cipher.DECRYPT_MODE, convertKey);
            result = cipher.doFinal(result);
            System.out.println("解密后：" + new String(result));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        test();
//        relieve();
//        jdkThreeDES();
    }



}
