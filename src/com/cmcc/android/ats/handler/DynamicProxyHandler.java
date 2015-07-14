package com.cmcc.android.ats.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {

	@Override
	public Object invoke(Object obj, Method method, Object[] aobj)
			throws Throwable {
		if(method.getClass().getName().equalsIgnoreCase("junit.framework.TestCase") && method.getName().equalsIgnoreCase("runTest")){
			System.out.println("----------->" + method.getName());
		}
		return null;
	}

}
