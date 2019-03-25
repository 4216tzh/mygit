package com.java.jdkTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda {
	
	private  List<String> list=new ArrayList<String>();
	
	private static List<Product> products=new ArrayList<Product>();
	
	public Lambda(){
		long i=0;
		float m=0.16f;
		while(i<10){
			this.list.add("test");
			i++;
			this.list.add("test"+i);
			Product p=new Product();
			p.setCode(StringUtil.getRandom());
			p.setCreateTime(new Date());
			p.setId(i);
			p.setPrice(new BigDecimal(i+m*(i+1.13)));
			p.setMemo("这是一个备注:"+StringUtil.getRandom());
			p.setName(StringUtil.getUUID());
			products.add(p);
		}
		
	}
	
	public static void main(String[] args) {
		Lambda lambda=new Lambda();
		lambda.list.forEach((player)->{
		if("test1".equals(player)){
			//lambda.list.remove(player);
			System.out.println(player);
		}
		});
		
		//lambda.list.forEach(System.out::println);
		
		//lambda.list.stream().filter((strs)->(strs.equals("test"))).limit(8).forEach(System.out::println);
		
		//lambda.products.stream().filter((p)->p.getPrice().doubleValue()>5).limit(11).forEach(System.out::println);
		//Stream<Product> s=lambda.products.stream().filter((p)->p.getPrice().doubleValue()>1);
		//Object[] ss=s.toArray();
		Map<Long, Product> map=products.stream().collect(Collectors.toMap(Product::getId, a->a,(key1,key2)->key1));
		BigDecimal total=products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
		total.setScale(2);
		//total.setScale(3, RoundingMode.DOWN);
		System.out.println(total.doubleValue());
		System.out.println(map.toString());
		List<Product> l=new ArrayList<Product>();
		Integer i=compares(-10,Math::abs);
		System.out.println(i);
	}
	
	public static void compare() {
		Comparator<Integer> t=(x,y)->Integer.compare(x,y);
		//Boolean b=(x,y)->{};
		//int i=(i1,i2) -> {return (i1 - i2);};
	}
	
	public static Integer getValue(Integer x,Integer y) {
		return (x-y);
	}
	
	public static Integer compares(Integer x,Function<Integer, Integer> fun) {
		Integer l=fun.apply(x);
		return l;
	}
	
	
	

}
