package com.cmcc.android.ats.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

public class TestResultDO {

	private int testCounter = 0, failureCounter = 0, errorCounter = 0;
	private List<Test> testCaseList = new ArrayList<Test>();
	private Map<String, String> failCaseMap = new HashMap<String, String>();
	private Map<String, String> errCaseMap = new HashMap<String, String>();
	
	public int getTestCounter() {
		return testCounter;
	}
	
	public void increaseTestCounter() {
		this.testCounter++;
	}
	
	public int getFailureCounter() {
		return failureCounter;
	}
	
	public void increaseFailureCounter() {
		this.failureCounter++;
	}
	
	public int getErrorCounter() {
		return errorCounter;
	}
	
	public void increaseErrorCounter() {
		this.errorCounter++;
	}
	
	public List<Test> getTestCaseList() {
		return testCaseList;
	}
	
	public void addTestCase(Test testCase) {
		this.testCaseList.add(testCase);
	}
	
	public Map<String, String> getFailCaseMap() {
		return failCaseMap;
	}
	
	public void addFailCase(String failCaseName, String failMessage) {
		this.failCaseMap.put(failCaseName, failMessage);
	}
	
	public Map<String, String> getErrCaseMap() {
		return errCaseMap;
	}
	
	public void addErrCase(String errCaseName, String errMessage) {
		this.errCaseMap.put(errCaseName, errMessage);
	}
}
