package com.avc.anything;

import java.util.*;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class CountOfZhiShu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int cnt=0;
    	for(int i =2; i<=n/2; i++) {
    		if(isPrime(i) && isPrime(n-i))
    			cnt++;
    	}
    	System.out.println(cnt);
	}
	
	public static boolean isPrime(int n) {
		for(int i=2; i<n; i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}

}
