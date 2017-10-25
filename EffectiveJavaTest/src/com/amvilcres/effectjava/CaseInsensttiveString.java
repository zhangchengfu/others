package com.amvilcres.effectjava;

import java.util.ArrayList;
import java.util.List;

public class CaseInsensttiveString {
	// HashMap
	private final String s;
	
	public CaseInsensttiveString(String s) {
		if(s == null)
			throw new NullPointerException();
		this.s = s;
	}
	
	@Override public boolean equals(Object o) {
//		if(o instanceof CaseInsensttiveString)
//			return s.equalsIgnoreCase(((CaseInsensttiveString) o).s);
//		if(o instanceof String)
//			return s.equalsIgnoreCase((String) o);
//		return false;
		return o instanceof CaseInsensttiveString &&((CaseInsensttiveString)o).s.equalsIgnoreCase(s);
	}
	
	public static void main(String[] args) {
		CaseInsensttiveString cis = new CaseInsensttiveString("Polish");
		String s = "polish";
		System.out.println(s.equals(cis));
		
		List<CaseInsensttiveString> list = new ArrayList<>();
		list.add(cis);
		
		System.out.println(list.contains(s));
	}

}
