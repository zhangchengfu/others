package com.amvilcres.effectjava;

import java.util.HashMap;
import java.util.Map;

public final class PhoneNumver {
	private final short areCode ;
	private final short prefix;
	private final short lineNumber;
	
	public PhoneNumver(int areCode, int prefix, int lineNumber) {
		rangeCheck(areCode, 999, "are Code");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "line number");
		
		this.areCode = (short)areCode;
		this.prefix = (short)prefix;
		this.lineNumber = (short)lineNumber;
	}
	
	private static void rangeCheck(int arg, int max, String name){
		if(arg < 0 || arg > max)
			throw new IllegalArgumentException(name+":"+arg);
	}
	
	@Override public int hashCode() {return 42;}
	
	@Override public boolean equals(Object o) {
		if(o == this)
			return true;
		if(!(o instanceof PhoneNumver))
			return false;
		
		PhoneNumver pn = (PhoneNumver)o;
		return pn.lineNumber == lineNumber
			&& pn.prefix == prefix
			&& pn.areCode == areCode;
	}

	public static void main(String[] args) {
		Map<PhoneNumver,String> mp = new HashMap<PhoneNumver,String>();
		mp.put(new PhoneNumver(707, 867, 5309),"Jerry");
		String value = mp.get(new PhoneNumver(707, 867, 5309));
		System.out.println(value);
	}

}
