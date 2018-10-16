package com.eTeng.ds.stack.impl;
import org.junit.Before;
import org.junit.Test;

import com.eTeng.ds.stack.impl.MyArrayStack;

public class MyArrayStackTest {

	private MyArrayStack<String> myArrayStack;
	
	@Before
	public void setUp() {
		myArrayStack = new MyArrayStack<String>();
		
	}
	
	@Test
	public void testPush() {
		myArrayStack.push("0");
		myArrayStack.push("1");
		myArrayStack.push("2");
		System.out.println(myArrayStack);
	}
	
	@Test
	public void testPop() {
		testPush();
		myArrayStack.pop();
//		myArrayStack.pop();
//		myArrayStack.pop();
		System.out.println(myArrayStack);

	}
	
	@Test
	public void testPopAll() {
		testPush();
		myArrayStack.popAll();
		System.out.println(myArrayStack);
	}
	
	@Test
	public void testTop() {
		testPop();
		System.out.println(myArrayStack.top());
	}
}
