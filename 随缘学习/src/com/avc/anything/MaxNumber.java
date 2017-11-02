package com.avc.anything;

import java.util.*;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class MaxNumber{
	
    public static void main(String[] args){
        
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	List<Integer> list = new ArrayList<>();
    	for(int i=0; i<n; i++) {
    		list.add(sc.nextInt());
    	}
    	
    	list.sort( new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = String.valueOf(o1);
				String s2 = String.valueOf(o2);
//				return (s2+s1).compareTo(s1+s2);
				return (o2+""+o1).compareTo(o1+""+o2);
			}
    	});
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<list.size(); i++) {
    		sb.append(list.get(i));
    	}
//    	System.out.println("--------------");
//    	for (Integer i : list) {
//    		System.out.println(i);
//    	}
    	
    	System.out.println(sb.toString());
    }

}