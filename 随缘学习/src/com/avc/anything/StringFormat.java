package com.avc.anything;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ2ÈÕ
 */
import java.util.*;

public class StringFormat {
	public static void main(String[] args) {
		new StringFormat();
	}
	
	public StringFormat() {
		String str = "A%sCy%sE%s";
		char[] chs = {'E','F','G','P'};
		String s= formatString(str, str.length(), chs, chs.length);
		System.out.println(s);
	}
	
    public String formatString(String A, int n, char[] arg, int m) {
        // write code here
    	int i = 0;
    	while(A.contains("%s")) {
    		int start = A.indexOf("%s");
    		A = replaceStr(A, start, arg[i]);
//    		System.out.println("AA=="+A);
    		i++;
    	}
    	if(i != m) {
    		for(int j=i; j<m; j++) {
    			A = A.concat(arg[j]+"");
    		}
    	}
    	return A;
    }
    
    public String replaceStr(String str,int index, char c) {
    	return str.substring(0, index).concat(c+"").concat(str.substring(index+2));
    }
}