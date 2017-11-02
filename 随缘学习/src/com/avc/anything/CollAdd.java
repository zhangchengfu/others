package com.avc.anything;

import java.util.*;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê11ÔÂ1ÈÕ
 */
public class CollAdd {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Set<Integer> set = new TreeSet<>();

		for(int i=0; i<n; i++)
			set.add(sc.nextInt());
		for(int i=0; i<m; i++)
			set.add(sc.nextInt());
		sc.close();
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			System.out.print(it.next());
			if(it.hasNext())
				System.out.print(" ");
		}
	}
}
