package com.avc.lambda;

import org.testng.AssertJUnit;

import com.avc.stream.StringCollector;
import com.avc.stream.StringCombiner;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    	
    	List<String> c2 = Stream.of("af","b","cfsf","sd")
    			.map(s -> s.toUpperCase())
    			.collect(Collectors.toCollection(LinkedList::new));
    	
    	//AssertJUnit.assertEquals(Arrays.asList("A", "B", "C"), c2); 
    	
    	Integer toger = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4))
    			.flatMap(numbers -> numbers.stream()).filter(v->v>2)
    			.collect(Collectors.maxBy(Comparator.comparing(num -> num))).get();
    			//.collect(Collectors.toList());
    	
    	System.out.println("max = "+toger);
    	// 最小值
    	String min = c2.stream().min(Comparator.comparing(s -> s.length())).get();
    	System.out.println("min="+min);
    	// 求和
    	//简写： Stream.of(1,2,3).reduce(0, (acc,ele) -> acc+ele);
    	int count = Stream.of(1,2,3).reduce(0, (acc,ele) -> { return acc+ele;});
    	count = Stream.of(1,2,3).reduce(0, Integer::sum);
    	System.out.println("count = "+count);
    	// 流式求和
    	Integer[] ar = new Integer[]{1,3,3};
    	System.out.println(addUp(Stream.of(ar)));
    	
    	Optional<?> emptyOptional = Optional.empty();
    	System.out.println(emptyOptional);
    	
    	// 数据分块(分解成两个集合)
    	Map<Boolean, List<String>> ls = c2.stream().collect(Collectors.partitioningBy(s -> s.length() > 1));
    /*	System.out.println("数据分块");
    	ls.forEach((k, v) ->{
    		System.out.println(k + "---" + Arrays.deepToString(v.toArray()));
    	});
    	*/
    	// 数据分组是一种更自然的分割数据操作，可以使用任意值对数据分组
    	Map<Integer, List<String>> map =  c2.stream().collect(Collectors.groupingBy(s -> s.length()));
    	/*System.out.println("数组分组：");
    	map.forEach((k,v) -> {
    		System.out.println(k);
    		System.out.println(Arrays.deepToString(v.toArray()));
    	});*/
    	
    	// 组合收集器
    	Map<Integer, Long> cm = c2.stream().collect(Collectors.groupingBy(s -> s.length(),Collectors.counting()));
    /*	System.out.println("组合收集器：");
    	Map<Integer, List<String>> p = c2.stream().collect(Collectors.groupingBy(s -> s.length(),
    			 Collectors.mapping(val -> val.toLowerCase(), Collectors.toList())));
    	cm.forEach((key,value)->{
    		System.out.println(key+"::"+value);
    	});
    	*/
    	// 字符串
    	String str = c2.stream().collect(Collectors.joining(",", "[", "]"));
    	//System.out.println("字符串："+str);
    	//stringCombinerTest();
    	List<Integer> nums = Stream.of(1,2,3,4,5).collect(Collectors.toList());
    	System.out.println("***:"+multiplyThrough(nums));
    }
    
	public long externalCountArtistsFromLondonExpanded(List<String> allArtists) {
    	return  allArtists.stream().filter(art -> art.equals("London")).count();
    }
	
	static int addUp(Stream<Integer> numbers) {
		 return numbers.reduce((acc,ele)-> acc+ele).get();
	 }
   
	private static void stringCombinerTest() {
		List<String> strList = new ArrayList<>();
		strList.add("Acb");
		strList.add("fff");
		strList.add("xYz");
		
		/*String res = strList.stream()
				.map(s->s.toLowerCase())
				.reduce(new StringCombiner(",", "[", "]"),
						StringCombiner::add,
						StringCombiner::merge)
		.toString();*/
		
		String res = strList.stream()
				.map(s->s.toLowerCase())
				.collect(new StringCollector(",","[","]"));
		
		System.out.println("res = "+res);
	}
	
	private static int multiplyThrough(List<Integer> nums) {
		return 5*nums.parallelStream().reduce(1, (acc,x) -> acc * x);
	}
}
