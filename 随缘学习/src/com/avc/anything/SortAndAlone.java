package com.avc.anything;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
/**
 * @author AmVilCres
 * @desc 
 * @date 2017年11月9日
 */
/*
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），
 * 对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，
 * 按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作。
 
	 
	Input Param 
	     n               输入随机数的个数     
	 inputArray      n个随机整数组成的数组 
	     
	Return Value
	     OutputArray    输出处理后的随机整数
 * */
import java.util.Scanner;
public class SortAndAlone {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] inputArray = new int[n];
		ArrayList<Integer> list = new  ArrayList<>();
		for (int i = 0; i < inputArray.length; i++) {
			inputArray[i] =sc.nextInt();
			if(!list.contains(inputArray[i]))
				list.add(inputArray[i]);
		}
		list.sort(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer integer = iterator.next();
			System.out.println(integer);
		}
	}
}
