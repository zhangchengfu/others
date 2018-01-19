package com.avc.anything;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ9ÈÕ
 */
public class CharNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String s = sc.next();
		char[] chs = str.toCharArray();
		HashMap<String,Integer> map = new HashMap<>();
		for(char c :chs) {
			if(map.get((c+"").toLowerCase())==null) {
				map.put((c+"").toLowerCase(), 1);
			}else {
				map.put((c+"").toLowerCase(), map.get((c+"").toLowerCase())+1);
			}
		}
		
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		int count=0;
		while(it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			String key = entry.getKey();
			if(key.equalsIgnoreCase(s)) {
				count=entry.getValue();
				break;
			}
		}
		System.out.println(count);
		
	}

}
