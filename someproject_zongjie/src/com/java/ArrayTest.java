package com.java;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArrayTest {
	
	public static void main(String[] args) {
		/*List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(10);
		list.add(7);
		list.add(9);
		list.add(11);
		List<Integer> lists=new ArrayList<Integer>();
		lists.add(22);
		lists.add(23);
		lists.add(21);
		lists.add(20);
		lists.add(25);
		Integer[] targets=new Integer[list.size()+lists.size()];
		System.arraycopy(list.toArray(new Integer[0]), 0, targets, 0, list.size());
		System.arraycopy(lists.toArray(new Integer[0]), 0, targets, list.size(), lists.size());
		
		Set<Integer> set=new TreeSet<Integer>(Arrays.asList(targets));
		for(Integer x:set){
			System.out.println(x);
		}
		String[] s={"1","2"};
		String[] i={"1","b","c"};
		
		Set set=arrayCopys(s,i);
		Iterator it=set.iterator();
		while(it.hasNext()){
			Object obj=it.next();
			System.out.println(obj.toString());
		}*/
		 String str=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		 System.out.println(str);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set arrayCopys(Object[] a,Object[] b){
		if(a==null||b==null||a.length==0||b.length==0){
			return null;
		}else if((a.getClass()) != (b.getClass())){
			return null;
		}
		Object[] target=new Object[a.length+b.length];
		System.arraycopy(a, 0, target, 0, a.length);
		System.arraycopy(b, 0, target, a.length, b.length);
		return new TreeSet(Arrays.asList(target));
	}
	

}
