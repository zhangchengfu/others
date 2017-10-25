package com.avc.md5;

import java.security.MessageDigest;

public class MD5Test {
	
	public static final String MD5 = "MD5";
	
	//MD5
	public static String EncryptionStr(String str,String algorithm) {
		byte[] buf = null;
		String strRes = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(str.getBytes());
			buf = md.digest();
			StringBuffer strHex = new StringBuffer();
			for (int i = 0; i < buf.length; i++) {
				String hex = Integer.toHexString(0xff&buf[i]);
				if(hex.length()==1)
					strHex.append('0');
				strHex.append(hex);
			}
			strRes = strHex.toString();
		} catch (Exception e) {
			System.out.println("Bad Paramer");
		}
		
		return strRes;
	}
	
	//加密解密
	public static String convertMD5(String str) {
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			chs[i] = (char)(chs[i]^'J');
		}
		
		return new String(chs);
	}
	
	public static void main(String[] args) {
		String str = "text";
		System.out.println(EncryptionStr(str, MD5));//1cb251ec0d568de6a929b520c4aed8d1
		System.out.println(convertMD5("1cb251ec0d568de6a929b520c4aed8d1"));//加密
		System.out.println(convertMD5(convertMD5("1cb251ec0d568de6a929b520c4aed8d1"))); //解密
	}
}
