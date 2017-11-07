package com.avc.anything;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class ThirdWord {
	public static void main(String[] args) {
		//System.out.println(">".toLowerCase().equals(">"));
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		str = str.replaceAll(" +", "");
		//System.out.println(str);
		char[] chs = str.toCharArray();
		HashMap<String,Integer> map = new HashMap<>();
		for(char c :chs) {
			if(map.get((c+"").toLowerCase())==null) {
				map.put((c+"").toLowerCase(), 1);
			}else {
				map.put((c+"").toLowerCase(), map.get((c+"").toLowerCase())+1);
			}
			
			if(map.get((c+"").toLowerCase()) == 3) {
				System.out.println(c);
				break;
			}
		}
	}
	
}

