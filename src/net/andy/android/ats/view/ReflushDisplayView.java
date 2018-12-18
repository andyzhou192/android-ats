package net.andy.android.ats.view;

import junit.framework.Test;

import net.andy.android.ats.model.ResultViewDO;
import net.andy.android.ats.model.TestResultDO;

public class ReflushDisplayView {

	// ����TestDisplay����
	public static void reflushResultDisplay(Test test, ResultViewDO rv, TestResultDO tr) {
		TestResultDisplayView td = new TestResultDisplayView(test, rv, tr);
		/**
		 * ʵ���߳�����TestDisplay����run�����е����� �˴���Ϊ�����������Activity��UI�߳���������
		 * ��Ϊ�ö���run�������漰��View�����UI������Ĳ�����
		 */
		rv.getMainActivity().runOnUiThread(td);
	}

	// ����TestDisplay����
	public static void reflushResultTitle(ResultViewDO rv, TestResultDO tr) {
		TestResultDisplayView td = new TestResultDisplayView(rv, tr, true, false);
		/**
		 * ʵ���߳�����TestDisplay����run�����е����� �˴���Ϊ�����������Activity��UI�߳���������
		 * ��Ϊ�ö���run�������漰��View�����UI������Ĳ�����
		 */
		rv.getMainActivity().runOnUiThread(td);
	}

	// ����TestDisplay����
	public static void reflushResultList(ResultViewDO rv, TestResultDO tr) {
		TestResultDisplayView td = new TestResultDisplayView(rv, tr, false, true);
		/**
		 * ʵ���߳�����TestDisplay����run�����е����� �˴���Ϊ�����������Activity��UI�߳���������
		 * ��Ϊ�ö���run�������漰��View�����UI������Ĳ�����
		 */
		rv.getMainActivity().runOnUiThread(td);
	}

}
