package com.avc.anything;

import java.util.*;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ9ÈÕ
 */
public class LenOfEnigth {
	static ArrayList<String> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(str).reverse().toString());
	}
	public static void main1(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
//		String str1 = sc.next();
//		String str2 = sc.next();
		String[] strArr = new String[len];
		for (int i = 0; i < strArr.length; i++) {
			strArr[i] = sc.next();
		}
		for (int i = 0; i < strArr.length; i++) {
			dealStr(strArr[i]);
		}
		//dealStr(str1);
	//	dealStr(str2);
		//System.err.println(list.size());
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}
	}
	public static String dealStr(String str) {
		String res="";
		if(!str.trim().equals("")) {
			if(str.length()<8) {
				for (int i = str.length(); i < 8; i++) {
					res+="0";
				}
				String tmp = (str+=res);
				list.add(tmp);
				res = tmp;
			}else if(str.length()==8){
				list.add(res+=str);
			}else {
				list.add(str.substring(0, 8));
				dealStr(str.substring(8));
			}
		}
		return res;
	}
}
