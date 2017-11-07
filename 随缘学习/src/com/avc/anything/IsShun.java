package com.avc.anything;

import java.util.Arrays;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ7ÈÕ
 */
public class IsShun {
	public static void main(String[] args) {
		int[] arr = {1,1,0,0,0};
		System.out.println(isContinuous(arr));
	}
	 public static boolean isContinuous(int [] numbers) {
		 boolean isContains = false;
		 Arrays.sort(numbers);
		 int index = 0;
		 int zero_count = 0;
		 if(numbers[0]==0) {
			 zero_count++;
			 int tmp=0;
			 for(tmp = index+1;tmp<numbers.length; tmp++) {
				 if(numbers[tmp]==0)
					 zero_count++;
				 else {
					 index = tmp;
					 break;
				 }
			 }
		 }
		 System.out.println(zero_count+"   "+index);
		 if(zero_count == numbers.length)
			 isContains=true;
		 else {
			 int k=-1;
			 for(k = index; k<numbers.length-1; k++) {
				 if(numbers[k]==numbers[k+1])
					 break;
				 zero_count = zero_count-(numbers[k+1]-numbers[k]-1);
			 }
			 System.out.println(zero_count);
			 if(zero_count>=0 && k==numbers.length-1)
				 isContains=true;
		 }
		 return isContains;
	 }
	 
	 public int StrToInt(String str) {
		 try {
			int i = Integer.parseInt(str);
			return i;
		} catch (Exception e) {
			return 0;
		}
	 }
}
