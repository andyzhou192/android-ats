package com.cmcc.android.ats;

import com.cmcc.android.ats.model.ResultViewDO;
import com.cmcc.android.ats.model.TestResultDO;
import com.cmcc.android.ats.runner.StartTest;
import com.cmcc.android.ats.util.ClickListenerUtil;
import com.cmcc.android.ats.view.MainView;
import com.cmcc.android.ats.view.ReflushDisplayView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class BaseActivity extends Activity {

	private Class<?>[] testClasses = null;
	private String testPackage = null;
	private ResultViewDO rv;
	private TestResultDO tr;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		@SuppressWarnings("deprecation")
		int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		MainView mv = new MainView(context, screenWidth);
		setContentView(mv.getBaseLayout());
		rv = new ResultViewDO(testClasses, this, testPackage, mv);
		rv.clearListViewAdapter();
		tr = new TestResultDO();
		StartTest.testResult = null;
		initOnClickListener();
		ReflushDisplayView.reflushResultDisplay(null, rv, tr);
	}

	public void setTestClass(Class<?>[] classes) {
		this.testClasses = classes;
	}

	public void setTestPackage(String testPackage) {
		this.testPackage = testPackage;
	}
	
	private void initOnClickListener() {
		ClickListenerUtil listener = new ClickListenerUtil(context, rv, tr);
		rv.getLaunchBtn().setOnClickListener(listener.getClickListener());
		rv.getRunTestCountView().setOnClickListener(listener.getClickListener());
		rv.getFailTestCountView().setOnClickListener(listener.getClickListener());
		rv.getErrTestCountView().setOnClickListener(listener.getClickListener());
	}

}
