package com.avc.anything;

import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class LuckNumber {
	
	public static void main(String[] args) {
		//System.out.println(f(0));
		Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int count = 0;
    	
    	for(int i=1; i<=n; i++) {
    		if(f(i) == g(i))
    			count++;
    	}
    	
    	System.out.println(count);
	}
	
	public static int f(int x) {
		String xStr = x+"";
		char[] chs = xStr.toCharArray();
		int fSum = 0;
		for(char c :chs) {
			fSum += Integer.parseInt((c+""));
		}
		return fSum;
	}
	
	public static int g(int x) {
		int cnt=0;
		while(x!=0) {
			cnt++;
			x = x&(x-1);
		}
		
		return cnt;
	}
	
}
