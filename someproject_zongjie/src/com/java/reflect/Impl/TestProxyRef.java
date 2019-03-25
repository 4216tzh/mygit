package com.java.reflect.Impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

import com.java.reflect.TestRef;

public class TestProxyRef implements InvocationHandler {
	Logger logger=Logger.getGlobal();
	
	 private Object delegate;
	 
	   /**
	    * 动态生成方法被处理过后的对象 (写法固定)
	    * 
	    * @param delegate
	    * @param proxy
	    * @return
	    */
	 public Object bind(Object delegate) {
	      this.delegate = delegate;
	       return Proxy.newProxyInstance(
	              this.delegate.getClass().getClassLoader(), this.delegate
	                       .getClass().getInterfaces(), this);
	  }

	@Override
	public Object invoke(Object paramObject, Method paramMethod,
			Object[] paramArrayOfObject) throws Throwable {
			
			Object obj="failed";
		if("useRef".equals(paramMethod.getName())){
			obj="success";
			logger.info(obj+",methodName:"+paramMethod.getName());
		}
		return obj;
	}
	
	public static void main(String[] args) {
		TestRef t=(TestRef)new TestProxyRef().bind(new TestRefImpl());
		t.useRef();
		t.testRef();
	
	}
}
