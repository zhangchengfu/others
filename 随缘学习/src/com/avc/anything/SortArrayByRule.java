package com.avc.anything;
import java.util.*;
/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ10ÈÕ
 */
public class SortArrayByRule {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		Integer[] arr = new Integer[len];
		while((len-1)>=0) {
			arr[len-1] = sc.nextInt();
			len--;
		}
		int sortFlag = sc.nextInt();
		
		sortIntegerArray(arr, sortFlag);
		
		for (int i = 0; i < arr.length; i++) {
			if(i==arr.length-1)
				System.out.println(arr[i]);
			else
				System.out.print(arr[i]+" ");
		}
	}
	
	public static void sortIntegerArray(Integer[] pIntegerArray, int iSortFlag) {
		Arrays.sort(pIntegerArray);
		Arrays.sort(pIntegerArray,new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return iSortFlag==1? -1:1;
			}
		});
		
	}
}
