package com.avc.anything;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ7ÈÕ
 */
import java.util.*;

public class FirstWeizhi {
	public static void main(String[] args) {
		int vnt = FirstNotRepeatingChar("google");
		System.out.println(vnt);
	}
    public static int FirstNotRepeatingChar(String str) {
        char[] chs = str.toCharArray();
        if(chs.length==0)
        	return -1;
		HashMap<Character,Integer> map = new HashMap<>();
		for(char c :chs) {
			if(map.get(c)==null) {
				map.put(c, 1);
			}else {
				map.put(c, map.get(c)+1);
			}
		}
        
        int pos = 0;
        for(char c : chs) {
        	Integer i = map.get(c);
        	if(i==1)
        		break;
        	else
        		pos++;
        }
        return pos;
    }
}
