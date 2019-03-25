package com.java.jdkTest.ioc;

import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
 
/**
 * ��ʾ��
 * @author student
 */
public class Demo {
	// �����ַ�������
	private static String[] classNames = { "com.java.study.TzhController", "com.java.study.TzhDaoImpl" };
	// ���ڴ洢�ַ��������Ķ���
	private static Map map = new HashMap<>();
 
	public static void main(String[] args) throws Exception {
		init();
		//UserUI ui = (UserUI)getBean("ui");
		//ui.login();
	}
 
	/**
	 * ��ʼ������ ���ڽ������ַ��������Ķ�����������Map��
	 * 
	 * @throws Exception
	 */
	private static void init() throws Exception {
		// ��������
		for (String className : classNames) {
			// ʹ��Class��ͨ���ַ�����ʾ��������ȡ��Ӧ���ļ�
			Class clazz = Class.forName(className);
			// ʹ�����ļ��½�����
			Object obj = clazz.newInstance();
			// �������Map[����ע������ֵ��ֵ����ע������ֵ����ע�������]
			// ------|<ǿ��ת���Ի�����·���>|<----��ȡ�����ע��---->|<---��ȡע�����ͣ���ע��---->|<-��ȡ����ֵ->|<ֵ>|
			map.put(((MyTypeAnnotation) clazz.getAnnotation(MyTypeAnnotation.class)).strType(), obj);
		}
		for (Object key : map.keySet()) {
			System.out.println(key + "\t" + map.get(key));
		}
 
	}
 
	/**
	 * ʹ�÷�����ݹ��ȡ����
	 * 
	 * @param name
	 * @return obj
	 * @throws Exception
	 * @throws NoSuchMethodException
	 */
	private static Object getBean(String key) throws Exception {
		Object obj = null;
		// ���ݴ�����ַ������ͼ��õ�����ֵ
		obj = map.get(key);
		// ƥ���ж�
		if (obj != null) {
			// ��ȡ�������
			Class clazz = obj.getClass();
			// ʹ�÷����ȡ���������ֶ�
			Field[] fs = clazz.getDeclaredFields();
			// ����һ�ε�����ֹ����
			// ������Щ�ֶ�
			for (Field f : fs) {
				// ��ȡ�ֶ�ע�����
				MyFieldAnnotation myFieldAnn = f.getAnnotation(MyFieldAnnotation.class);
				// �ж϶����Ƿ�Ϊ��
				if (myFieldAnn != null) {
					// ���ע�����ԣ��ַ�������õ���Ӧ��set������
					// -----------------|<-ǰ׺->|<-------------��ȡ����ĸ����д---------------->|<----��ȡ������ĸ�ַ���------>|
					String methodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
					// ���set������
					// -------------|<--�����ȡ����-->|<--������-->|<---��������--->|
					Method method = clazz.getMethod(methodName, f.getType());
					//<--���÷���-->|<��������>|<ǿתĿ������.ǿ��ת��>|<-----�ݹ飺getBean("dao")----->|
					method.invoke(obj,    f.getType().cast(getBean(myFieldAnn.strField())));
				}
 
			}
		}
		return obj;
	}
}