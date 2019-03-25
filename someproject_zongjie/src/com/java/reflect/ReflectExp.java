package com.java.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.java.file.excel.Customer;


public class ReflectExp {
	
	/**
	 * ��ʵ����ת��Ϊmap
	 * @param obj
	 * @return
	 */
	public static ConcurrentMap<String, Object> beanToMap(Object obj){
		Class<?> clazz=obj.getClass();
		 Field[] fileds= clazz.getDeclaredFields();
		 ConcurrentMap<String, Object> map= new ConcurrentHashMap<String,Object>();
		 for(Field f:fileds){
			 try {
				 f.setAccessible(true);
				 if(f.get(obj)!=null)
				map.put(f.getName(), f.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		 }
		return map;
	}
	
	/**
	 * ��ʵ�����װ��map
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	 @SuppressWarnings({ "rawtypes", "unchecked" })    
	    public static Map convertBean(Object bean) throws Exception {    
	        Class type = bean.getClass();    
	        Map returnMap = new HashMap();    
	        BeanInfo beanInfo = Introspector.getBeanInfo(type);    
	    
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();    
	        for (int i = 0; i< propertyDescriptors.length; i++) {    
	            PropertyDescriptor descriptor = propertyDescriptors[i];    
	            String propertyName = descriptor.getName();    
	            if (!propertyName.equals("class")) {    
	                Method readMethod = descriptor.getReadMethod();    
	                Object result = readMethod.invoke(bean, new Object[0]);    
	                if (result != null) {    
	                    returnMap.put(propertyName, result);    
	                } else {    
	                    returnMap.put(propertyName, "");    
	                }    
	            }    
	        }    
	        return returnMap;    
	    }  
	
	 
	 /**  
	     * ��һ�� Map ����ת��Ϊһ�� JavaBean  
	     * @param type Ҫת��������  
	     * @param map ��������ֵ�� map  
	     * @return ת�������� JavaBean ����  
	     * @throws IntrospectionException �������������ʧ��  
	     * @throws IllegalAccessException ���ʵ���� JavaBean ʧ��  
	     * @throws InstantiationException ���ʵ���� JavaBean ʧ��  
	     * @throws InvocationTargetException ����������Ե� setter ����ʧ��  
	     */    
	    @SuppressWarnings("rawtypes")    
	    public static Object convertMap(Class type, Map map)    
	            throws IntrospectionException, IllegalAccessException,    
	            InstantiationException, InvocationTargetException {    
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); // ��ȡ������    
	        Object obj = type.newInstance(); // ���� JavaBean ����    
	    
	        // �� JavaBean ��������Ը�ֵ    
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();    
	        for (int i = 0; i< propertyDescriptors.length; i++) {    
	            PropertyDescriptor descriptor = propertyDescriptors[i];    
	            String propertyName = descriptor.getName();    
	    
	            if (map.containsKey(propertyName)) {    
	                // ����һ����� try ������������һ�����Ը�ֵʧ�ܵ�ʱ��Ͳ���Ӱ���������Ը�ֵ��    
	                Object value = map.get(propertyName);    
	    
	                Object[] args = new Object[1];    
	                args[0] = value;    
	    
	                descriptor.getWriteMethod().invoke(obj, args);    
	            }    
	        }    
	        return obj;    
	    }  
	 
	 
	public static void main(String[] args) {
		Customer c=new Customer();
		c.setAccount("1111");
		c.setAddress("22222");
		c.setAge(111);
		c.setHoppy("basketball");
		c.setId("6666");
		c.setIsVip(true);
		c.setName("bob");
		c.setRemark("wo jiu shiw o");
		c.setSex("boy");
		//c.setTel("1008611");
		Map map=null;
		try {
			map = convertBean(c);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(!map.containsKey("tel")){
			System.out.println("hahahh");
		}
		System.out.println(map.size());
	}
}
