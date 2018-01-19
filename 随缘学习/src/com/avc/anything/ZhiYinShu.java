package com.avc.anything;

import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ9ÈÕ
 */
public class ZhiYinShu {
	public static void main(String[] args) {
		new ZhiYinShu();
	}
	public ZhiYinShu() {
		Scanner sc = new Scanner(System.in);
		long number = sc.nextLong();
		String result = getResult(number);
		StringBuilder sb = new StringBuilder();
		String s = sb.append(result).reverse().toString().trim();
		System.out.println(s+" ");
	}
	public String getResult(long ulDataInput) {
		String res = "";
		if(ulDataInput%2 ==0)
			res+=2+" ";
		for(int index=2; index <= ulDataInput/2; index++) {
			if(ulDataInput%index ==0 && isPrim(index)) {
//				getResult(ulDataInput/index);
				res=index+" ";
				res+=getResult(ulDataInput/index);
			}			
		}
		
		return res;
	}
	
	public boolean isPrim(int n) {
		for(int i=2; i<n; i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
}
