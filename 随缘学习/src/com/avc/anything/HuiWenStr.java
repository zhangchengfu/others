package com.avc.anything;

import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ2ÈÕ
 */
public class HuiWenStr {

	public static void main(String[] args) {
		//System.out.println("skj".substring( 2));
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		int count=0;
		for(int i=0; i<str1.length(); i++) {
			if(isHuiWen(insertStr(str1, str2, i))) {
				System.err.println(insertStr(str1, str2, i));
				count++;
			}
		}
		if(isHuiWen(str1.concat(str2)))
			count++;
		
		System.out.println(count);
	}

	public static boolean isHuiWen(String str) {
		int len = str.length();
		String s1 = str.substring(0,len/2);
		String s2 = null;
		if(len%2==0)
			s2 = str.substring(len/2);
		else
			s2 = str.substring(len/2+1);
		
		StringBuilder sb = new StringBuilder();
		return sb.append(s1).reverse().toString().equals(s2);
	}
	
	public static String insertStr(String s1,String s2, int index) {
		return s1.substring(0,index).concat(s2).concat(s1.substring(index));
		//return "";
	}
}
