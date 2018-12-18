package net.andy.android.ats.view;

import junit.framework.Test;

import net.andy.android.ats.model.ResultViewDO;
import net.andy.android.ats.model.TestResultDO;

public class ReflushDisplayView {

	// 创建TestDisplay对象
	public static void reflushResultDisplay(Test test, ResultViewDO rv, TestResultDO tr) {
		TestResultDisplayView td = new TestResultDisplayView(test, rv, tr);
		/**
		 * 实现线程驱动TestDisplay对象run方法中的任务， 此处意为将该任务放在Activity的UI线程中启动，
		 * 因为该对象run方法中涉及到View组件（UI组件）的操作。
		 */
		rv.getMainActivity().runOnUiThread(td);
	}

	// 创建TestDisplay对象
	public static void reflushResultTitle(ResultViewDO rv, TestResultDO tr) {
		TestResultDisplayView td = new TestResultDisplayView(rv, tr, true, false);
		/**
		 * 实现线程驱动TestDisplay对象run方法中的任务， 此处意为将该任务放在Activity的UI线程中启动，
		 * 因为该对象run方法中涉及到View组件（UI组件）的操作。
		 */
		rv.getMainActivity().runOnUiThread(td);
	}

	// 创建TestDisplay对象
	public static void reflushResultList(ResultViewDO rv, TestResultDO tr) {
		TestResultDisplayView td = new TestResultDisplayView(rv, tr, false, true);
		/**
		 * 实现线程驱动TestDisplay对象run方法中的任务， 此处意为将该任务放在Activity的UI线程中启动，
		 * 因为该对象run方法中涉及到View组件（UI组件）的操作。
		 */
		rv.getMainActivity().runOnUiThread(td);
	}

}
