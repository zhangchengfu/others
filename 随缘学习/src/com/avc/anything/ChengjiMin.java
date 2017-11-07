package com.avc.anything;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ7ÈÕ
 */
import java.util.ArrayList;
public class ChengjiMin {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
    	ArrayList list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int tmp = 0;
        for(int i : array) {
        	if(isContain(array, sum-i)) {
        		tmp = i*(sum-i);
        		if(min>tmp) {
        			list.clear();
        			list.add(Math.min(i, sum-i));
        			list.add(Math.max(i, sum-i));
        			min = tmp;
        		}
        	}
        }
    	return list;
    }
    
    public boolean isContain(int[] arr,int n) {
    	boolean b = false;
    	for(int num : arr) {
    		if(num==n) {
    			b = true;
    			break;
    		}
    	}
    	return b;
    }
}