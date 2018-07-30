package com.eTeng.ds.stack.impl;

import org.junit.Before;
import org.junit.Test;

import com.eTeng.ds.stack.impl.MyLinkedStack;

public class MyLinkedStackTest {

	private MyLinkedStack<String> myLinkedStack;
	
	@Before
	public void setUp() {
		myLinkedStack = new MyLinkedStack<>();
	}
	
	@Test
	public void testPush() {
		myLinkedStack.push("0");
		myLinkedStack.push("1");
		myLinkedStack.push("2");
		System.out.println(myLinkedStack);
	}
	
	@Test
	public void testPop() {
		testPush();
		System.out.println(myLinkedStack.pop());
		System.out.println(myLinkedStack.pop());
//		System.out.println(myLinkedStack.pop());
		System.out.println(myLinkedStack);
	}
	
	@Test
	public void testPopAll() {
		testPush();
		myLinkedStack.popAll();
		System.out.println(myLinkedStack);
	}
	
	@Test
	public void testTop() {
		testPop();
		System.out.println(myLinkedStack.top());
	}
}
