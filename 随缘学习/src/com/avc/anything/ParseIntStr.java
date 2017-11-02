package com.avc.anything;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class ParseIntStr {
	public static void main(String[] rgs){
        Scanner sc = new Scanner(System.in);
        try{
             BigInteger x = sc.nextBigInteger();
             BigInteger y = sc.nextBigInteger();
             System.out.println(x.add(y));
        }catch(Exception e){
            System.out.println("error");
        }
    }

}
