package com.java.reflect.Impl;

import com.java.reflect.TestRef;

public class TestRefImpl implements TestRef {

	@Override
	public void testRef() {
		System.out.println("this is testRef");
	}

	@Override
	public void useRef() {
		System.out.println("this is useRef");
	}

}
