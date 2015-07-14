package com.cmcc.android.ats.runner;

import java.lang.reflect.Method;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.cmcc.android.ats.annotation.TargetSheet;
import com.cmcc.android.ats.handler.Excel2003Handler;
import com.cmcc.android.ats.model.ResultViewDO;
import com.cmcc.android.ats.model.TestResultDO;
import com.cmcc.android.ats.tools.StringTools;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.runner.BaseTestRunner;

public class TestRunnerHelper {

	// 判断是否是测试方法
	public static boolean isTest(Test test) {
		return (!"AndroidTestCase".equals(test.getClass().getSimpleName()) && !test
				.toString().contains("testAndroidTestCaseSetupProperly"));
	}

	// 添加失败和错误信息，并刷新页面
	public static TestResultDO addFailureAndError(Test test, TestResultDO tr, ResultViewDO rv,
			Throwable t) {
//		Test test = tr.getTestCounter() > 0 ? tr.getTestCaseList().get(
//				tr.getTestCounter() - 1) : tr.getTestCaseList().get(
//				tr.getTestCounter());
		if (TestRunnerHelper.isTest(test)) {
			// 计数自增
			if (t instanceof AssertionFailedError) {
				tr.increaseFailureCounter();
				tr.addFailCase(test.toString(), getMsg(t));
			} else if (t instanceof Throwable) {
				tr.increaseErrorCounter();
				tr.addErrCase(test.toString(), getMsg(t));
			}
			//ReflushDisplay.reflushResultDisplay(test, rv, tr);
		}
		return tr;
	}

	// 获取失败或错误信息
	private static String getMsg(Throwable t) {
		String message = null;
		if (t instanceof AssertionFailedError) {
			message = "failureMsg：" + t + "\n" + "detail："
					+ BaseTestRunner.getFilteredTrace(t);
		} else {
			message = "errorMsg：" + t + "\n" + "detail："
					+ BaseTestRunner.getFilteredTrace(t);
		}
		return message;
	}

	// 初始化参数
	public static void initParamFromExcel(Context ctx, Test test) {
		com.cmcc.android.ats.annotation.Test testAnnotation = test.getClass()
				.getAnnotation(com.cmcc.android.ats.annotation.Test.class);
		String fileName = testAnnotation.paramFile();
		if (!StringTools.isEmpty(fileName)) {
			String testCase = test.toString()
					.substring(0, test.toString().indexOf("(")).trim();// test_01(com.cmcc.example.TestClass)
			Method methods[] = test.getClass().getDeclaredMethods();
			for (Method m : methods) {
				if (m.getName().equals(testCase)) {
					int paramCount = m.getParameterTypes().length;
					TargetSheet sheetAnnotation = m
							.getAnnotation(TargetSheet.class);
					if (sheetAnnotation != null) {
						Object targetSheet = StringTools
								.isEmpty(sheetAnnotation.sheetName()) ? sheetAnnotation
								.sheetIndex() : sheetAnnotation.sheetName();
						List<String[]> paramList = Excel2003Handler.readExcel(
								ctx, fileName, targetSheet);
						if (paramList.get(0).length == paramCount) {
							for (int i = 0; i < paramList.size(); i++) {

							}
						} else {
							Log.e("InitParam",
									"actual param number is not equal the param number that in excel.");
						}
					}
					break;
				}
			}
		}
	}
}
