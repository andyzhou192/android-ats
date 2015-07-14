package com.cmcc.android.ats.view;

import com.cmcc.android.ats.util.ConstantsUtil;
import com.cmcc.android.ats.util.StructureWidgetUtil;
import com.cmcc.android.ats.view.textview.BorderTextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor") 
public class MainView extends View{
	@SuppressWarnings("unused")
	private static final String TAG = String.valueOf(MainView.class);
	
	Context context;
	
	private ListView listView;
	private ArrayAdapter<String> listViewAdapter;

	private Button launchBtn;
	private BorderTextView testText, failureText, errorText;
	private LinearLayout baseLayout, operateLayout, msgLayout;
	private TableLayout titleLayout;
	private int screenWidth;

	public MainView(Context context, int screenWidth) {
		super(context);
		this.context = context;
		this.screenWidth = screenWidth;
		this.initView();
	}
	
	private void initView(){
		//按钮区
		launchBtn = StructureWidgetUtil.strucButton(context, 0, ConstantsUtil.RUN_TEST, null);
		operateLayout = StructureWidgetUtil.strucLinearLayout(context, StructureWidgetUtil.MATCH_WRAP_LPS, LinearLayout.HORIZONTAL, launchBtn);
		
		//测试结果title展示区
		testText = StructureWidgetUtil.strucBorderTextView(context, ConstantsUtil.RUN_TAB_TITLE, 0, Color.WHITE, screenWidth/3);
		testText.setId(ConstantsUtil.RUN_VIEW_ID);
		failureText = StructureWidgetUtil.strucBorderTextView(context, ConstantsUtil.FAIL_TAB_TITLE, Color.BLUE, Color.GRAY, screenWidth/3);
		failureText.setId(ConstantsUtil.FAIL_VIEW_ID);
		errorText = StructureWidgetUtil.strucBorderTextView(context, ConstantsUtil.ERR_TAB_TITLE, Color.RED, Color.GRAY, screenWidth/3);
		errorText.setId(ConstantsUtil.ERR_VIEW_ID);
		titleLayout = StructureWidgetUtil.strucTableLayout(context, StructureWidgetUtil.strucTableRow(context, testText, failureText, errorText));
		
		//失败和错误信息list展示区
		listView = new ListView(context);
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
		msgLayout = StructureWidgetUtil.strucLinearLayout(context, null, 0, listView);
		
		//主视图拼装
		baseLayout = StructureWidgetUtil.strucLinearLayout(context, StructureWidgetUtil.MATCH_LPS, 0, operateLayout, titleLayout, msgLayout);
	}
	
	/** -----------------getter methods------------------ **/
	
	public LinearLayout getBaseLayout() {
		return this.baseLayout;
	}
	
	public ListView getListView() {
		return this.listView;
	}

	public Button getLaunchBtn() {
		return this.launchBtn;
	}

	public TextView getTestText() {
		return this.testText;
	}

	public TextView getFailureText() {
		return this.failureText;
	}

	public TextView getErrorText() {
		return this.errorText;
	}
	
	public ArrayAdapter<String> getListViewAdapter() {
		return this.listViewAdapter;
	}

	public void setListViewAdapter(ArrayAdapter<String> listViewAdapter) {
		this.listViewAdapter = listViewAdapter;
	}
}
