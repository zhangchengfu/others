package com.avc.anything;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class M_sum {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		double sum=0;
		double num = n;
		for(int i=0; i<m-1; i++) {
			sum+=Math.sqrt(num);
			num = Math.sqrt(num);
		}
		sc.close();
		System.out.printf("%.2f",sum+n);
	}
	
}
