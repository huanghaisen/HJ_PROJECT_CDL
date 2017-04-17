/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.framework.security;

import com.framework.util.SystemUtil;
import org.apache.axis.encoding.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * BlowFish的加密和復元的样本
 */
public class BlowFish {
	
	
	public static final String KEY=SystemUtil.getPro("SYS.KEY");
	public static final String ENCODING = "GBK";

	// 32bit - 128bit (448bit)
	// public static final String TEST_KEY = "3a966cd0e52bd28dH";
	/**
	 * 把byte数组变换为16進数的字符串。
	 * 
	 * @param bytes
	 *            byte数组
	 * @return 16進数的字符串
	 */
	public static String byteToString(byte[] bytes) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {
			int d = bytes[i];
			if (d < 0) {
				d += 256;
			}
			if (d < 16) {
				buf.append("0");
			}
			buf.append(Integer.toString(d, 16));
		}
		return buf.toString();
	}

	/**
	 * 把16進数的字符串变换为byte数组。
	 * 
	 * @param string
	 *            16進数的字符串
	 * @return byte 数组
	 */
	public static byte[] stringToByte(String string) {

		byte[] bytes = new byte[string.length() / 2];
		String b;

		for (int i = 0; i < string.length() / 2; i++) {
			b = string.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(b, 16);
		}
		return bytes;
	}

	/**
     * 返回把字符串用暗号键加密的byte数组。
     *
     * @param key
     *            暗号键
     * @param text
     *            加密的字符串
     * @return 把字符串加密的byte数组
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
   public static byte[] encrypt(String key, byte[] text) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");
        // BlowFish的加密mode是ECB、Padding方式是PKCS5Padding。
       Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
       cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
       byte[] encrypted = cipher.doFinal(text);
       return encrypted;
    }
    /**
     * 返回加密的byte数组用暗号键復元的字符串。
     *
     * @param key
     *            暗号键
     * @param encrypted
     *            加密的byte数组
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
   public static byte[] decrypt(String key, byte[] encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
       SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(),"Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
       cipher.init(Cipher.DECRYPT_MODE, sksSpec);
       byte[] decrypted = cipher.doFinal(encrypted);
       return decrypted;
    }

	/**
	 * Base64字符解码
	 * 
	 * @param base64String
	 *            被解码字符
	 * @return 解码后字符
	 */
	public static String base64Decoder(String base64String) {
		String str = new String(Base64.encode(base64String.getBytes()));
		return str;

	}

	/**
	 * Base64字符编码
	 * 
	 * @param base64String
	 *            被解码字符
	 * @return 解码后字符
	 */
	public static String base64Eecoder(byte[] img) {
		String str = new String(Base64.encode(img));
		return str;

	}

	/**
	 * 16进制字符转字符串(把阴文转为明文方法)
	 * 
	 * @param str
	 * @return
	 */
	public static String hexToString(String str) {
		String value = "";
		if (str != null && !"".equals(str)) {
			value = new String(BlowFish.stringToByte(str));
		}
		return value;
	}

	/**
	 * 字符转16进制字符(明文转阴文)
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToHex(String str) {
		String value = "";
		if (str != null && !"".equals(str)) {
			value = BlowFish.byteToString(str.getBytes());
		}
		return value;
	}
	
	/**
	 * 加密包装
	 * @param text
	 * @return
	 */
	public static String pushEncode(String text){
		String textValue="";
		try {
			textValue=BlowFish.byteToString(encrypt(KEY, text.getBytes()));
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return textValue;
	}
	
	
	/**
	 * 解密包装
	 * @param text
	 * @return
	 */
	public static String pushDencode(String text){
		String textValue="";
		try {
			textValue=BlowFish.byteToString(BlowFish.decrypt(KEY, BlowFish.stringToByte(text)));
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return BlowFish.hexToString(textValue);
	}
	

}