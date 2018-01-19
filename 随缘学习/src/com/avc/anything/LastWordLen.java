package com.avc.anything;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ9ÈÕ
 */
public class LastWordLen {
	public static void main(String[] args)throws Exception{
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String line = bufr.readLine();
		int len = StringLastWordLength(line);
		System.out.println(len);
	}
	public static int StringLastWordLength(String str){
		if(str == null || str.trim().equals(""))
			return 0;
		String[] strArr = str.split(" +");
		return strArr[strArr.length-1].length();
	}    
}
