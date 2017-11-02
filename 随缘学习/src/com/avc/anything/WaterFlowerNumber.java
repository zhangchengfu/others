package com.avc.anything;

import java.util.Scanner;

/**
 * @author AmVilCres
 */
public class WaterFlowerNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = sc.nextInt();
		int end = sc.nextInt();
		int count = 0;
		String sum="";
		if(start > end)
			System.out.println("no");
		for(int x=start; x<=end; x++) {
			if(isWaterFlowerNumber(x)) {
				sum+=(x+" ");
				count++;
			}
		}
		if(count==0)
			System.out.println("no");
		else
			System.out.println(sum.trim());
	}
	
	public static boolean isWaterFlowerNumber(int num) {
		if(num < 100)
			return false;
		else {
			int heigth = Integer.parseInt((num+"").substring(0, 1));
			int middle = Integer.parseInt((num+"").substring(1, 2));
			int low = Integer.parseInt((num+"").substring(2));
			int sum = heigth*heigth*heigth + middle*middle*middle+low*low*low;
			if(sum == num)
				return true;
			return false;
		}
	}
}
