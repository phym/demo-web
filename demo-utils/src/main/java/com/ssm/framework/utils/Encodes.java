package com.ssm.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encodes {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(Encodes.class);
    
    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final char[] BASE16_UPPER = "0123456789ABCDEF".toCharArray();

    private static final char[] BASE16_LOWER = "0123456789abcdef".toCharArray();

    private static final byte[] BASE16_DECODE = new byte[128];

    static {
        for (int i = 0; i < 10; i++) {
            BASE16_DECODE['0' + i] = (byte) i;
            BASE16_DECODE['A' + i] = (byte) (10 + i);
            BASE16_DECODE['a' + i] = (byte) (10 + i);
        }
    }

    /**
     * Encode bytes to base16 chars.
     *
     * @param src Bytes to encode.
     * @param upper Use upper or lowercase chars.
     *
     * @return Encoded chars.
     */
    public static char[] encodeBase16(byte[] src, boolean upper) {
        char[] table = upper ? Encodes.BASE16_UPPER : Encodes.BASE16_LOWER;
        char[] dst = new char[src.length * 2];

        for (int si = 0, di = 0; si < src.length; si++) {
            byte b = src[si];
            dst[di++] = table[(b & 0xf0) >>> 4];
            dst[di++] = table[(b & 0x0f)];
        }

        return dst;
    }

    /**
     * Decode base16 chars to bytes.
     *
     * @param src Chars to decode.
     *
     * @return Decoded bytes.
     */
    public static byte[] decodeBase16(char[] src) {
        byte[] dst = new byte[src.length / 2];

        for (int si = 0, di = 0; di < dst.length; di++) {
            byte high = BASE16_DECODE[src[si++] & 0x7f];
            byte low = BASE16_DECODE[src[si++] & 0x7f];
            dst[di] = (byte) ((high << 4) + low);
        }

        return dst;
    }

    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     * @throws DecoderException 
     */
    public static byte[] decodeHex(String input) throws DecoderException {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * Base62编码。
     */
    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[(input[i] & 0xFF) % BASE62.length];
        }
        return new String(chars);
    }

    /**
     * Html 转码.
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * Xml 解码.
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     * @throws UnsupportedEncodingException 
     */
    public static String urlEncode(String part) throws UnsupportedEncodingException {
        try {
            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     * @throws UnsupportedEncodingException 
     */
    public static String urlDecode(String part) throws UnsupportedEncodingException
    {
        
        try
        {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
