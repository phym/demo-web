package com.ssm.framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 密码加密解密工具类,提供MD5/SHA/DES加密方式.
 * 
 */
public final class CryptUtils {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(CryptUtils.class);

    public static String MD5 = "MD5";
    public static String SHA = "SHA-1";
    public static String DES = "DES";
    public static String DESEDE = "DESede";
    public static String BLOWFISH = "Blowfish";
    public static String AES = "AES";
    public static String BASE64 = "BASE64";
    /** 加密类型 **/
    private String cryptType = null;
    private static CryptUtils cryptUtils;

    /**
     * 构造函数
     */
    private CryptUtils() {

    }

    /**
     * CryptUtils对象的创建
     * 
     * @param cryptType 加密类型,可以参考CryptUtils定义的常量
     * @return CryptUtils
     */
    public static CryptUtils getInstance(String cryptType) {
        if (cryptUtils == null) {
            cryptUtils = new CryptUtils();
        }
        cryptUtils.cryptType = cryptType;
        return cryptUtils;
    }

    private byte[] encrypt(byte[] bytes, byte[] salt, int hashIterations) {
        int hashI = Math.max(1, hashIterations);
        MessageDigest digest = getDigest(this.cryptType);
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }
        byte[] hashed = digest.digest(bytes);
        int iterations = hashI - 1;
        for (int i = 0; i < iterations; i++) {
            digest.reset();
            hashed = digest.digest(hashed);
        }
        return hashed;
    }

    private MessageDigest getDigest(String type) {
        try {
            return MessageDigest.getInstance(type);
        } catch (NoSuchAlgorithmException e) {
            String msg = "No native '" + type + "' MessageDigest instance available on the current JVM.";
            logger.error(msg);
        }
        return null;
    }

    /**
     * MD5加密
     * 
     * @param value 加密内容
     * @return 加密以后的字符串
     */
    public String encrypt(String value) {
        byte[] digesta = encrypt(value.getBytes(), null, 0);
        String rs = byte2hex(digesta);
        return rs;
    }

    /**
     * MD5加密
     * 
     * @param value 加密内容
     * @return 加密以后的字符串
     */
    public String encrypt(String value, String salt) {
        byte[] digesta = encrypt(value.getBytes(), salt.getBytes(), 0);
        String rs = byte2hex(digesta);
        return rs;
    }

    /**
     * MD5加密
     * 
     * @param value 加密内容
     * @return 加密以后的字符串
     */
    public String encrypt(String value, String salt, int hashIterations) {
        byte[] digesta = encrypt(value.getBytes(), salt.getBytes(), hashIterations);
        String rs = byte2hex(digesta);
        return rs;
    }

    /**
     * 将二进制转化为16进制字符串
     * 
     * @param b 二进制字节数组
     * @return 字符串
     */
    private String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
    
    public static void main(String[] args) {
        System.out.println(CryptUtils.getInstance(CryptUtils.MD5).encrypt("aaa"));
        System.out.println(CryptUtils.getInstance(CryptUtils.MD5).encrypt("aaa","abc"));
        System.out.println(CryptUtils.getInstance(CryptUtils.MD5).encrypt("aaa","abc",4));
    }
}
