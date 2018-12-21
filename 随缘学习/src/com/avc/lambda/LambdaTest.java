package com.avc.lambda;

import org.testng.AssertJUnit;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    public static void main(String[] args) {
    /*	String name = "Jzao";
        Runnable r = () -> System.out.println("666" + name);
        Thread t = new Thread(r);
        t.start();*/
    	//BinaryOperator<Long> addlongs = (x,y) -> x+y;
    	
    	List<String> collected = Stream.of("a","b","c")
    			.collect(Collectors.toList());
    	
    	AssertJUnit.assertEquals(Arrays.asList("a", "b", "c"), collected); 
    	
    	List<String> c2 = Stream.of("af","b","cfsf")
    			.map(s -> s.toUpperCase())
    			.collect(Collectors.toList());
    	
    	//AssertJUnit.assertEquals(Arrays.asList("A", "B", "C"), c2); 
    	
    	Integer toger = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4))
    			.flatMap(numbers -> numbers.stream()).filter(v->v>2)
    			.collect(Collectors.maxBy(Comparator.comparing(num -> num))).get();
    			//.collect(Collectors.toList());
    	
    	System.out.println("max = "+toger);
    	
    	String min = c2.stream().min(Comparator.comparing(s -> s.length())).get();
    	System.out.println(min);
    	int count = Stream.of(1,2,3).reduce(0, (acc,ele) -> acc +ele);
    	System.out.println(count);
    	Integer[] ar = new Integer[]{1,3,3};
    	System.out.println(addUp(Stream.of(ar)));
    	
    	Optional<?> emptyOptional = Optional.empty();
    	System.out.println(emptyOptional);
    }
    
	public long externalCountArtistsFromLondonExpanded(List<String> allArtists) {
    	return  allArtists.stream().filter(art -> art.equals("London")).count();
    }
	
	static int addUp(Stream<Integer> numbers) {
		 
		 return numbers.reduce((acc,ele)-> acc+ele).get();
	 }
   
}
