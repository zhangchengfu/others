package com.avc.anything;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class MaxNum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long number = sc.nextLong();
		int cnt = sc.nextInt();
		sc.close();
		String strNum = number+"";
		char[] chs = strNum.toCharArray();
		int[] arr = new int[chs.length];
		for(int i=0; i<chs.length; i++) {
			arr[i]=(Integer.parseInt(chs[i]+""));
		}
		Arrays.sort(arr);
		int[] temp = new int[cnt];
		for(int i=0; i<cnt; i++) {
			temp[i] = arr[i];
		}
		
		for(int j=0; j<temp.length; j++) {
			for(int i=j; i<arr.length; i++) {
				if(arr[i]==temp[j]) {
					arr[i]=-1;
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int x : arr) {
			if(x != -1) {
				sb.append(x);
			}
		}
		
		System.out.println(sb.toString());
	}
}
