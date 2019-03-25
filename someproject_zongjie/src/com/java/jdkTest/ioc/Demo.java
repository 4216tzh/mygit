package com.java.jdkTest.ioc;

import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
 
/**
 * 演示类
 * @author student
 */
public class Demo {
	// 对象字符串数组
	private static String[] classNames = { "com.java.study.TzhController", "com.java.study.TzhDaoImpl" };
	// 用于存储字符串解析的对象
	private static Map map = new HashMap<>();
 
	public static void main(String[] args) throws Exception {
		init();
		//UserUI ui = (UserUI)getBean("ui");
		//ui.login();
	}
 
	/**
	 * 初始化函数 用于将根据字符串创建的对象解析后放入Map中
	 * 
	 * @throws Exception
	 */
	private static void init() throws Exception {
		// 遍历数组
		for (String className : classNames) {
			// 使用Class类通过字符串表示的类名获取对应类文件
			Class clazz = Class.forName(className);
			// 使用类文件新建对象
			Object obj = clazz.newInstance();
			// 对象存入Map[键：注解属性值，值：该注解属性值所标注的类对象]
			// ------|<强制转换以获得旗下方法>|<----获取此类的注解---->|<---获取注解类型：类注解---->|<-获取属性值->|<值>|
			map.put(((MyTypeAnnotation) clazz.getAnnotation(MyTypeAnnotation.class)).strType(), obj);
		}
		for (Object key : map.keySet()) {
			System.out.println(key + "\t" + map.get(key));
		}
 
	}
 
	/**
	 * 使用反射与递归获取对象
	 * 
	 * @param name
	 * @return obj
	 * @throws Exception
	 * @throws NoSuchMethodException
	 */
	private static Object getBean(String key) throws Exception {
		Object obj = null;
		// 依据传入的字符串类型键得到对象值
		obj = map.get(key);
		// 匹配判断
		if (obj != null) {
			// 获取对象的类
			Class clazz = obj.getClass();
			// 使用反射获取该类所有字段
			Field[] fs = clazz.getDeclaredFields();
			// ！第一次迭代终止处！
			// 遍历这些字段
			for (Field f : fs) {
				// 获取字段注解对象
				MyFieldAnnotation myFieldAnn = f.getAnnotation(MyFieldAnnotation.class);
				// 判断对象是否为空
				if (myFieldAnn != null) {
					// 获得注解属性，字符串处理得到对应类set方法名
					// -----------------|<-前缀->|<-------------获取首字母并大写---------------->|<----获取无首字母字符串------>|
					String methodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
					// 获得set方法：
					// -------------|<--反射获取方法-->|<--方法名-->|<---参数类型--->|
					Method method = clazz.getMethod(methodName, f.getType());
					//<--调用方法-->|<方法对象>|<强转目标类型.强制转换>|<-----递归：getBean("dao")----->|
					method.invoke(obj,    f.getType().cast(getBean(myFieldAnn.strField())));
				}
 
			}
		}
		return obj;
	}
}