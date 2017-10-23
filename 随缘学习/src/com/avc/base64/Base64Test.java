package com.avc.base64;

import java.util.Base64;

public class Base64Test {

	public static void main(String[] args) {
		String encoder = Base64.getEncoder().encodeToString("text".getBytes());
		System.out.println(encoder); // dGV4dA==
		
		byte[] buf = Base64.getDecoder().decode("dGV4dA==");
		System.out.println(new String(buf)); //text
	}
}
