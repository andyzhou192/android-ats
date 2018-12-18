package net.andy.android.ats.runner;

import net.andy.android.ats.model.ResultViewDO;
import net.andy.android.ats.model.TestResultDO;

import android.util.Log;

public class StartTest {
	private static final String TAG = "StartTest";
	private static Thread testRunnerThread = null;
	public static TestResultDO testResult;

	// Ϊ�÷�������߳���
	public static synchronized void run(ResultViewDO rv) {
		testResult = null;
		testRunnerThread = null;
//		if ((testRunnerThread != null) && !testRunnerThread.isAlive()) {
//			testRunnerThread = null;
//			Log.i(TAG, "testRunnerThread is null");
//		} else if (testRunnerThread == null) {
			// ����߳����������񣬼�TestRunner��run�����е�����
			testRunnerThread = new Thread(new TestRunner(rv));
			// �����߳�
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
