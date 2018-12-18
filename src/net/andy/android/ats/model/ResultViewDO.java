package net.andy.android.ats.model;

import net.andy.android.ats.view.MainView;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ResultViewDO {
	//获取测试类
	private Class<?>[] testClass;
	
	//测试包名package
	private String testPackage;

	//主视图activity
	private Activity mainActivity;
	
	//运行用例总数的视图, 失败用例数量的视图, 错误用例数量的视图
	private TextView runTestCountView, failTestCountView, errTestCountView;
	
	//测试执行按钮
	private Button launchBtn;
	private ArrayAdapter<String> listViewAdapter;
	
	private ListView listView;
	
	public ResultViewDO(Class<?>[] testClass, Activity mainActivity, String testPackage, MainView mv){
		this.testClass = testClass;
		this.mainActivity = mainActivity;
		this.testPackage = testPackage;
		this.listViewAdapter = mv.getListViewAdapter();
		this.listView = mv.getListView();
		this.launchBtn = mv.getLaunchBtn();
		this.runTestCountView = mv.getTestText();
		this.failTestCountView = mv.getFailureText();
		this.errTestCountView = mv.getErrorText();
	}
	
	public ListView getListView() {
		return listView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public Class<?>[] getTestClass() {
		return testClass;
	}

	public void setTestClass(Class<?>[] testClass) {
		this.testClass = testClass;
	}

	public Activity getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(Activity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public TextView getRunTestCountView() {
		return runTestCountView;
	}

	public void setRunTestCountView(TextView runTestCountView) {
		this.runTestCountView = runTestCountView;
	}

	public TextView getFailTestCountView() {
		return failTestCountView;
	}

	public void setFailTestCountView(TextView failTestCountView) {
		this.failTestCountView = failTestCountView;
	}

	public TextView getErrTestCountView() {
		return errTestCountView;
	}

	public void setErrTestCountView(TextView errTestCountView) {
		this.errTestCountView = errTestCountView;
	}

	public ArrayAdapter<String> getListViewAdapter() {
		return listViewAdapter;
	}

	public void setListViewAdapter(ArrayAdapter<String> listViewAdapter) {
		this.listViewAdapter = listViewAdapter;
	}

	public void clearListViewAdapter(){
		if(null != this.listViewAdapter)
			this.listViewAdapter.clear();
	}
	
	public Button getLaunchBtn() {
		return launchBtn;
	}

	public void setLaunchBtn(Button launchBtn) {
		this.launchBtn = launchBtn;
	}

	public String getTestPackage() {
		return testPackage;
	}

	public void setTestPackage(String testPackage) {
		this.testPackage = testPackage;
	}

}
