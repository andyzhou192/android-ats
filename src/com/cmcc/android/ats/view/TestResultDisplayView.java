package com.cmcc.android.ats.view;

import junit.framework.Test;

import com.cmcc.android.ats.model.ResultViewDO;
import com.cmcc.android.ats.model.TestResultDO;

public class TestResultDisplayView implements Runnable {
	@SuppressWarnings("unused")
	private static final String TAG = String.valueOf(TestResultDisplayView.class);

	private ResultViewDO rv;
	private TestResultDO tr;
	private Test test;
	private boolean isUpdateTitle, isUpdateList;

	public TestResultDisplayView(Test test, ResultViewDO rv, TestResultDO tr) {
		this.rv = rv;
		this.tr = tr;
		this.test = test;
		this.isUpdateTitle = true;
		this.isUpdateList = true;
	}
	
	public TestResultDisplayView(ResultViewDO rv, TestResultDO tr, boolean isUpdateTitle, boolean isUpdateList) {
		this.rv = rv;
		this.tr = tr;
		this.test = null;
		this.isUpdateTitle = isUpdateTitle;
		this.isUpdateList = isUpdateList;
	}

	@Override
	public void run() {
		if(null == tr){
			return;
		}
		if(isUpdateTitle){
			//获取成功case数量
			int succTestCount = tr.getTestCounter() - tr.getFailureCounter() - tr.getErrorCounter();
			// 设置界面title内容
			rv.getRunTestCountView().setText("RunTest：" + succTestCount + "/" + tr.getTestCounter());
			rv.getFailTestCountView().setText("Failure：" + tr.getFailureCounter());
			rv.getErrTestCountView().setText("Error：" + tr.getErrorCounter());
		}

		if(isUpdateList){
			if(null != test){
				// 在界面的listView中打印出测试错误的相关信息
				rv.getListViewAdapter().add(test.toString());
			}
			rv.getListView().setAdapter(rv.getListViewAdapter());
		}

		int height = rv.getRunTestCountView().getHeight();
		if(height > 0){
			rv.getFailTestCountView().setHeight(height);
			rv.getErrTestCountView().setHeight(height);
		}
	}
}
