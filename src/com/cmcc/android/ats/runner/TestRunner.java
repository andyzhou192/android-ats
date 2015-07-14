package com.cmcc.android.ats.runner;

import java.lang.reflect.Method;

import com.cmcc.android.ats.annotation.Parameters;
import com.cmcc.android.ats.model.ResultViewDO;
import com.cmcc.android.ats.model.TestResultDO;
import com.cmcc.android.ats.util.AllTestUtil;
import com.cmcc.android.ats.util.DialogUtil;
import com.cmcc.android.ats.view.ReflushDisplayView;

import android.test.AndroidTestRunner;
import android.util.Log;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;

public class TestRunner implements Runnable, TestListener {

	public final String TAG = "TestRunner";

	private ResultViewDO rv;
	private TestResultDO tr;

	public TestRunner(ResultViewDO rv) {
		this.rv = rv;
	}

	@Override
	public void run() {
		Log.i(TAG, "Test started");
		tr = new TestResultDO();
		// 运行测试
		getTestRunner().runTest();
		afterTest();
		Log.i(TAG, "Test ended");
	}

	// 监听开始测试
	@Override
	public void startTest(Test test) {
		Log.i(TAG, "startTest..." + test.getClass().getSimpleName());
		//去除AndroidTestCase中的测试方法
		if (TestRunnerHelper.isTest(test)) {
			tr.addTestCase(test);
			tr.increaseTestCounter();
			
			//获取当前测试方法名称
			String methodName = test.toString().split("\\(")[0];
			try {
				//获取当前执行的测试方法
				Method method = test.getClass().getMethod(methodName, new Class<?>[0]);
				int paramNum = method.getParameterTypes().length;
				//判断指定方法上是否含有@Parameters注解
				if(method.isAnnotationPresent(Parameters.class)){
					//获取注解
					Parameters params = method.getAnnotation(Parameters.class);
					String value = params.value();
					Log.i(TAG, value);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	// 监听测试的错误状态，进行处理
	@Override
	public void addError(Test test, final Throwable t) {
		TestRunnerHelper.addFailureAndError(test, tr, rv, t);
	}

	// 监听失败测试状态，进行处理
	@Override
	public void addFailure(Test test, final AssertionFailedError t) {
		TestRunnerHelper.addFailureAndError(test, tr, rv, t);
	}

	// 监听结束测试
	@Override
	public void endTest(Test test) {
		Log.i(TAG, "endTest..." + test.toString());
		if(TestRunnerHelper.isTest(test)){
			ReflushDisplayView.reflushResultDisplay(test, rv, tr);
		}
	}

	// 获取testRunner
	private AndroidTestRunner getTestRunner(){
		AndroidTestRunner testRunner = new AndroidTestRunner();
		// 设置在testRunner上运行测试类套件中的多个测试类，
		// 此处也可以单独运行一个测试类，如testRunner.setTest(new MathTest());
		testRunner.setTest(AllTestUtil.suite(rv));
		// 为testRunner添加监听
		testRunner.addTestListener(this);
		testRunner.setContext(rv.getMainActivity());
		return testRunner;
	}

	//测试执行完成后的清理
	private void afterTest() {
		StartTest.testResult = tr;
		StartTest.destroyThread();
		DialogUtil.dismissDialog();
	}

}
