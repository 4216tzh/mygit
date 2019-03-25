package com.java.connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Animal {

	public static final String KEY = "key";

	public static final String VALUE = "value";

	public static void main(String[] args) {
		// 先进先出(多线程下安全队列)
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		// synchronizedList
		List<String> list = Collections
				.synchronizedList(new ArrayList<String>());
		list.add("key");
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
		for (int n = 0; n < 10; n++) {
			map.put(KEY + n, VALUE + n);
		}
		if (map != null || map.size() > 0) {
			Iterator<Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				System.out.println("key:" + entry.getKey() + ",value:"
						+ entry.getValue());
			}
		}
		try {
			for (int i = 0; i < 10; i++) {
				queue.put("我是第" + i + "个");
			}
			String res = null;
			while ((res = queue.poll()) != null) {
				System.out.println("结果：" + res);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
