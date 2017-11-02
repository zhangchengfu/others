package com.avc.anything;

import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ2ÈÕ
 */
public class RevNum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		System.out.println(Rev(Rev(x)+Rev(y)));
	}
	
	public static int Rev(int n) {
		StringBuilder sb = new StringBuilder();
		sb.append(n);
//		System.err.println(sb.toString());
		return Integer.parseInt(sb.reverse().toString());
	}
}
