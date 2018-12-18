package net.andy.android.ats.runner;

import net.andy.android.ats.model.ResultViewDO;
import net.andy.android.ats.model.TestResultDO;

import android.util.Log;

public class StartTest {
	private static final String TAG = "StartTest";
	private static Thread testRunnerThread = null;
	public static TestResultDO testResult;

	// 为该方法添加线程锁
	public static synchronized void run(ResultViewDO rv) {
		testResult = null;
		testRunnerThread = null;
//		if ((testRunnerThread != null) && !testRunnerThread.isAlive()) {
//			testRunnerThread = null;
//			Log.i(TAG, "testRunnerThread is null");
//		} else if (testRunnerThread == null) {
			// 添加线程驱动的任务，即TestRunner类run方法中的任务
			testRunnerThread = new Thread(new TestRunner(rv));
			// 启动线程
			testRunnerThread.start();
			Log.i(TAG, "testRunnerThread is started...");
//		} else {
//			Toast.makeText(rv.getMainActivity(), "Test is still running", Toast.LENGTH_SHORT)
//					.show();
//		}
	}
	
	public static void destroyThread(){
		testRunnerThread = null;
	}
	
}
