package com.avc.anything;

import java.util.*;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ10ÈÕ
 */
public class ContainsAllChar {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String shortStr = sc.next();
		String longStr = sc.next();
		boolean bC = new ContainsAllChar().boolIsAllCharExist(shortStr, longStr);
		System.out.println(bC);
	}
	
	public boolean boolIsAllCharExist(String pShortString,String pLongString){
		boolean isContainsAll = true;
		 if(pShortString.length() > pLongString.length())
			 return false;
		 
		 char[] chs = pShortString.toCharArray();
		 for (char c : chs) {
			if(pLongString.contains(c+""))
				continue;
			isContainsAll =  false;
		}
		
		return isContainsAll;
	}
}
